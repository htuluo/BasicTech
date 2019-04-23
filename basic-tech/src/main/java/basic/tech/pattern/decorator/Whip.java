package basic.tech.pattern.decorator;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/23
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Whip extends CondimentDecorator{
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Whip";
    }

    @Override
    public double cost() {
        return 0.35+beverage.cost();
    }
}
