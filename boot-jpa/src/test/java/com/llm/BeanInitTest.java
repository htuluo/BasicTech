package com.llm;

import com.llm.entity.HelloWorld;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class BeanInitTest {
    public static void main(String[] args) {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld helloWorld = (HelloWorld)context.getBean("helloWorld");
        helloWorld.getMessage();
        context.registerShutdownHook();
    }
}
