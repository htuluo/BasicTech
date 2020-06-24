package basic.tech.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/24
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ReverseLink {
    private Node head;


    public void add(String str) {
        if (head == null) {
            head = new Node(str);
            return;
        }
        Node index = head;
        while (index.next != null) {
            index = index.next;
        }
        index.next = new Node(str);
        return;

    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        if (head == null) {
            return list.toString();
        }
        Node index = head;
        while (index.next != null) {
            list.add(index.value);
            index = index.next;
        }
        list.add(index.value);
        return list.toString();
    }

    public static void main(String[] args) {
        ReverseLink reverseLink = new ReverseLink();
        reverseLink.add("aa");
        reverseLink.add("b");
        reverseLink.add("c");
        reverseLink.add("d");
        System.out.println(reverseLink);
    }

    class Node {
        private Node next;
        private String value;

        public Node() {
        }

        public Node(String value) {
            this.value = value;
        }


    }
}



