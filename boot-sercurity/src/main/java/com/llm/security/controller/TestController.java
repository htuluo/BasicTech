package com.llm.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

public class TestController {
    @RequestMapping("test/{str}")
    public String get(@PathParam("str") String str){
        return "you are calling "+str;

    }
}
