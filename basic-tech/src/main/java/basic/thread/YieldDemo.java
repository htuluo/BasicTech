package basic.thread;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/11/8
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class YieldDemo {
    public static void main(String[] args) throws InterruptedException {


        Thread threadA = new Thread(() -> {
            try {
                System.out.println("AA");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA");
        Thread threadB = new Thread(() -> {
            try {
                System.out.println("BB");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB");
        threadB.start();
        threadA.start();

        for (int i=0;i<10;i++){

            if (i%2==0){
                threadA.run();
                threadB.join();
                threadA.interrupt();
            }else {
                threadB.run();
                threadA.join();

            }
        }
    }
}
