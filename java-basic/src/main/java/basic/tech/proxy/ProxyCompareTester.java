package basic.tech.proxy;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/3
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 * @see https://www.cnblogs.com/haiq/p/4304615.html
 */
public class ProxyCompareTester {
    public static void main(String[] args) {

        TestInterface nativeTest = new TestImpl();
        TestInterface decoratorTest = new DecoratorTest(nativeTest);
        TestInterface dynamicProxy=DynamicProxyTest.newProxyInstance(nativeTest);
        TestInterface cglibProxy=CglibProxyTest.newProxyInstance(TestImpl.class);


        int preRunCount=10000;
        runWithoutMonitor(nativeTest,preRunCount);
        runWithoutMonitor(decoratorTest,preRunCount);
        runWithoutMonitor(cglibProxy,preRunCount);
        runWithoutMonitor(dynamicProxy,preRunCount);

        Map<String,TestInterface> tests=new LinkedHashMap<String,TestInterface>();
        tests.put("Native      ",nativeTest);
        tests.put("Decorator   ",decoratorTest);
        tests.put("DynamicProxy",dynamicProxy);
        tests.put("CglibProxy  ",cglibProxy);

        int repeatCount=3;
        int runCount=1000000;
        runTest(repeatCount,runCount,tests);
        runCount=50000000;
        runTest(repeatCount,runCount,tests);

    }

    private static void  runWithoutMonitor(TestInterface test,int runCount){
        for (int i=0;i<runCount;i++){
            test.method(i);
        }
    }

    private static void  runTest(int repeatCount,int runCount,Map<String,TestInterface > test){
        System.out.println(String.format("========run test:[repeartCount=%s],[runCount=%s],[java.verson=%s]======",repeatCount,runCount,System.getProperty("java.version")));
        for (int i=0;i<repeatCount;i++){
            System.out.println(MessageFormat.format("----------test:{0}",(i+1)));
            for(String key:test.keySet()){
                runWithMonitor(test.get(key),runCount,key);
            }

        }
    }
    private static void  runWithMonitor(TestInterface test,int runCount,String tag){
        long start=System.currentTimeMillis();
        for (int i=0;i<runCount;i++){
            test.method(i);
        }
        long end=System.currentTimeMillis();
        System.out.println("["+tag+"] cost time:"+(end-start)+"ms;");
    }
}
