package basic.tech.pattern.singleton;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/26
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SingleTon2 {
    private volatile static SingleTon2 singleTon;

    private SingleTon2() {
    }

    public static SingleTon2 getSingleTon() {
        if (singleTon == null) {
            synchronized (SingleTon2.class) {
                if (singleTon == null) {

                    singleTon = new SingleTon2();
                }
            }
        }
        return singleTon;
    }
}
