package basic.thread;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/7/29
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ShutDownHookTest {

    public static void main(String[] args) {

        boolean flag = false;

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("hook execute -------");
            }));

        while (flag) {

            // app is runing

        }


        System.out.println("main thread execute end...");
    }
}
