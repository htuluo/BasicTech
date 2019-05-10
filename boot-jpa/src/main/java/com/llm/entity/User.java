package com.llm.entity;

import com.llm.common.Sex;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Entity
@Data
public class User extends AbstractPersistable<Long> {
    private String name;
    private Integer age;
    private String addressCode;

    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    public User() {
    }

    public User(String name, Integer age, String addressCode, Sex sex) {
        this.name = name;
        this.age = age;
        this.addressCode = addressCode;
        this.sex = sex;
    }
}
