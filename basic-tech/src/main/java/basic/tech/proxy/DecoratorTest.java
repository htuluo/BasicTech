package basic.tech.proxy;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/3
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class DecoratorTest implements TestInterface {
    private  TestInterface target;

    public DecoratorTest(TestInterface target) {
        this.target = target;
    }

    @Override
    public int method(int i){
        return  this.target.method(i);
    }
}
