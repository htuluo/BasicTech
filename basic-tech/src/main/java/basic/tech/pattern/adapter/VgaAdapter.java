package basic.tech.pattern.adapter;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class VgaAdapter extends Adapter {
    @Override
    public void typec(){
        System.out.println("信息从Typec口的手机输出。");
    }

    @Override
    public void typecToVga() {
        super.typecToVga();
        System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
        System.out.println("信息已转换成VGA接口，显示屏可以对接。");
    }

    @Override
    public void typecToHdmi() {

    }

}
