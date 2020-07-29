package com.llm.spring.service;

import org.springframework.beans.factory.annotation.Value;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class User {
    @Value("sss")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User() {
    }
}
