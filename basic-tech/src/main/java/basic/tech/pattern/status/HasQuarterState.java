package basic.tech.pattern.status;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/7
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class HasQuarterState implements State {
    GumballMachineNew gumballMachineNew;

    public HasQuarterState(GumballMachineNew gumballMachineNew) {
        this.gumballMachineNew = gumballMachineNew;
    }

    @Override
    public void insertQuarter() {
        System.out.println("您已投了一枚硬币，请勿重复投币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("您退回了一枚投币");
        gumballMachineNew.setState(gumballMachineNew.getNoQuarterState());

    }

    @Override
    public void turnCrank() {

        System.out.println("您扭动曲柄");
        gumballMachineNew.setState(gumballMachineNew.getSoldState());
    }

    @Override
    public void dispense() {

        System.out.println("不能释放球");
    }
}
