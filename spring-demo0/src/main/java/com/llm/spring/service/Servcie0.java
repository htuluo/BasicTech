package com.llm.spring.service;

import org.springframework.beans.factory.DisposableBean;
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
@Order(3)
public class Servcie0 implements DisposableBean {
    public Servcie0() {
        System.out.println("Servcie0 is inited");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Servcie0 is destroyed");
    }
}
