package basicTech.multiThread;

public class SleepWait {

    public class Service {
        public void mSleep() {

            synchronized (this) {
                System.out.println("Sleep, 当前时间：" + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                    this.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void mWait() {
            synchronized (this) {
                System.out.println("Wait, 结束时间" + System.currentTimeMillis());
                try {
                    this.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class SleepThread implements Runnable {
        private Service service;

        public SleepThread(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.mSleep();
        }
    }

    public class WaitThread implements Runnable {
        private Service service;

        public WaitThread(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.mWait();
        }
    }

    public static void main(String[] args) {
        SleepWait sleepWait = new SleepWait();
        Service service = sleepWait.new Service();
        Thread threadA = new Thread(sleepWait.new SleepThread(service));
        Thread threadB = new Thread(sleepWait.new WaitThread(service));
        threadB.start();
        threadA.start();


    }

}

