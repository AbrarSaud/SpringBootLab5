package com.example.students.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int id;
    public String name;
    public int age;
    public Student degree;
    public  int gpa;
}
