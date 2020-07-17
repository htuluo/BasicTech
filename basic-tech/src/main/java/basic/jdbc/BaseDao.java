package basic.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public abstract class BaseDao<T> {
    private Class<T> clazz = null;

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType typeName = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = typeName.getActualTypeArguments();
            clazz = (Class<T>) actualTypeArguments[0];
        }
    }

    /**
     * 更新
     *
     * @param sql
     * @param objects
     * @return
     */
    public int update(Connection connection, String sql, Object... objects) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                statement.setObject(i + 1, objects[i]);
            }
            return statement.executeUpdate();

        } catch (Exception e) {
            return 0;
        } finally {
            JdbcUtils.closeConnection(null, statement);
        }
    }

    /**
     * 泛型返回查询结果集
     *
     * @param connection
     * @param sql
     * @param objects
     * @return
     */
    public T selectOne(Connection connection, String sql, Object... objects) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                statement.setObject(i + 1, objects[i]);
            }
            resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            if (resultSet.next()) {
                T instance = clazz.newInstance();
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnName = resultSetMetaData.getColumnLabel(i + 1);
//                    String columnName = resultSetMetaData.getColumnName(i+1);
                    Field declaredField = clazz.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(instance, columnValue);
                }
                return instance;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JdbcUtils.closeConnection(null, statement, resultSet);

        }
        return null;
    }

    /**
     * 泛型返回查询结果集
     *
     * @param connection
     * @param sql
     * @param objects
     * @return
     */
    public <E> E selectResult(Connection connection, String sql, Object... objects) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                statement.setObject(i + 1, objects[i]);
            }
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return (E) resultSet.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JdbcUtils.closeConnection(null, statement, resultSet);
        }
        return null;
    }

    /**
     * 泛型返回查询结果集
     *
     * @param connection
     * @param sql
     * @param objects
     * @return
     */
    public List<T> selectList(Connection connection, String sql, Object... objects) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                statement.setObject(i + 1, objects[i]);
            }
            resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()) {
                T instance = clazz.newInstance();
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnName = resultSetMetaData.getColumnLabel(i + 1);
//                    String columnName = resultSetMetaData.getColumnName(i+1);
                    Field declaredField = clazz.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(instance, columnValue);
                }
                list.add(instance);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JdbcUtils.closeConnection(null, statement, resultSet);
        }
        return null;
    }
}
