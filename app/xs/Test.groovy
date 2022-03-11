package com.sky.xs

class Test {
    String name;
    String title;

    Test(String name, String title) {
        this.name = name
        this.title = title
    }

    def print() {
        println "$name + $title"
    }
}