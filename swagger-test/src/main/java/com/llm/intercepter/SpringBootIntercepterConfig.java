package com.llm.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/12/21
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Configuration
public class SpringBootIntercepterConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginIntercepter())
                .addPathPatterns("/**").excludePathPatterns("/dd/**");
        super.addInterceptors(registry);
    }
}
