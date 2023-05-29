package com.llm.spring.demo.service;

import com.llm.spring.demo.dto.Student;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentEventRegisterServiceImpl implements StudentEventRegisterService{
    @Resource
    ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void register() {
        Student student = new Student(2L, "张三");
        applicationEventPublisher.publishEvent(student);
        System.out.println("发布完成。。。");
    }
}
