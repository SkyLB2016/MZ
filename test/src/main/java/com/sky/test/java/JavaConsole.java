package com.sky.test.java;

import com.sky.test.kt.Course;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/16 7:01 下午
 * @Version: 1.0
 */
public class JavaConsole {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int a = new PAA().a;
    }

    private static void extracted1() throws IOException, ClassNotFoundException {
        Course course = new Course("英语", 12f);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\WorkSpace\\XS\\test\\src"));
        oos.writeObject(course);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\WorkSpace\\XS\\test\\src"));
        Course cc = (Course) ois.readObject();
        System.out.println("course:$cc");
    }

    final Course course11 = new Course("英语", 12f);

    public void test() {
        course11.setScore(11f);
    }

    private static void extracted() throws IOException, ClassNotFoundException {
        Course course = new Course("英语", 12f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(course);
        course.setScore(78f);
        // oos.reset();
        oos.writeUnshared(course);
        // oos.writeObject(course);
        byte[] bs = out.toByteArray();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs));
        Course course1 = (Course) ois.readObject();
        Course course2 = (Course) ois.readObject();
        System.out.println("course1: " + course1);
        System.out.println("course2: " + course2);
    }
}
