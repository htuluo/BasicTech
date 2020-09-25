package basic.tech.pattern.status;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/7
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SoldOutState implements State {
    GumballMachineNew gumballMachineNew;

    public SoldOutState(GumballMachineNew gumballMachineNew) {
        this.gumballMachineNew = gumballMachineNew;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已售罄");
        ejectQuarter();
        gumballMachineNew.setState(gumballMachineNew.getSoldOutState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("售罄退币");

    }

    @Override
    public void turnCrank() {

        System.out.println("已售罄，不能扭动曲柄");
    }

    @Override
    public void dispense() {

        System.out.println("已售罄，不能释放糖果");
    }
}
