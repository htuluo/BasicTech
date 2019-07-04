package basic.tech.pattern.singleton;

/**
 * @description: 静态内部类实现，线程安全
 * @author: luolm
 * @createTime： 2019/7/4
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SingleTon3 {
    private SingleTon3() {
    }

    public static SingleTon3 getInstance(){
        return InnerHolder.singleTon3;
    }

    private static  class InnerHolder{
        private static SingleTon3 singleTon3=new SingleTon3();
    }
}
