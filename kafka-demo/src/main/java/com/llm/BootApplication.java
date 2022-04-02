package com.llm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication()
@Slf4j
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BootApplication.class);
//        springApplication.setRegisterShutdownHook(false);
        springApplication.run(args);
//        SpringApplication.run(SpringJPABootApplication.class, args);
        log.info("Hello World!");
    }
}
