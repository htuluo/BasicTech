package basic.tech.leetcode;

import javax.swing.tree.TreeNode;
import java.util.*;
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
//        breadFirstOrderSearch(pointer, list);
        breadFirstOrderSearchFindRight(pointer, list);
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
     * @param root
     * @param list
     */
    public void breadFirstOrderSearch(TreeNode root, List<Integer> list) {
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(root);
        while (treeNodeQueue.peek() != null) {
            TreeNode poll = treeNodeQueue.poll();
            list.add(poll.value);
            if (poll.left != null) {
                treeNodeQueue.add(poll.left);
            }
            if (poll.right != null) {
                treeNodeQueue.add(poll.right);
            }
        }
    }

    /**
     * 广度优先遍历,打印最右侧元素
     *
     * @param root
     * @param list
     */
    public void breadFirstOrderSearchFindRight(TreeNode root, List<Integer> list) {
        Queue<NodeLevel> treeNodeQueue = new LinkedList<>();
        NodeLevel nodeLevel = new NodeLevel(root, 1);
        treeNodeQueue.add(nodeLevel);
//        list.add(root.value);
        int i = 1;
        List<NodeLevel> nodeFilter = new LinkedList<>();
        while (treeNodeQueue.peek() != null) {
            NodeLevel poll = treeNodeQueue.poll();
            if (nodeFilter.size() > 0 && nodeFilter.get(nodeFilter.size() - 1).level.equals(poll.level)) {
                nodeFilter.remove(nodeFilter.size() - 1);
            }
            nodeFilter.add(poll);
            if (poll.node.left != null) {
                treeNodeQueue.add(new NodeLevel(poll.node.left, poll.level + 1));
            }
            if (poll.node.right != null) {
                treeNodeQueue.add(new NodeLevel(poll.node.right, poll.level + 1));
            }
        }

        while (nodeFilter.size() > 0) {
            list.add(nodeFilter.remove(0).node.value);
        }


    }

    class NodeLevel {
        private TreeNode node;
        private Integer level;

        public NodeLevel(TreeNode node, Integer level) {
            this.node = node;
            this.level = level;
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

