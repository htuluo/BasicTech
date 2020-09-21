package com.luo.jdbc;

import org.junit.Test;

/**
 * @author ： luolm
 * @date ：Created in 2020/9/17
 * @description： //TODO
 */
public class StringTest {
    @Test
    public void test1() {
        String str = "abc@123.com";
        int x = str.indexOf("@");
        System.out.println(str.substring(0, x));
        System.out.println(str.substring(x));
    }

    @Test
    public void test2() {
        Double d = Double.valueOf("0d");
        System.out.println(d == 0);
    }
}
