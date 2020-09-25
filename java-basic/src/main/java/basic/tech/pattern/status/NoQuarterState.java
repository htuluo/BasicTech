package basic.tech.pattern.status;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/7
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class NoQuarterState implements State {
    GumballMachineNew gumballMachineNew;

    public NoQuarterState(GumballMachineNew gumballMachineNew) {
        this.gumballMachineNew = gumballMachineNew;
    }

    @Override
    public void insertQuarter() {
        System.out.println("投了一枚硬币");
        gumballMachineNew.setState(gumballMachineNew.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("您还未投币");

    }

    @Override
    public void turnCrank() {

        System.out.println("您扭动曲柄，但没有投币");
    }

    @Override
    public void dispense() {

        System.out.println("请先投币");
    }
}
