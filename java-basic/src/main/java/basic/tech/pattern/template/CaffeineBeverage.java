package basic.tech.pattern.template;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/5
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public abstract class CaffeineBeverage {
   public final void prepareRecipe(){

        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
    abstract void brew();
    abstract void addCondiments();


    void boilWater(){
        System.out.println("Boiling water");
    }

    void pourInCup(){
        System.out.println("Pouring into cup");
    }
}
