package com.llm.spring.demo.controller;

import com.llm.spring.demo.service.StudentEventRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "testEvent")
public class RestStudentEventController {
    @Autowired
    private StudentEventRegisterService registerService;
    @GetMapping(value = "register")
    public void registerUser(){
        registerService.register();

    }
}
