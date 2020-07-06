package basic.classloader;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //五中类主动加载 的形式
        //1,new 对象
//        SimpleDemo simpleDemo = new SimpleDemo();
        //2. 静态变量
//        System.out.println(SimpleDemo.a);
//
//        //3. 静态方法
//        System.out.println(SimpleDemo.say());

        //4反射,必须是全限定名
//        Class<SimpleDemo> simpleDemo = (Class<SimpleDemo>) Class.forName("basic.classloader.SimpleDemo");
//        SimpleDemo simpleDemo1 = simpleDemo.newInstance();



        //-------常量变量不能实例化对象
        System.out.println(SimpleDemo.b);

        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
    }
}
