package data.trees;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;

/**
 * Created by ADMIN on 4/1/2017.
 */
public class AVLTree<T extends Comparable> extends BinarySearchTree<T>{

    @Override
    public TreeNode<T> insert(T content) {
        TreeNode<T> n;
        if (root == null) {
            System.out.println("inserted " + content + " as root");
            System.out.println("=============================");
            return root = new TreeNode<>(null, null, null, content, 0);
        }
        n = root.insertInSubtree(content);
        System.out.println("===============================");
        return balance(n.parent.parent);
    }

    @Override
    public TreeNode<T> delete(T content) {
        TreeNode<T> n = new TreeNode<>();
        n = root.deleteFromSubtree(content);
        return balance(n);
    }

    private TreeNode<T> balance(TreeNode<T> node) {
        if(node == null)
            return null;
        else if(node.left == null && node.right == null)
            return node;
        else if (node.right == null && node.left.getHeight() == 0 || node.left == null && node.right.getHeight() == 0)
            return node;
        else if (node.right == null && node.left.getHeight() > 0){
            rightRotate(node);
        }
        else if (node.left == null && node.right.getHeight() > 0){
            leftRotate(node);
        }
        else if(node.left.getHeight() - node.right.getHeight() > 1){               //if left - right > 1
            if (node.left.left.getHeight() >= node.left.right.getHeight()){        //if height of left.left >= left.right
                rightRotate(node);
            }
            else{                                                                  //if height of left.left < left.right
                leftRotate(node.left);
                rightRotate(node);
            }
        }
        else{
            if (node.right.getHeight() - node.left.getHeight() > 1){            //if right - left > 1
                if(node.right.right.getHeight() >= node.right.left.getHeight()) //if height of right.right >= right.left
                    leftRotate(node);
                else{
                    rightRotate(node.right);                                    //if height of right.right < right.left
                    leftRotate(node);
                }
            }
        }
        balance(node.parent);
        return node;
    }
}