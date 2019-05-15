package com.llm.bean;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        System.out.println("------------------ContextStoppedEvent Received");
    }
}
