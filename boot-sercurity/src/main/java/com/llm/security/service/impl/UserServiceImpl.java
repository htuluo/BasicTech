package com.llm.security.service.impl;

import com.llm.security.Dao.RoleDao;
import com.llm.security.Dao.UserDao;
import com.llm.security.configuration.PasswordEncoderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.List;

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

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("用户名：{}", username);
        com.llm.security.Model.User user = this.userDao.findByName(username);

        if (user==null){
            return  new com.llm.security.Model.User();
        }
        //查询用户的角色信息，并返回存入user中
        List<Role> roles = roleDao.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
//        User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
