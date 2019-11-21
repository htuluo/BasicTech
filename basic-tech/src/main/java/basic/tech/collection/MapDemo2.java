package basic.tech.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/6/28
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class MapDemo2 {
    public static void main(String[] args) {
        Map h2 = new HashMap();
        for (int i = 0; i < 10; i++) {
            h2.put(new Element(i), new Figureout());
        }
        System.out.println("h2:");
        System.out.println("Get the result for Element:");
        Element test = new Element(3);
        if (h2.containsKey(test)) {
            System.out.println((Figureout) h2.get(test));
        } else {
            System.out.println("Not found");
        }
    }
}

class Element {
    int number;

    public Element(int n) {
        number = n;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Element) && (number == ((Element) o).number);
    }
}

class Figureout {
    Random r = new Random();
    boolean possible = r.nextDouble() > 0.5;

    @Override
    public String toString() {
        if (possible) {
            return "OK!";
        } else {
            return "Impossible!";
        }
    }
}
