package basic.tech.boxing;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/8
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class BoxingDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Long sum=0L;//换成long声明会快十倍左右的速度，因为有自动装箱
        for (long i = 0; i<Integer.MAX_VALUE; i++){
            sum+=i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("cost time ---"+(end-start)+"ms");
    }
}
