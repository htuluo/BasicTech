package basic.tech.pattern.factory;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")){
            return new NYStyleCheesePizza();
        }else if (type.equals("veggie")){
            return new NYStyleVeggiePizza();
        }else if (type.equals("clam")){
            return new NYStyleClamPizza();
        }
        return null;
    }
}
