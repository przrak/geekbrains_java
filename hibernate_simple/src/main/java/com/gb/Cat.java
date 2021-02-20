package com.gb;

@Table(title = "cat")
public class Cat {
    @Column
    String name;

    @Column
    String color;

    @Column
    int age;

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }
}
