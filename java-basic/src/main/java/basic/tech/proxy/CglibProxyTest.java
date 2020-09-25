package basic.tech.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/3
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class CglibProxyTest implements MethodInterceptor {
    private CglibProxyTest(){}
    public static <T extends TestInterface> TestInterface newProxyInstance(Class<T> targetInstanceClazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetInstanceClazz);
        enhancer.setCallback(new CglibProxyTest());
        return (TestInterface) enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        return methodProxy.invokeSuper(o,objects);
    }
}
