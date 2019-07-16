package com.llm.security.controller;

import com.llm.security.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@RestController
@RequestMapping(value = "/user",method = RequestMethod.GET)
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public String getUser(){
        return "hello Spring Security";
    }

    @GetMapping("/login")
    public String login(String username,String password){

        UserDetails userDetails = userService.loadUserByUsername(username);
        return userDetails.getUsername();
    }


}
