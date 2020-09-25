package basic.tech.pattern.status;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/7
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SoldState implements State {
    GumballMachineNew gumballMachineNew;

    public SoldState(GumballMachineNew gumballMachineNew) {
        this.gumballMachineNew = gumballMachineNew;
    }

    @Override
    public void insertQuarter() {
        System.out.println("请稍等，正在出货");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("对不起，你已经扭动曲柄，不能退币");

    }

    @Override
    public void turnCrank() {

        System.out.println("不能重复扭动曲柄");
    }

    @Override
    public void dispense() {

        gumballMachineNew.releaseBall();
        if (gumballMachineNew.getCount()>0){
            gumballMachineNew.setState(gumballMachineNew.getNoQuarterState());
        }else {
            System.out.println("ooo,没有气球了");
            gumballMachineNew.setState(gumballMachineNew.getSoldOutState());
        }
    }
}
