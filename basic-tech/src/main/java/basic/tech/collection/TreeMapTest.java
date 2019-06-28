package basic.tech.collection;

import java.util.Map;
import java.util.TreeMap;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/6/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap map=new TreeMap();
        map.put("aa","bb");
        map.put("b","cc");
        System.out.println(map.toString());
    }
}
