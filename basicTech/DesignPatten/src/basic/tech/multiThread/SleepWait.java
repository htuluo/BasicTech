package basic.tech.multiThread;

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

    public class SleepRunnable implements Runnable {
        private Service service;

        public SleepRunnable(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.mSleep();
        }
    }

    public class SleepThread extends Thread{
        private Service service;

        public Service getService() {
            return service;
        }

        public void setService(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
//            super.run();
            synchronized (this.service) {
                System.out.println("thread run,当前时间："+System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class WaitRunnable implements Runnable {
        private Service service;

        public WaitRunnable(Service service) {
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
        Thread threadA = new Thread(sleepWait.new SleepRunnable(service));
        Thread threadB = new Thread(sleepWait.new WaitRunnable(service));
        SleepThread threadC =  sleepWait.new SleepThread();
        threadC.setService(service);
        threadA.start();
        threadB.start();
        threadC.start();


    }

}

