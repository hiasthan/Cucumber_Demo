package com.example.CucumberDemo.cucumber;


import com.example.CucumberDemo.CucumberDemoApplication;
import com.example.CucumberDemo.controller.StudentController;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = {CucumberDemoApplication.class , StudentController.class} ,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(plugin = {"pretty"} ,tags = "" , features = "src/test/resources/features")
public class CucumberTestClass {
}
