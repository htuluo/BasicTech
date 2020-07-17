package basic.jdbc;

import org.apache.tools.ant.taskdefs.Classloader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class JdbcUtils {
    /**
     * 获取连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(stream);
        //加载类供Manager使用
        Class.forName(properties.getProperty("jdbc.driverClass"));
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);
        Connection connect = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.user"), properties.getProperty("jdbc.password"));
        return connect;
    }

    /**
     * 关闭连接
     * @param connection
     * @param statement
     */
    public static void closeConnection(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {

        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }
}
