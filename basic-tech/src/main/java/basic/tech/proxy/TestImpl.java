package basic.tech.proxy;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/3
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TestImpl implements TestInterface {
    @Override
    public int method(int i) {
        return i+1;
    }
}
