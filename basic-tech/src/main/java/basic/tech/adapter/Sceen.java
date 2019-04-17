package basic.tech.adapter;

/**
 * @description: 适配器模式demo
 * @author: luolm
 * @createTime： 2019/4/17
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Sceen {
    public static void main(String[] args) {
        System.out.println("第一种适配器");
        TypecToVga1 vga1 = new TypecToVga1();
        vga1.vgaInterface();
        System.out.println("显示屏对接适配器，手机成功投影到显示屏");

        System.out.println("\r\n第二种适配器");
        TypecToVga2 vga2 = new TypecToVga2(new Phone());
        vga2.vgaInterface();
        System.out.println("显示屏对接适配器，手机成功投影到显示屏");

        //第三种适配器用法
        System.out.println("\r\n-------------第三种适配器------------");
        VgaAdapter vgaAdapter = new VgaAdapter();
        vgaAdapter.typec();
        vgaAdapter.typecToVga();//适配器将typec转换成vga
        System.out.println("显示屏对接适配器，手机成功投影到显示屏!");
    }
}
