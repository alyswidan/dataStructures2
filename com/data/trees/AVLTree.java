package data.trees;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;

/**
 * Created by ADMIN on 4/1/2017.
 */
public class AVLTree<T extends Comparable> extends BinarySearchTree<T>{

    @Override
    TreeNode<T> insert(T content) {
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
    TreeNode<T> delete(T content) {
        TreeNode<T> n = new TreeNode<>();
        n.deleteFromSubtree(content);
        return balance(n);
    }

    private TreeNode<T> balance(TreeNode<T> node) {
        if(node == null)
            return null;
        else if(node.left == null && node.right == null)
            return node;
        else if(node.left.getHeight() - node.right.getHeight() > 1 && node.right == null){               //if left - right > 1
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
        return node;
    }

    public static void main(String[] args) {
        AVLTree<Integer> bst = new AVLTree<>();
        bst.insert(15);
        bst.insert(10);
        bst.insert(20);
        bst.insert(8);
        bst.insert(4);
        bst.insert(9);
        bst.insert(18);
        bst.insert(32);
        bst.insert(40);

        BTreePrinter.printNode(bst.root);

        BTreePrinter.printNode(bst.root);
        bst.leftRotate(bst.find(20));
        BTreePrinter.printNode(bst.root);
        bst.leftRotate(bst.find(32));
        BTreePrinter.printNode(bst.root);
        bst.leftRotate(bst.find(8));
        BTreePrinter.printNode(bst.root);
        bst.rightRotate(bst.find(32));
        BTreePrinter.printNode(bst.root);
        bst.rightRotate(bst.find(40));
        BTreePrinter.printNode(bst.root);

        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        bst1.insert(3);
        bst1.insert(4);
        bst1.insert(5);
        System.out.println(bst1.find(3));
        System.out.println(bst1.find(4));
        System.out.println(bst1.find(5));
        BTreePrinter.printNode(bst1.root);
        BTreePrinter.printNode(bst1.root);

        //bst1.find(3).leftRotate();
        //BTreePrinter.printNode(bst1.root);


    }
}