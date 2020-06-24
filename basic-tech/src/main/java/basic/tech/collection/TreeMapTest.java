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
        map.put("c","aa");
        map.put("ab","aa");
        map.put("b","tt");
        map.put("m","tt");
        map.put("q","bt");
        map.put("o","bs");
        System.out.println(map.toString());
    }
}
