package basic.tech.pattern.strategy;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class FirstWeapon implements Weapon {
    @Override
    public void shooting() {
        System.out.println("使用AK-47射击");
    }
}
