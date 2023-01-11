package com.example.CucumberDemo.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student {

    private int rno;
    private String address;
    private String name;

    public Student(int rno, String address, String name) {
        super();
        this.rno = rno;
        this.address = address;
        this.name = name;
    }


}
