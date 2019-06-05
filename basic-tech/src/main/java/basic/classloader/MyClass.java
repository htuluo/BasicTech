package basic.classloader;

import java.io.Serializable;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/6/3
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class MyClass<T> implements Cloneable,Serializable {
    private int id=1;
    private String name="Unknown";

    public MyClass() {
    }

    public MyClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "MyClass: id="+this.id+", name="+this.name;
    }

}


