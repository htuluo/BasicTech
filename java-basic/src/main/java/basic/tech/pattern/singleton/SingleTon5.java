package basic.tech.pattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 容器实现单例，安全
 * @author: luolm
 * @createTime： 2019/7/4
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SingleTon5 {
    private static SingleTon5 singleTon5;
    private static Map<String, Object> map = new HashMap<>();

    public static void registService(String key, Object service) {
        if (!map.containsKey(key)) {
            map.put(key, service);
        }
    }

    public static Object getInstance(String key) {
        return map.get(key);
    }
}
