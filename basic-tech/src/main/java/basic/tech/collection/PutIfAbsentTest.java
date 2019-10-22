package basic.tech.collection;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/22
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class PutIfAbsentTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "zhangsan");
        map.put(2, "lisi");
        map.put(1, "wangwu");
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(MessageFormat.format("{0}:{1}", entry.getKey(), entry.getValue()));

        }
        Map<Integer, String> absentMap = new HashMap<>();
        absentMap.putIfAbsent(1, "张三");
        absentMap.putIfAbsent(2, "李四");
        absentMap.putIfAbsent(1, "王五");
        absentMap.forEach((key, value) -> {
            System.out.println(MessageFormat.format("{0}:{1}", key, value));
        });
    }
}
