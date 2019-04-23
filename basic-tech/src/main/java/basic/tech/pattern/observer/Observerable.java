package basic.tech.pattern.observer;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public interface Observerable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();

}
