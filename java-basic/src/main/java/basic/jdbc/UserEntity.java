package basic.jdbc;

import java.util.Date;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 *
 *
 *
 *
 * CREATE TABLE `user` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `user` varchar(20) DEFAULT NULL,
 *   `pwd` varchar(50) DEFAULT NULL,
 *   `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
 */
public class UserEntity {
    private Integer id;
    private String user;
    private String pwd;

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public UserEntity() {
    }

    public UserEntity(Integer id, String user, String pwd, Date createDate) {
        this.id = id;
        this.user = user;
        this.pwd = pwd;
        this.createDate = createDate;
    }

    public UserEntity(Integer id, String user, String pwd) {
        this.id = id;
        this.user = user;
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
