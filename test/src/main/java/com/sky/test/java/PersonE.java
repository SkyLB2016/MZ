package com.sky.test.java;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/23 6:39 下午
 * @Version: 1.0
 */
public class PersonE {
   public static String aa = "ABC";

    private String name;
    private double score;
    private int sex = 1;

    public PersonE(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public PersonE(String name, double score, int sex) {
        this.name = name;
        this.score = score;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}