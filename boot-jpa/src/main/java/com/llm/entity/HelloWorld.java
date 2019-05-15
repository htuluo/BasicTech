package com.llm.entity;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/14
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class HelloWorld {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void init(){
        System.out.println("Bean is initing");
    }
    public void  destory(){
        System.out.println("Bean will be destroy now");
    }
}
