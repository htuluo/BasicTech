package com.llm.spring;

import com.llm.spring.config.AppConfig;
import com.llm.spring.service.X;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/24
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class AppTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(X.class);
//        AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
//        autowireCapableBeanFactory.getBean("x");

    }
}
