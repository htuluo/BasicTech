package com.luo.jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class JdbcTest {
    /**
     * jdbc实现方式1
     */
    @Test
    public void testJdbc() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");


        Connection connect = driver.connect(url, info);
        System.out.println(connect);

    }


    /**
     * jdbc实现方式2
     */
    @Test
    public void testJdbc2() throws Exception {
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");


        Connection connect = driver.connect(url, info);
        System.out.println(connect);

    }

    /**
     * jdbc实现方式3
     */
    @Test
    public void testJdbc3() throws Exception {
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        DriverManager.registerDriver(driver);
        Connection connect = DriverManager.getConnection(url, info);
        System.out.println(connect);

    }
}
