package com.example.students.Controller;

import com.example.students.Api.ApiResponse;
import com.example.students.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();

    // Display all students
    @GetMapping("/get")
    public ArrayList<Student> getStudents() {
        return students;
    }

    //  Create a new student
    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Added successfully!");
    }

    // Update a student
    @PutMapping("/update/{id}")
    public ApiResponse updateTask(@PathVariable int id, @RequestBody Student student) {
        for (Student s : students) {
            if (s.getId() == id) {
                students.set(id, student);
                return new ApiResponse("Updated successfully!");
            }
        }
        return new ApiResponse("Student not found");
    }

    // Delete a student
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteTask(@PathVariable int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(id);
                return new ApiResponse("Deleted successfully!");
            }
        }
        return new ApiResponse("Student not found");
    }

    //  Based on GPA, classify students into honors categories.
    @GetMapping("/honors/{id}")
    public ApiResponse honorsCategories(@PathVariable int id) {
        for (Student s : students) {
            double gpa = s.getGpa();
            if (s.getId() == id) {
                if (gpa >= 4.75 && gpa <= 5) {
                    return new ApiResponse("First Class Honors");
                } else if (gpa >= 4.25 && gpa < 4.75) {
                    return new ApiResponse("Second Class Honors");
                } else {
                    return new ApiResponse("No honors");
                }
            }
        }
        return new ApiResponse("Student not found");
    }

    // Display a group of students whose GPA is greater than the average GPA
    @GetMapping("/ava")
    public ArrayList<Student> aveGPA() {
        double total = 0;
        for (Student s : students) {
            total += s.getGpa();
        }
        double ave = total / students.size();
        ArrayList<Student> greaterAveStudents = new ArrayList<>();
        for (Student s : students) {
            if (s.getGpa() > ave) {
                greaterAveStudents.add(s);
            }
        }
        return greaterAveStudents;
    }


}