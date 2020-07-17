package com.luo.jdbc;

import basic.jdbc.JdbcUtils;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
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
    /**
     * jdbc实现方式4,默认加载模式
     */
    @Test
    public void testJdbc4() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        //加载类供Manager使用
        Class.forName("com.mysql.jdbc.Driver");
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);
        Connection connect = DriverManager.getConnection(url, info);
        System.out.println(connect);

    }

    /**
     * jdbc实现方式5,配置文件加载
     */
    @Test
    public void testJdbc5() throws Exception {
        InputStream io = JdbcTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(io);

        //加载类供Manager使用
        Class.forName(  properties.getProperty("jdbc.driverClass"));
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);
        Connection connect = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.user"),properties.getProperty("jdbc.password"));
        System.out.println(connect);

    }

    @Test
    public void testInsert() throws Exception {
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
    }
}
