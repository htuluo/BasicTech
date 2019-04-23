package basic.tech.pattern.decorator;

/**
 * @description: 装饰者模式
 * @author: luolm
 * @createTime： 2019/4/23
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public abstract class Beverage {
    String description="unkown Beverage";

    public String getDescription(){
        return description;
    }
    public abstract  double cost();
}
