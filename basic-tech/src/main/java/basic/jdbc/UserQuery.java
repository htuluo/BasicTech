package basic.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class UserQuery {
    /**
     * select操作
     *
     * @param sql
     * @param objects
     * @return
     */
    public UserEntity selectQuery(String sql, Object... objects) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                statement.setObject(i + 1, objects[i]);
            }
            resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            if (resultSet.next()) {
                UserEntity userEntity = new UserEntity();
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnName = resultSetMetaData.getColumnLabel(i + 1);
//                    String columnName = resultSetMetaData.getColumnName(i+1);
                    Field declaredField = UserEntity.class.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(userEntity, columnValue);
                }
                return userEntity;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JdbcUtils.closeConnection(connection, statement, resultSet);
        }
        return null;
    }
}
