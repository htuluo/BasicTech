package com.llm.spring.demo;

import org.springframework.stereotype.Controller;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Controller
public class DemoController {


    public String get(String str){
        return "Hello World";
    }
}
