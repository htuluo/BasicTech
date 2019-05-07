package basic.tech.pattern.status;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/7
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
