package basic.tech.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TreeNodeDemo {
    private TreeNode root;

    class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private Integer value;

        public TreeNode(Integer value) {
            this.value = value;
        }


    }

    public void addNode(Integer value) {
        TreeNode treeNode = new TreeNode(value);
        if (root == null) {
            root = treeNode;
            return;
        }
        TreeNode pointer = root;
        while (pointer != null) {
            if (pointer.value.compareTo(value) >= 0) {
                if (pointer.left != null) {
                    pointer = pointer.left;
                    continue;
                } else {
                    pointer.left = treeNode;
                    break;
                }
            } else {
                if (pointer.right != null) {
                    pointer = pointer.right;
                    continue;
                } else {
                    pointer.right = treeNode;
                    break;
                }
            }
        }

    }


    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list.toString();
        }
        TreeNode pointer = root;
//        preOrderSearch(pointer, list);
        list.add(pointer.value);
        breadFirstOrderSearch(pointer, list);
        return list.toString();
    }

    /**
     * 前序遍历
     *
     * @param node
     * @param list
     */
    public void preOrderSearch(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.value);
            preOrderSearch(node.left, list);
            preOrderSearch(node.right, list);
        }
    }

    /**
     * 广度优先遍历
     *
     * @param node
     * @param list
     */
    public void breadFirstOrderSearch(TreeNode node, List<Integer> list) {
        Queue<TreeNode> treeNodes = new ArrayBlockingQueue<TreeNode>(12);
        treeNodes.add(node);
        if (node != null) {
            if (node.left != null) {
                list.add(node.left.value);
            }
            if (node.right != null) {
                list.add(node.right.value);
            }
            breadFirstOrderSearch(node.left, list);
            breadFirstOrderSearch(node.right, list);
        }
    }


    public static void main(String[] args) {
        TreeNodeDemo treeNodeDemo = new TreeNodeDemo();
        treeNodeDemo.addNode(10);
        treeNodeDemo.addNode(7);
        treeNodeDemo.addNode(8);
        treeNodeDemo.addNode(6);
        treeNodeDemo.addNode(15);
        treeNodeDemo.addNode(3);
        treeNodeDemo.addNode(16);
        treeNodeDemo.addNode(14);
        System.out.println(treeNodeDemo.toString());


    }


}

