package basic.tech.classinit;

/**
 * @description: 演示类的实例化顺序
 * @author: luolm
 * @createTime： 2019/10/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class InitClass2 {
    private int i = 0;
    private int j = i;

    public InitClass2(int k) {
        System.out.println(i);
        System.out.println(j);
        this.i = k;
        System.out.println(i);
        System.out.println(j);
    }

    {
        j += 2;
    }

    public static void main(String[] args) {
        new InitClass2(3);
    }

}
