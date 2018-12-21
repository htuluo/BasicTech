package com.llm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.llm")
public class SwaggerAppliation
{
    public static void main( String[] args )
    {
        SpringApplication.run(SwaggerAppliation.class,args);
        System.out.println( "Hello World!===================" );
    }
}
