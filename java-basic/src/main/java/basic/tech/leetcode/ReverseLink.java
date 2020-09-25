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

    /**
     * 翻转链表
     */
    public void reverse() {
        if (head == null) {
            return;
        }
        Node pre, second, third = null;
        pre = head;
        while (pre != null) {
            if (pre.next == null) {
                pre.next = third;
                head = pre;
                return;
            }
            second = pre.next;
            pre.next = third;
            if (second.next == null) {
                head = second;
                second.next = pre;
                return;
            }
            third = second.next;
            second.next = pre;
            if (third.next == null) {
                head = third;
            }
            pre = third.next;
            third.next = second;
        }


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
        ReverseLink.Node node = reverseLink.new Node();//内部类的创建
        reverseLink.add("a");
        reverseLink.add("b");
        reverseLink.add("c");
        reverseLink.add("d");
        reverseLink.add("e");
        reverseLink.add("f");
        System.out.println(reverseLink.toString());
        reverseLink.reverse();
        System.out.println(reverseLink.toString());
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



