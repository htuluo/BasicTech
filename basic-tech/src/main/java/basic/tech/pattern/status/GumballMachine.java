package basic.tech.pattern.status;

import java.text.MessageFormat;

/**
 * @description: 糖果机状态模型
 * @author: luolm
 * @createTime： 2019/5/6
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class GumballMachine {
    final static int SOLD_OUT=0;
    final static int NO_QUARTER=1;
    final static int HAS_QUARTER=2;
    final static int SOLD=3;

    int state=SOLD_OUT;
    int count;

    public GumballMachine(int count) {
        this.count = count;
        if (count>0){
            state=NO_QUARTER;
        }
    }

    /**
     * 插入硬币
     */
    public void insertQuarter(){

        if(state==HAS_QUARTER){
            System.out.println("你不能再插入另一枚硬币");
        }else if (state==NO_QUARTER){
            state=HAS_QUARTER;
            System.out.println("插入一枚硬币");
        }  else if (state==SOLD){
            System.out.println("稍等，一个糖果正在售卖");
        }else if (state==SOLD_OUT){
            System.out.println("对不起，所有糖果被售出");
        }
    }
    /**
     * 退回硬币
     */
    public void ejectQuarter(){

        if(state==HAS_QUARTER){
            System.out.println("你退回一个硬币");
            state=NO_QUARTER;
        }else if (state==NO_QUARTER){
            System.out.println("你没有可退回的硬币");
        }  else if (state==SOLD){
            System.out.println("请稍等，已经退回硬币");
        }else if (state==SOLD_OUT){
            System.out.println("不能撤销，没投入硬币");
        }
    }
    /**
     * 扭动
     */
    public void turnCrank(){

        if(state==HAS_QUARTER){
            System.out.println("你扭了");
            state=SOLD;
            dispense();
        }else if (state==NO_QUARTER){
            System.out.println("扭动但没有硬币");
        }  else if (state==SOLD){
            System.out.println("不能重复扭动曲柄");
        }else if (state==SOLD_OUT){
            System.out.println("没有糖果了，不要扭曲柄了");
        }
    }
    /**
     * 发放
     */
    public void dispense(){

        if(state==HAS_QUARTER){
            System.out.println("没有糖果发放");
            state=SOLD;
        }else if (state==NO_QUARTER){
            System.out.println("请先付款");
        }  else if (state==SOLD){
            System.out.println("正在售出");
            count-=1;
            if (count==0){
                System.out.println("糖果售罄");
                state=SOLD_OUT;
            }else {
                state=NO_QUARTER;
            }
        }else if (state==SOLD_OUT){
            System.out.println("没有糖果发放");
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("state:{0},count:{1}",this.state,this.count);
    }
}
