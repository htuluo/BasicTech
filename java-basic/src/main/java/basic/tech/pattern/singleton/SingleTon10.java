package basic.tech.pattern.singleton;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/26
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SingleTon10 {
    private static SingleTon10 singleTon;
    private SingleTon10(){}

    public static synchronized SingleTon10 getSingleTon() {
        if (singleTon==null){
            singleTon=new SingleTon10();
        }
        return singleTon;
    }
}
