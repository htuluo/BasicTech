package basic.tech.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 两个list的操作
 * @author: luolm
 * @createTime： 2019/11/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TwoListDemo {
    @Test
    public void testTwoList() {
        List<String> arrayList =  Arrays.asList("a", "b", "c").subList(0,2);
        List<String> arrayListB = Arrays.asList("f", "b", "c");
        arrayList.forEach(x -> System.out.println(x));
        System.out.println("--------------------");
        List<String> cList = new ArrayList<>();
        cList.add("11");
        cList.add("22");
        cList.add("33");
        arrayList.addAll(cList);//执行保持，因为Arrays内部类ArrayList没有实现add和addAll方法
        System.out.println("arrayList:" + arrayList);
        List<String> dList = new ArrayList<>();
        dList.add("dd");
        dList.add("ee");
        dList.add(0, "ff");
        dList.add(1, "hh");
//        dList.forEach(x -> System.out.println(x));

        System.out.println("dList:" + dList);
        cList.addAll(dList);
        System.out.println("clist:" + cList);

//        arrayList.forEach(x -> System.out.println(x));


        System.out.println("aaaaaaa");
    }

    public static void main(String[] args) {

    }
}
