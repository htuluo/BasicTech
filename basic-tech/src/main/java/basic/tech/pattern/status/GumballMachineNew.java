package basic.tech.pattern.status;

import java.text.MessageFormat;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/7
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class GumballMachineNew {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;

    State state=soldOutState;
    int count=0;

    public GumballMachineNew(int count) {
        soldOutState=new SoldOutState(this);
        hasQuarterState=new HasQuarterState(this);
        noQuarterState=new NoQuarterState(this);
        soldState=new SoldState(this);
        this.count = count;
        if (count>0){
            state=noQuarterState;
        }
    }
    public void  insertQuarter(){
        state.insertQuarter();
    }
    public void ejectQuarter(){
        state.ejectQuarter();
    }
    public void turnCrank(){
        state.turnCrank();
        state.dispense();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    void releaseBall(){
        System.out.println("正在释放糖果");
        if (count!=0){
            count--;
        }
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return MessageFormat.format("state:{0},count-{1}",this.getState(),this.count);
    }
}
