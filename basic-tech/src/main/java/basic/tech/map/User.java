package basic.tech.map;

import netscape.javascript.JSObject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class User {
    private Region region;
    private String name;

    public User(Region region, String name) {
        this.region = region;
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
//        String s1=new String("上海");
//        String s2=new String("上海");
//        System.out.println(s1.hashCode()+"----"+s2.hashCode());
        Region region = new Region("北京", "东城区");
        Region region1 = new Region("北京", "海淀区");
        Region region2 = new Region("北京", "朝阳区");
        Region region3 = new Region("北京", "西城区");
        Region region4 = new Region("上海", "浦东区");
        Region region5 = new Region("上海", "浦西区");
        Region region6 = new Region("上海", "浦西区");
        List<User> list = new ArrayList<>();
        list.add(new User(region, "张"));
        list.add(new User(region1, "张"));
        list.add(new User(region2, "张2"));
        list.add(new User(region3, "张3"));
        list.add(new User(region4, "张4"));
        list.add(new User(region5, "张4"));
        list.add(new User(region5, "张6"));
        list.add(new User(region5, "张7"));
        list.add(new User(region6, "张8"));
        Map<Region, Integer> map = new HashMap<>();
        for (User user : list) {
            if (map.containsKey(user.getRegion())) {
                map.put(user.getRegion(), map.get(user.getRegion()) + 1);
            } else {
                map.put(user.getRegion(), 1);
            }
        }
        for (Map.Entry<Region, Integer> entry : map.entrySet()) {
            System.out.println(MessageFormat.format("key:{0},value:{1}", entry.getKey().toString(), entry.getValue()));

        }

    }
}
