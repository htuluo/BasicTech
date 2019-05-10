package com.llm.Dao;

import com.llm.common.Sex;
import com.llm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public interface UserCustomRepository {
    Page<User> search(Integer age, String addressCode, Sex sex, Pageable pageRequest);
}
