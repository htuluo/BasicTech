package com.llm.security.service.impl;

import com.llm.security.configuration.PasswordEncoderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Component
@Slf4j
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoderImpl passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("用户名：{}", username);
        String password=passwordEncoder.encode("123456");

        User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
