package basic.tech.adapter;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TypecToVga1 extends Phone implements Vga {
    @Override
    public void vgaInterface() {
        typecPhone();
        System.out.println("接收Type-c信息，转换成Vga接口中");
        System.out.println("信息已转换成VGA接口，可以对接");
    }
}
