package com.gb;

@Table(title = "students")
public class Student {
    @Column
    int id;

    @Column
    String name;

    @Column
    int score;

    public Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
