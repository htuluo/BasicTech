package basic.tech.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Subject implements Observerable {
    private List<Observer> list;
    private String subPropty;

    public Subject() {
        this.list=new ArrayList<>();
    }

    public String getSubPropty() {
        return subPropty;
    }

    public void setSubPropty(String subPropty) {
        this.subPropty = subPropty;
        System.out.println("更新了被观察者的数据："+subPropty);
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer o) {

        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {

        list.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (int i=0;i<list.size();i++){
            Observer observer = list.get(i);
            observer.update(subPropty);
        }

    }
}
