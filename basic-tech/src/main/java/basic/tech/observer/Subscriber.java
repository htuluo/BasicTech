package basic.tech.observer;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Subscriber implements Observer {
    private String name;
    private String msg;

    public Subscriber(String name) {
        this.name = name;
    }



    @Override
    public void update(Object msg) {

        this.msg=(String) msg;
        read();
    }

    public void  read(){
        System.out.println(this.name+":收到推送消息："+msg);
    }

    public static void main(String[] args) {
        Subscriber subscriber = new Subscriber("张三");
        Subscriber subscriber2= new Subscriber("李四");
        Subscriber subscriber3 = new Subscriber("王五");

        Subject subject = new Subject();
        subject.registerObserver(subscriber);
        subject.registerObserver(subscriber2);
        subject.registerObserver(subscriber3);
        subject.setSubPropty("year");
        subject.removeObserver(subscriber3);
        subject.setSubPropty("OK");

    }
}
