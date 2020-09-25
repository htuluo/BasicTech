package basic.jdbc;

import java.sql.Connection;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class UserDaoImpl extends BaseDao<UserEntity> implements UserDao {
    @Override
    public UserEntity selectById(Connection connection, int id) {
        String sql = "select id,user,pwd,create_date createDate from user where id=?";
        UserEntity userEntity = selectOne(connection, sql, id);
        return userEntity;
    }

    @Override
    public int deleteById(Connection connection, int id) {
        String sql = "delete from user where id=?";
        int update = update(connection, sql, id);
        return update;
    }

    @Override
    public int update(Connection connection, UserEntity userEntity) {
        String sql = "update  user set user=?,pwd=? where id=?";
        int update = update(connection, sql, userEntity.getUser(), userEntity.getPwd(), userEntity.getId());
        return update;
    }

    @Override
    public Long getCount(Connection connection, UserEntity userEntity) {
        String sql = "select count(*) from user ";
        Long selectResult = selectResult(connection, sql);
        return selectResult;
    }
}
