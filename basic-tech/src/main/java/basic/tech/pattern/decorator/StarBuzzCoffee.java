package basic.tech.pattern.decorator;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/23
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class StarBuzzCoffee {
    public static void main(String[] args) {
//        Beverage beverage = new Beverage();
//        System.out.println(beverage.description);

        Beverage beverage2 = new HouseBlend();
        beverage2=new Soy(beverage2);
        beverage2=new Mocha(beverage2);
        beverage2=new Whip(beverage2);
        System.out.println(beverage2.getDescription()+" $"+beverage2.cost());
    }
}
