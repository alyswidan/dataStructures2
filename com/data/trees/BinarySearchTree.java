package data.trees;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by ADMIN on 4/1/2017.
 */
public class BinarySearchTree<T extends Comparable> {
    TreeNode<T> root;
    Comparator<T> comparator;

    BinarySearchTree() {

        comparator = Comparator.naturalOrder();
    }


    TreeNode<T> insert(T content) {
        if (root == null) {
            System.out.println("inserted " + content + " as root");
            System.out.println("=============================");
            return root = new TreeNode<>(null, null, null, content, 0);
        }
        TreeNode<T> node = root.insertInSubtree(content);
        System.out.println("===============================");
        return node;
    }

    TreeNode<T> delete(T content) {
        TreeNode<T> n = new TreeNode<>();
        n = root.deleteFromSubtree(content);
        return n;
    }

    TreeNode<T> find(T content) {
        return root.findInSubtree(content);
    }

    String getSpaces(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- >= 0) builder.append(" ");
        return builder.toString();
    }

    void rightRotate(TreeNode<T> y)
    {
        if(y==null || y.left==null)return;
        TreeNode<T> x = y.left;
        y.left = x.right;
        if(x.right!=null)x.right.parent = y;
        x.parent = y.parent;
        if(y.parent ==null)root = x;
        else if(y.parent.right == y) y.parent.right = x;
        else y.parent.left = x;
        x.right = y;
        y.parent = x;
    }
    void leftRotate(TreeNode<T> x) {

        if(x==null || x.right==null)return;
        TreeNode<T> y = x.right;

        x.right = y.left;
        if(y.left!=null)y.left.parent=x;
        y.parent = x.parent;

        if(x.parent == null)
            root = y;

        else if(x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;
    }
}