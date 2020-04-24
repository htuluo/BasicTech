package com.llm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


/**
 * 测试
 *
 * @RestController 注解等同于@Controller @ResponseBody两个注解的合用，
 * 如果RestController有value属性，需要对应RequestMapping注解
 */
//@RestController(value = "test")
@Controller
@ResponseBody
@RequestMapping("test")
@Slf4j
public class TestController {

    @GetMapping("get")
    public String get(String str) {
        log.info("get start ");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(1==1){
//            return null;
            throw new RuntimeException("aa");
        }
        return "you are calling the " + str;
    }

    @PostMapping("post")
    public String post(@RequestBody String string) {
        log.info("post start ");
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "you are post the "+string;
    }
}
