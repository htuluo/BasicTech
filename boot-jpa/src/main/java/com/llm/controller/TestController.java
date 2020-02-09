package com.llm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 测试
 * @RestController 注解等同于@Controller @ResponseBody两个注解的合用
 */
//@RestController(value = "test")
@Controller
@ResponseBody
@RequestMapping("test")
public class TestController {

    @GetMapping("get")
    public String get(String str) {
        return "you are calling the " + str;
    }
}
