package basic.tech.reference;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/1/4
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */

public class DetailClass {
    int anInt;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public  void process(DetailClass detailClass){
        detailClass.setAnInt(10);
    }

    public static void main(String[] args) {
        DetailClass detailClass=new DetailClass();
        detailClass.setAnInt(1);
        detailClass.process(detailClass);
        System.out.println(detailClass.getAnInt());
    }


}
