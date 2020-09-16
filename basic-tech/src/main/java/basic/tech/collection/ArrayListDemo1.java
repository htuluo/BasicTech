package basic.tech.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ArrayListDemo1 {
    private static List<String> list=new CopyOnWriteArrayList<>();// Collections.synchronizedList(new ArrayList<String>());// new ArrayList<String>();

    public static void main(String[] args) {
        setToArraylist();
//        arrayListTest();
//        System.out.println(list);
    }

    /**
     * set转ArrayList
     */
    private static void setToArraylist() {
        Set<String> set=new HashSet<>();
        set.add("aa");
        set.add("bb");
        set.add("cc");
        ArrayList<String> strings = new ArrayList<>(set);
        System.out.println(strings.toString());
    }

    /**
     * 会出现java.util.ConcurrentModificationException错误，
     * 解决方案：
     * 1. vector
     * 2. Collections.synchronizedList
     * 3. CopyOnWriteArrayList
     *
     */
    private static void arrayListTest() {
        for(int i=0;i<30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
