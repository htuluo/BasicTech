package com.llm.security.Dao;

import com.llm.security.Model.User;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Repository
public interface UserDao {
    User findByName(String username);
}
