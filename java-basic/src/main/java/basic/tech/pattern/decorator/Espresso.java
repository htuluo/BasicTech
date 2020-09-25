package basic.tech.pattern.decorator;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/23
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Espresso extends  Beverage {

    public Espresso() {
        description="Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
