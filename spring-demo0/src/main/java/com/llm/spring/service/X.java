package com.llm.spring.service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/24
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Component
@Order(5)
public class X {
    public X(){
        System.out.println("X started initi");
    }
}
