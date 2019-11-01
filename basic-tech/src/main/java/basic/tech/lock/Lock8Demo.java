package basic.tech.lock;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/11/1
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Lock8Demo {

    /**
     * 1、同一个对象，标准访问，两个线程分别访问两个synchronized方法
     * 2、同上，其中一个方法暂停几秒，
     * 3、同上，普通方法，先打印普通方法还是发送邮件
     * 4、两部手机，先打印哪个
     * 5、两个静态同步方法，同一部手机，先打印哪个
     * 6、两个静态同步方法，两部手机，先打印哪个
     * 7、一个静态同步方法，一个普通同步方法，同一部手机，先打印哪个
     * 8、一个静态同步方法，一个普通同步方法，两部手机，先打印哪个
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        new Thread(() -> {

            phone.sendSms();
        }, "BB").start();
    }
}

class Phone {

    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("========sendEmail");

    }

    public synchronized void sendSms() {
        System.out.println("========sendSms");
    }

    public void method() {
        System.out.println("========method");

    }
}
