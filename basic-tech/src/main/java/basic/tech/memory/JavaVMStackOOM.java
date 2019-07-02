package basic.tech.memory;


/**
 * @description: VM args  -Xms20M -Xmx20M -Xss256k
 * @author: luolm
 * @createTime： 2019/7/1
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class JavaVMStackOOM {
    public static void main(String[] args) {
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
