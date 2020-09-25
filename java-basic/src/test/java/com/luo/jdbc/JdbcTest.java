package com.luo.jdbc;

import basic.jdbc.JdbcUtils;
import basic.jdbc.UserEntity;
import basic.jdbc.UserQuery;
import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
        Class.forName(properties.getProperty("jdbc.driverClass"));
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);
        Connection connect = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.user"), properties.getProperty("jdbc.password"));
        System.out.println(connect);

    }

    @Test
    public void testInsert() throws Exception {
        Connection connection = JdbcUtils.getConnection();
        String sql = "insert user(user,pwd)values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "zhangsan");
        preparedStatement.setString(2, "11111");
        preparedStatement.execute();
        JdbcUtils.closeConnection(connection, preparedStatement);
        System.out.println(connection);
    }

    @Test
    public void testUpdate() throws Exception {
        Connection connection = JdbcUtils.getConnection();
        String sql = "update user set pwd=? where user=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "aaa");
        preparedStatement.setString(2, "zhangsan");
        preparedStatement.execute();
        JdbcUtils.closeConnection(connection, preparedStatement);
        System.out.println(connection);
    }

    @Test
    public void testUpdate2() throws Exception {
        String sql = "update user set pwd=? where user=?";
        int update = JdbcUtils.update(sql, "bbb", "zhangsan");
        System.out.println("update result---" + update);
    }

    @Test
    public void testSelectUser() throws Exception {
        String sql = "select id,user,pwd,create_date as createDate from user where user=?";
        UserQuery userQuery = new UserQuery();
        UserEntity userEntity = userQuery.selectQuery(sql, "zhangsan");
        System.out.println("select result---" + userEntity);
    }

    @Test
    public void testSelectUser1() throws Exception {
        String sql = "select id,user,pwd,create_date as createDate from user where user=?";
        UserEntity userEntity = JdbcUtils.selectQuery(UserEntity.class, sql, "zhangsan");
        System.out.println("select result---" + userEntity);
    }

    @Test
    public void testBatchInsert1() throws Exception {
        String sql = "insert user(user,pwd)values(?,?)";
        long l = System.currentTimeMillis();
        Stopwatch started = Stopwatch.createStarted();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            statement.setObject(1, "user" + i);
            statement.setObject(2, "pwd" + i);
            statement.addBatch();
            if (i%500==0){
                statement.executeBatch();
                statement.clearBatch();
            }
        }
        int[] ints = statement.executeBatch();
        connection.commit();
        JdbcUtils.closeConnection(connection, statement);

        System.out.println("batch result---cost:" + started.elapsed());
    }
}
