package com.llm.common;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public enum Sex {
    male(0),
    female(1);
    private int sex;

    Sex(int sex) {
        this.sex = sex;
    }
}
