package basic.tech.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ProxyTest2 implements InvocationHandler {
    private Object target;

    public ProxyTest2(Object target) {
        this.target = target;
    }

    public Object creatProxy(){
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("start invoke");
        return method.invoke(target, args);
    }

    public static void main(String[] args) {
        ProxyTest2 proxyTest2 = new ProxyTest2(new TestImpl());
        TestInterface o = (TestInterface)proxyTest2.creatProxy();
        System.out.println(o.method(0));
    }


}
