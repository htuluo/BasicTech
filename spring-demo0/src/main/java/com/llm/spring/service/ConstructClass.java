package com.llm.spring.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ： luolm
 * @date ：Created in 2020/9/14
 * @description： //TODO
 */
@Component
public class ConstructClass {
    private static ConstructClass instance=null;

    /**
     *
     */
    @PostConstruct
    private void init(){
        instance=this;
    }
    public static ConstructClass getInstance(){
        return instance;
    }

    public void method(){
        System.out.println("method is called");
    }

}
