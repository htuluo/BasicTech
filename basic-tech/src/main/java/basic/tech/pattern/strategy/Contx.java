package basic.tech.pattern.strategy;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Contx {
    Weapon weapon;

    public Contx(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void shooting(){
        weapon.shooting();
    }

    public static void main(String[] args) {
        Contx contx = new Contx(new FirstWeapon());
        contx.shooting();
        contx.setWeapon(new SecondWeapon());
        contx.shooting();
    }

}
