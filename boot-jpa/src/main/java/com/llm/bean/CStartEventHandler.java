package com.llm.bean;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("------------ContextStartedEvent Received");
    }
}
