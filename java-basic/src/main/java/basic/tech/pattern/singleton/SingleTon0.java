package basic.tech.pattern.singleton;

/**
 * @description: 普通，不安全
 * @author: luolm
 * @createTime： 2019/7/4
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SingleTon0 {
    private static SingleTon0 singleTon0=new SingleTon0();
    public static SingleTon0 getInstance(){
        return singleTon0;
    }
}
