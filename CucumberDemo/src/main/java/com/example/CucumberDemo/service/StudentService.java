package com.example.CucumberDemo.service;

import com.example.CucumberDemo.pojo.Student;

import java.util.List;
public interface StudentService {
    List<Student> getStudent();

    Student addStu(Student s);

    void upStu(Student stu, int id);

    void delete(int id);
}
