package basic.tech.pattern.factory;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ChicagoStyleCheesePizza extends  Pizza {
    public ChicagoStyleCheesePizza() {
        this.name="Chicago style deep dish and cheese Pizza";
        this.dough="Extra thick crust dough";
        this.source="plum tomato sauce";
        topping.add("Shredded Mozzarella cheese");
    }
    @Override
    public void cut(){
        System.out.println("Cutting the pizza into square slices");
    }
}
