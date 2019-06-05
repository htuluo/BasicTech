package basic.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/6/5
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ClassReflection {
    public static void main(String[] args) {
        Class<MyClass> myClass=MyClass.class;
        try {
            Constructor<MyClass> constructor = myClass.getConstructor(int.class, String.class);
            MyClass abc = constructor.newInstance(2, "abc");
            System.out.println(abc);
            MyClass myClass1 = myClass.newInstance();
            Method myClassMethod = myClass.getMethod("setName", String.class);
            myClassMethod.invoke(myClass1,"dds");
            System.out.println(myClass1);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
