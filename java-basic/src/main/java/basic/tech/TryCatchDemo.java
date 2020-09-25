package basic.tech;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/5
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TryCatchDemo {
    public static void main(String[] args) {

        System.out.println(tryReturn());
    }

    private static int tryReturn() {
        int i = 0;
        try {
            i=1;
//            if (1==1){
//                throw  new  RuntimeException("aaa");
//            }
            return i;
        }
        catch (Exception e){
            i=2;
            return i;

        }finally {
            i=4;
        }
    }
}
