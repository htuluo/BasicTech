package com.llm.Dao;

import com.llm.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public interface UserRepository extends CrudRepository<User,Long>,UserCustomRepository {
}
