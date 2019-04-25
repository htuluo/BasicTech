package basic.tech.pattern.factory;

import javax.sound.midi.Soundbank;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan order a "+pizza.getName()+"\n");
        pizza=chicagoPizzaStore.orderPizza("clam");
        System.out.println("Joel order a "+pizza.getName()+"\n");
    }
}
