package com.luo.jdbc;

import basic.jdbc.JdbcUtils;
import basic.jdbc.UserDao;
import basic.jdbc.UserDaoImpl;
import basic.jdbc.UserEntity;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class UserDaoImplTest {

    private UserDao dao = null;

    @Before
    public void before() {
        dao = new UserDaoImpl();
    }


    @Test
    public void testSelectById() {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            UserEntity userEntity = dao.selectById(connection, 1);
            System.out.println(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void testDeleteById() {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            int userEntity = dao.deleteById(connection, 1);
            System.out.println(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void testUpdate() {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            UserEntity userEntity = new UserEntity();
            userEntity.setId(1);
            userEntity.setPwd("bbt");
            userEntity.setUser("bml");
            int update = dao.update(connection, userEntity);
            System.out.println(update);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void testGetCount() {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            UserEntity userEntity1 = new UserEntity();

            Long count = dao.getCount(connection, userEntity1);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(connection, null);
        }
    }
}
