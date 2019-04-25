package basic.tech.pattern.factory;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza=createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String type);

}
