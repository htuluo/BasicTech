package basic.tech.leetcode;

import java.util.*;

/**
 * @description: 合并N个有序链表
 * @author: luolm
 * @createTime： 2020/7/16
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class MergeLink {

    static class Node {
        private Node next;
        private int value;

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }
    }

    /**
     * 合并n个有序链表
     * @param nodeList
     * @return
     */
    public static Node mergeLink(List<Node> nodeList) {
        //三种比较形式
       /* Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value-o2.value;
            }
        });*/
        //Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.value-o2.value);
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

        Node mergeHead = null;

        for (Node head : nodeList) {
            queue.add(head);
        }
        mergeHead = queue.poll();
        Node mergeNode = mergeHead;
        Node tempNode = mergeHead.next;
        while (queue.peek() != null) {
            if (tempNode != null) {
                queue.add(tempNode);
            }
            mergeNode.next = queue.poll();
            mergeNode = mergeNode.next;
            tempNode=mergeNode.next;
        }
        return mergeHead;
    }

    public static void main(String[] args) {
        Node head1 = new Node(null, 1);
        head1.next = new Node(null, 2);
        Node head2 = new Node(null, 3);
        head2.next = new Node(null, 4);
        Node head3 = new Node(null, 3);
        head3.next = new Node(null, 4);
        List<Node> list = new ArrayList<Node>();
        list.add(head1);
        list.add(head2);
        list.add(head3);
        Node mergeLink = mergeLink(list);
        while (mergeLink!=null){
            System.out.println(mergeLink.value);
            mergeLink=mergeLink.next;
        }
    }

}
