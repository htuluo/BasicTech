package basicTech.serialize;

import java.io.Serializable;

/**
 * @description: 序列化类
 * @author: luolm
 * @createTime： 2018/10/24
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SerializeClass implements Serializable{
    private final static long serialVersionUID=1L;
    private String str0;
    private  transient String str1;
    private static String str2="abc";

    public static String getStr2() {
        return str2;
    }

    public SerializeClass(String str0, String str1) {
        this.str0 = str0;
        this.str1 = str1;
    }

    public String getStr0() {
        return str0;
    }

    public void setStr0(String str0) {
        this.str0 = str0;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }
}
