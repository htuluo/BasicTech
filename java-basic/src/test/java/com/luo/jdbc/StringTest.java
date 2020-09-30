package com.luo.jdbc;

import org.junit.Test;

import java.util.*;

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

    @Test
    public void test3() {
        System.out.println(true^false);
        System.out.println(true^true);
        System.out.println(false^false);
        System.out.println(false^true);
        Set<String> a=new HashSet<>();
        a.add("aaa");
        a.add("bbb");
        a.add("ccc");
        a.add("ddd");
        Set<String> b=new HashSet<>(a);
        a.remove("aaa");
        System.out.println(Arrays.asList(b).toString());
    }
}
