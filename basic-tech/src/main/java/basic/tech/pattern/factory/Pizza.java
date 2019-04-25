package basic.tech.pattern.factory;

import java.util.ArrayList;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public abstract class Pizza {
    protected String name;
    protected String dough;
    String source;

    ArrayList topping=new ArrayList<>();

    public void prepare(){
        System.out.println("prepareing "+name);
    }
    public void bake(){
        System.out.println("baking "+name);
    }
    public void cut(){
        System.out.println("Cutting the pizza ");
    }
    public void box(){
        System.out.println("Place pizza in official Box");
    }

    public String getName() {
        return name;
    }
}
