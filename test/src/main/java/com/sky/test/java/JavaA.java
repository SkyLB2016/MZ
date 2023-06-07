package com.sky.test.java;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Comparator;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/1/5 6:33 下午
 * @Version: 1.0
 */
public class JavaA {
    //    Comparable
//    Comparator
    public static void main(String[] args) {
        System.out.println("lskfddjhfklshkl");
        rename();
    }

    public static void rename() {
        String dir = "/Users/sky/Documents/Java/Test/src/main/resources/banner";
        File dirFile = new File(dir);
        File[] files = dirFile.listFiles();
        int i = 0;
        for (File f : files) {
            System.out.println(f.getName());
            if (f.getName().startsWith("emotion")) {
                i++;
                System.out.println(f.getName() + "===" + i);
                DecimalFormat format = new DecimalFormat("#00");
                f.renameTo(new File(dir + "/banner" + format.format(i) + ".png"));
            }
        }
    }
}
