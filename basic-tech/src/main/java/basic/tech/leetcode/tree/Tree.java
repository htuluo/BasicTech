package basic.tech.leetcode.tree;

import java.util.*;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Tree {
    public TreeNode root;

    class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private Integer value;

        public TreeNode(Integer value) {
            this.value = value;
            left = null;
            right = null;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object node) {
            if (node instanceof TreeNode) {
                TreeNode tmp = (TreeNode) node;
                if (tmp.value.equals(this.value)) {
                    return true;
                }
            }
            return false;
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
     * 前序，中序，后序遍历
     *
     * @param node
     * @param list
     */
    public static void deepOrderSearch(TreeNode node, List<Integer> list, int order) {
        if (node != null) {
            if (order == 0) {
                list.add(node.value);
            }
            deepOrderSearch(node.left, list, order);
            if (order == 1) {
                list.add(node.value);
            }
            deepOrderSearch(node.right, list, order);
            if (order == 2) {
                list.add(node.value);
            }
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
        testSearch();


    }


    /**
     * 遍历demo
     */
    public static void testSearch() {
        Tree tree = new Tree();
        tree.addNode(10);
        tree.addNode(7);
        tree.addNode(8);
        tree.addNode(16);
        tree.addNode(5);
        tree.addNode(13);
        tree.addNode(2);
        tree.addNode(14);
        List<Integer> list = new LinkedList<>();
        deepOrderSearch(tree.root, list, 0);
        System.out.println("前序遍历：" + list);
        list.clear();
        deepOrderSearch(tree.root, list, 1);
        System.out.println("中序遍历：" + list);
        list.clear();
        deepOrderSearch(tree.root, list, 2);
        System.out.println("后序遍历：" + list);
        list.clear();
        tree.breadFirstOrderSearch(tree.root, list);
        System.out.println("广度优先遍历：" + list);
        list.clear();
        System.out.println(tree.toString());
    }


}

