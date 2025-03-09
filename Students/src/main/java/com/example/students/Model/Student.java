package com.example.students.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private int age;
    private Student degree;
    private  double gpa;
}
