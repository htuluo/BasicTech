package com.llm.security.Dao;

import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Repository
public interface RoleDao {
    List<Role> getRolesByUid(Long id);
}
