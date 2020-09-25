package basic.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
            MyClass aInstance = constructor.newInstance(2, "abc");
            System.out.println(aInstance);
            MyClass myClass1 = myClass.newInstance();
            Method myClassMethod = myClass.getMethod("setName", String.class);
            myClassMethod.invoke(myClass1,"dds");
            System.out.println(myClass1);

            Field name = myClass.getField("pubField");
            name.set(aInstance,"filedName");
            System.out.println(aInstance);
            Field declaredField = myClass.getDeclaredField("name");
            declaredField.setAccessible(true);
            declaredField.set(aInstance,"otherName");
            System.out.println(aInstance);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
