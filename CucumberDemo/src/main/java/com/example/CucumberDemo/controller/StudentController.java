package com.example.CucumberDemo.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CucumberDemo.pojo.Student;
import com.example.CucumberDemo.service.StudentService;

@RestController
@AllArgsConstructor
public class StudentController {

    @Autowired
    StudentService ss;

    @GetMapping("/home")
    public String homePage() {
        return "Welcome to Home";

    }

    @GetMapping("/getDetails")
    public List<Student> getDetails() {
        return ss.getStudent();
    }

    /*
     * @GetMapping("/getDetails/{rn}") public List<Student>
     * getDetailsByRn(@PathVariable("rn") int rn) { return ss.getStudent(rn); }
     */


    @PostMapping("/addStu")
    public Student addStudent(@RequestBody Student stu) {
        Student s = this.ss.addStu(stu);
        return s;
    }


    @PutMapping("/upStu/{rno}")
    public Student updateStudent(@RequestBody Student stu , @PathVariable("rno") int id)
    {
        this.ss.upStu(stu,id);
        return stu;

    }

    @DeleteMapping("/deleteStu/{rno}")
    public void deleteStudent(@PathVariable("rno") int id) {
        this.ss.delete(id);
    }
}

