package basic.tech.collection;

import java.util.HashMap;
import java.util.Random;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/6/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class MapDemo1 {
    public static void main(String[] args) {
        HashMap h1=new HashMap();
        Random r1=new Random();
        for (int i=0;i<1000;i++){
            Integer t=new Integer(r1.nextInt(20));
            if (h1.containsKey(t)) {
                ((Ctime) h1.get(t)).count++;
            } else {
                h1.put(t, new Ctime());
            }
        }
        System.out.println(h1);
    }
}

class Ctime{
    int count=1;
    @Override
    public String toString(){
        return Integer.toString(count);
    }
}
