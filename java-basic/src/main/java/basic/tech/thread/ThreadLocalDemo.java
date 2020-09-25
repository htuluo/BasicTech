package basic.tech.thread;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ThreadLocalDemo {
    static class DemoClass {
        private ThreadLocal<Integer> local = new ThreadLocal<>();

        public DemoClass() {
            this.local.set(1);
        }

        public void addLocal() {
            if (this.local.get() == null) {
                this.local.set(0);
            } else {
                this.local.set(this.local.get() + 1);
            }
        }

        public Integer getLocal() {
            return this.local.get();
        }

    }

    public static void main(String[] args) {
        test2();

    }

    public static void test2() {
        DemoClass demoClass = new DemoClass();
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    demoClass.addLocal();
                }
                System.out.println(Thread.currentThread().getName() + "  " + demoClass.getLocal());
            }, "thread" + String.valueOf(i));

            threadArray[i] = thread;
            thread.start();
        }
        for (int i = 0; i < 10; i++) {

            try {
                threadArray[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(demoClass.getLocal());
    }

    public static void test1() {
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                DemoClass demoClass = new DemoClass();
                for (int j = 0; j < 10; j++) {
                    demoClass.addLocal();
                }
                System.out.println(Thread.currentThread().getName() + "  " + demoClass.getLocal());
            }, "thread" + String.valueOf(i));

            threadArray[i] = thread;
            thread.start();
        }
        for (int i = 0; i < 10; i++) {

            try {
                threadArray[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
