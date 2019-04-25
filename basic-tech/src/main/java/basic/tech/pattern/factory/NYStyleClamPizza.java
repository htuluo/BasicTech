package basic.tech.pattern.factory;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class NYStyleClamPizza extends  Pizza {
    public NYStyleClamPizza() {
        this.name="NY style Sauce and Clam Pizza";
        this.dough="Thin Crust dough";
        this.source="Marinara Sauce";
        topping.add("Grated Reggiano Clam");
    }
}
