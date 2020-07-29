package com.llm.spring.config;

import com.llm.spring.service.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/24
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Configuration
@ComponentScan(basePackages = "com.llm.spring")
public class AppConfig {

    @Bean
    public User getUser() {
        return new User();
    }

}
