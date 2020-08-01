package basic.tech.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 是否是子树
 * @author: luolm
 * @createTime： 2020/8/1
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SubTree {

    /**
     * 是否是子树
     * @param root1
     * @param root2
     * @return
     */
    public static boolean isSubTree(Tree.TreeNode root1, Tree.TreeNode root2) {
        boolean flag = false;
        if(root1 != null && root2 != null){
            if(root1.equals( root2)){
                flag = isSub(root1, root2);
            }else{
                flag = isSubTree(root1.getLeft(), root2);
                if(!flag){
                    flag = isSubTree(root1.getRight(), root2);
                }
            }
        }
        return flag;
    }
    private static boolean isSub(Tree.TreeNode root1, Tree.TreeNode root2){
        if(root2 == null){
            return true;
        }
        if(root1 == null){
            return false;
        }
        if(!root1.equals(root2) ) {
            return false;
        }

        return isSub(root1.getLeft(), root2.getLeft()) && isSub(root1.getRight(), root2.getRight());
    }


    public static boolean isSubTree(Tree tree1, Tree tree2) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Tree.deepOrderSearch(tree1.root, list1, 0);
        Tree.deepOrderSearch(tree2.root, list2, 0);
        for (int i = 0; i < list2.size(); i++) {
            list1.indexOf(list2.get(0));

        }

        return list1.containsAll(list2);
    }


    public static void main(String[] args) {
        testSubTreeSearch();
        String s = new String();
//        s.contains()

    }

    /**
     * 遍历demo
     */
    public static void testSubTreeSearch() {
        Tree tree = new Tree();
        tree.addNode(10);
        tree.addNode(7);
        tree.addNode(8);
        tree.addNode(16);
        tree.addNode(5);
        tree.addNode(13);
        tree.addNode(2);
        tree.addNode(14);
        Tree tree2 = new Tree();
        tree2.addNode(10);
        tree2.addNode(4);
        tree2.addNode(8);

        System.out.println(isSubTree(tree, tree2));
        System.out.println(tree.toString());
    }
}