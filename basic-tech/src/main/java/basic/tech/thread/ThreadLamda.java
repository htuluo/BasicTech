package basic.tech.thread;

import java.util.concurrent.TimeUnit;

public class ThreadLamda {
    public static void main(String[] args) throws InterruptedException {
        String param = "dddddddddddddd";
        Integer ab = 220;
        TimeUnit.SECONDS.sleep(30);
        Thread thread = new Thread(() -> {
            System.out.println("ddd" + param + ab);
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            String param="ddd";
        },"aaa");
        thread.start();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //ab+=1;不能对变量进行更改，需要final的变量
                String param = "9999999999999";
                System.out.println(param + " " + ab);
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bbb");

        thread1.start();

    }
}
