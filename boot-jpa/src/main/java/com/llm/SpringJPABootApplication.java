package com.llm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class SpringJPABootApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringJPABootApplication.class);
//        springApplication.setRegisterShutdownHook(false);
        springApplication.run(args);
//        SpringApplication.run(SpringJPABootApplication.class, args);
        System.out.println("Hello World!");
    }
}
