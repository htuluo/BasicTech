package basic.tech.pattern.singleton;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/26
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SingleTon {
    private static SingleTon singleTon;
    private SingleTon(){}

    public static SingleTon getSingleTon() {
        if (singleTon==null){
            singleTon=new SingleTon();
        }
        return singleTon;
    }
}
