package basic.tech.pattern.template;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/5
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
}
