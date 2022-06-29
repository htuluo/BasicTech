package com.luo.test;

import basic.tech.inherit.Child2Class;
import basic.tech.inherit.ParentClass;
import org.junit.Test;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/11/17
 * @description： //TODO
 */
public class ParentChildClassTest {
    @Test
    public void test1(){
        ParentClass childClass=new Child2Class();
        if (childClass instanceof Child2Class){
            ((Child2Class)childClass).setChild2Int(2);
            System.out.println("----"+true);
            System.out.println("----"+((Child2Class)childClass).getChild2Int());
        }
    }
}
