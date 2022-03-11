package com.sky.test.kt

import com.sky.test.InterBB
import com.sky.test.java.PAA
import java.io.*

fun main() {
//    InterAA.AA().a
//    InterAA.BB().b
//    InterAA.CC().c
//    InterBB().testBB()
//    for (i in 0..10) {
//        println(i !in 5..9)
//        println(i in 5..9)
//    }
//    for (i in 0 until 10) {
//        println(i)
//    }
//    sealedTest(SealedAA.BB)
//    val a:Boolean=false
//    null
    val a =1.rangeTo(10)
    for (i in a){
        println(i)
    }
//    deserialFromFile()
//    serialToFile()
}

fun sealedTest(sealedAA: SealedAA) {
    when (sealedAA) {
        SealedAA.AA -> println("aaaaaa")
        SealedAA.BB -> println("bbbbbb")
        SealedAA.CC -> println("cccccc")
    }
}

private fun bufferreader() {
    val buffer = BufferedWriter(FileWriter(""))
    buffer.write("fdjflskdf")
    val bos =
        BufferedOutputStream(FileOutputStream("/Users/sky/Documents/WorkSpace/XS/test/src/main/java/com/sky/test/person.txt"))

    bos.write("福建省来看待解放路手机登录非空集收了款".toByteArray())
    bos.flush()
    bos.close()
}

private fun serialToFile() {
    val course = Person("英语", 12f)
    val oos =
        ObjectOutputStream(FileOutputStream("/Users/sky/Documents/WorkSpace/XS/test/src/main/java/com/sky/test/person.txt"))
    oos.writeObject(course)
    course.score = 88f
    Person.PBB = 9
    oos.writeUnshared(course)
    oos.reset()
    oos.writeObject(course)
    oos.close()
}

private fun deserialFromFile() {
    val ois =
        ObjectInputStream(FileInputStream("/Users/sky/Documents/WorkSpace/XS/test/src/main/java/com/sky/test/person.txt"))
    val cc = ois.readObject() as Person
    val cc1 = ois.readObject() as Person
    val cc2 = ois.readObject() as Person
    println("course:$cc")
    println("course:$cc1")
    println("course.paa==:${Person.PAA}")
    println("course.pbb==:${Person.PBB}")
    println("course:$cc2")
}

private fun serialToByte() {
    val course = Course("英语", 12f)
    val bos = ByteArrayOutputStream()
    val oos = ObjectOutputStream(bos)
    oos.writeObject(course)
    course.score = 78f
    // oos.reset();
//    oos.writeUnshared(course)
    oos.reset();
    oos.writeObject(course);
//     oos.writeObject(course);
    val bs = bos.toByteArray()
    oos.close()
    val bis = ByteArrayInputStream(bs)
    val ois = ObjectInputStream(bis)
    val course1 = ois.readObject() as Course
    val course2 = ois.readObject() as Course
//    val course3 = ois.readObject() as Course
    println("course1: $course1")
    println("course2: $course2")
    println("course2: ${Course.CAA}")
}