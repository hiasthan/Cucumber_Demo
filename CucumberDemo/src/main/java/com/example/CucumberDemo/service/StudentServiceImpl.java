package com.example.CucumberDemo.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.CucumberDemo.pojo.Student;

@Component
public class StudentServiceImpl implements StudentService {

    static List<Student> li = new ArrayList<Student>();

    static
    {
        li.add(new Student(1,"Pol","Tom"));
        li.add(new Student(2,"Ind","Com"));
    }


    @Override
    public List<Student> getStudent() {
        return li;
    }

    @Override
    public Student addStu(Student s) {
        li.add(s);
        return s;
    }

    @Override
    public void upStu(Student stu, int id) {
        li.stream().map(i->{
            if(i.getRno()==id ) {
                i.setAddress(stu.getAddress());
                i.setName(stu.getName());
            }
            return i;
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        li=li.stream().filter(i->
        {
            if(id!=i.getRno())
            {
                return true;
            }
            else
                return false;
        }).collect(Collectors.toList());
    }

}

