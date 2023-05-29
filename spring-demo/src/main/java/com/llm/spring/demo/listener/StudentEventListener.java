package com.llm.spring.demo.listener;

import com.llm.spring.demo.dto.Student;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class StudentEventListener  {
    @EventListener(condition = "#student.id!=null")
    public void handleEvent(Student student) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);

        }
        System.out.println(student);

    }
}
