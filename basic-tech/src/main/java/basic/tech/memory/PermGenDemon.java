package basic.tech.memory;

import basic.tech.InitClass;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/1
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PermGenDemon {
    //VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
    //java 8 移除了永生代，改为-XX:MetaspaceSize 和-XX:MaxMetaspaceSize
    public static void main(String[] args) {

        int count=0;
        while (true){
            Enhancer enhancer=new Enhancer();
            enhancer.setSuperclass(InitClass.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            enhancer.create();
            System.out.println(++count);

        }

    }


    public class PermClass{
        String aStr;
        public PermClass() {
        }

        public String getaStr() {
            return aStr;
        }

        public void setaStr(String aStr) {
            this.aStr = aStr;
        }

        public PermClass(String aStr) {
            this.aStr = aStr;
        }
    }

}

