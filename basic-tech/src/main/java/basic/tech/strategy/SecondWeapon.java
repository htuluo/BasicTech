package basic.tech.strategy;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SecondWeapon implements Weapon {
    @Override
    public void shooting() {
        System.out.println("shooting with bangbang");
    }
}
