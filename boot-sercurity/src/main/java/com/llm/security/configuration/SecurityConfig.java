package com.llm.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.formLogin()
//                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()  // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login.html").permitAll() // 设置所有人都可以访问登录页面
                .anyRequest() // 任何请求,登录后可以访问
                .authenticated()
                .and().csrf().disable(); // 关闭csrf防护
    }
}
