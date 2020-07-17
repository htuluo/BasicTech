package basic.jdbc;

import java.sql.Connection;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public interface UserDao {
    public UserEntity selectById(Connection connection,int id);
    int deleteById(Connection connection,int id);
    int update(Connection connection,UserEntity userEntity);
    Long getCount(Connection connection,UserEntity userEntity);
}
