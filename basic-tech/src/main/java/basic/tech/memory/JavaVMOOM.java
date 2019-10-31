package basic.tech.memory;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.misc.VM;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/1
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class JavaVMOOM {
    /**
     * 静态内部类，用于Metaspace的oom
     */
    static class StaticClass{}

    public static void main(String[] args) {

        metaSpaceTest(args);
        return;
    }

    /**
     * java.lang.OutOfMemoryError: Metaspace 演示
     *-Xms10M -Xmx10M -Xss128k -XX:+PrintGCDetails -XX:+UseGCOverheadLimit -XX:MaxMetaspaceSize=5M
     * @param args
     * 其中vm 参数：MaxMetaspaceSize
     */
    private static void metaSpaceTest(String[] args) {
        try {
            while (true) {
                Enhancer enhancer=new Enhancer();
                enhancer.setSuperclass(StaticClass.class);
                enhancer.setUseCache(true);
                //lamda表达式
                // enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> method.invoke(o,args));
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return method.invoke(o,args);
                    }
                });
                enhancer.create();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * java.lang.OutOfMemoryError: Direct buffer memory
     * -Xms10M -Xmx10M -Xss128k -XX:+PrintGCDetails -XX:+UseGCOverheadLimit -XX:MaxDirectMemorySize=5M
     */
    private static void directBufferErrorTest() {
        try {

            //-XX:MaxDirectMemorySize=5M
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
//            char[] chars = new char[];
            System.out.println("当前分配的最大内存：" + VM.maxDirectMemory() / 1024 / 1024 + "MB");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * -Xmx10M -Xss128k -XX:+PrintGCDetails -XX:+UseGCOverheadLimit -XX:+UseParallelGC
     * UseParallelGC收集器才报，GC overhead limit exceeded
     */
    private static void gcOverheadLimitTest() {
        String str = "aadl";
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {

//                list.add(str.intern());// java.lang.OutOfMemoryError: Java heap space
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(i);
            throw (e);
        } finally {
        }
    }


    /**
     * java.lang.OutOfMemoryError: Java heap space
     * VM args  -Xms20M -Xmx20M -Xss256k -XX:+PrintGCDetails
     * 堆内存溢出
     */
    private static void VmStackOOMDemo() {
        int count = 0;
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String[] strings = new String[1024 * 1024];
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            thread.start();
            System.out.println(++count);
        }
    }
}
