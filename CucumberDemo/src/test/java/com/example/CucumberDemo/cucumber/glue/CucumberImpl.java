package com.example.CucumberDemo.cucumber.glue;

import com.example.CucumberDemo.pojo.Student;
import com.example.CucumberDemo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CucumberImpl {

    @Autowired
    private TestRestTemplate tst;

    @Autowired
    private ObjectMapper om;

    private List<Student> expected;

    private List<Student> actual;

    @Autowired
    private StudentService ss;

    @Before
    public void setup()
    {
        expected=new ArrayList<>();
        actual= new ArrayList<>();
    }

    @Given("^Data is present$")
    public void studentDataPresent(final List<Student> student)
    {
        expected.addAll(student);
    }

    @When("^user request the application to get the data of the student$")
    public void getStudent() {
        actual=ss.getStudent();
    }

    @When("^user requested to add new student with rno ([0-9]) address (.*) name (.*)$")
    public void postRequest(final Integer rno , final String address , final String name )
    {
        expected=ss.getStudent();
        final Student expectedStu=new Student(rno , address,name);
        ss.addStu(expectedStu);
        actual=ss.getStudent();
    }

    @When("^user request to update rno ([0-9]) with Address (.*) name (.*)$")
    public void putRequest(final Integer rno , final String address , final String name )
    {
        expected=ss.getStudent();
        final Student expectedStu=new Student(rno , address,name);
        ss.upStu(expectedStu,rno);
        actual=ss.getStudent();
    }

    @When("^user request to delete the student with rno ([0-9])$")
    public void deleteRequest(final Integer rno)
    {
        expected=ss.getStudent();
        ss.delete(rno);
        actual=ss.getStudent();
    }


    @Then("^return all the data of the student$")
    public void all()
    {
        validateStudentDetails();
    }

    @Then("^new student should be added with rno ([0-9])$")
    public void postedData(Integer rno)
    {
        validateCountOfData(rno);
    }

    @Then("^Data of student should be updated with Address (.*) name (.*) for rno ([0-9])")
    public void putData(String address , String name , Integer rno)
    {
        validateStudentUpdate(rno,address,name);
    }

    @Then("^data of the student with rno ([0-9]) should be deleted")
    public void deleteData(Integer rno)
    {
        validateDeletedStudent(rno);
    }

    private void validateStudentDetails()
    {
        Assertions.assertEquals(expected.size(),actual.size());
        IntStream.range(0,actual.size()).forEach(i ->
                validateStudent(expected.get(i),actual.get(i)));
    }
    private void validateCountOfData(Integer rno) {
        Assertions.assertEquals(expected.size(), actual.size());
        List<Integer> id= new ArrayList<>();
        id=actual.stream().map(i->i.getRno()).collect(Collectors.toList());
        Assert.assertTrue(id.contains(rno));
    }

    private void validateStudent (final Student expected , final Student actual)
    {
        Assertions.assertEquals(expected.getRno(),actual.getRno());
        Assertions.assertEquals(expected.getName(),actual.getName());
        Assertions.assertEquals(expected.getAddress(),actual.getAddress());
    }

    private void validateStudentUpdate(Integer rno , String address , String name)
    {
        Assertions.assertEquals(expected.size(),actual.size());
        IntStream.range(0,actual.size()).forEach(i -> {
                    if (rno == actual.get(i).getRno()) {
                        Assertions.assertEquals(address,actual.get(i).getAddress());
                        Assertions.assertEquals(name,actual.get(i).getName());

                    }
                });
    }

    private void validateDeletedStudent(Integer rno) {
        Assertions.assertEquals(expected.size()-1, actual.size());
        List<Integer> id= new ArrayList<>();
        id=actual.stream().map(i->i.getRno()).collect(Collectors.toList());
        Assert.assertTrue(!id.contains(rno));
    }

}
