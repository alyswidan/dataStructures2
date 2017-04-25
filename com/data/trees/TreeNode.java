package data.trees;

import sun.reflect.generics.tree.Tree;

/**
 * Created by ADMIN on 4/1/2017.
 */
public class TreeNode<T extends Comparable> {
    public TreeNode<T> left, right, parent;
    public T content;
    public int depth;

    public TreeNode(TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent, T content, int depth) {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.content = content;
        this.depth = depth;
    }

    public TreeNode() {
    }

    public int getHeight()
    {
        if (left == null && right == null)
        {
            return 0;
        }
        else
        {
            return 1 + Math.max(left == null ? 0 : left.getHeight(), right == null ? 0 : right.getHeight());
        }
    }

    public TreeNode<T> insertInSubtree(T content) {

        if (content.compareTo(this.content) < 0) {
            if (left == null) {
                System.out.println("inserted " + content + " as left child of " + this.content + " at a depth of " + (this.depth + 1));
                return left = new TreeNode<>(null, null, this, content, (this.depth + 1));
            } else {
                System.out.println("inserting " + content + " in left subtree of " + this.content);
                return left.insertInSubtree(content);
            }
        } else {
            if (right == null) {
                System.out.println("inserted " + content + " as right child of " + this.content + " at a depth of " + (this.depth + 1));
                return right = new TreeNode<>(null, null, this, content, this.depth + 1);
            } else {
                System.out.println("inserting " + content + " in right subtree of " + this.content);
                return right.insertInSubtree(content);
            }
        }
    }

    public TreeNode<T> findInSubtree(T content) {

        if (this.content.equals(content)) return this;
        if (content.compareTo(this.content) < 0) {
            if (left != null) return left.findInSubtree(content);
            else return null;
        } else {
            if (right != null) return right.findInSubtree(content);
            else return null;
        }
    }

    public TreeNode<T> deleteFromSubtree(T content) {
        TreeNode<T> node = findInSubtree(content);
        if (node == null) return null;

        if (node.right == null && node.left == null)/*if the deleted node is a leaf node*/ {
            if (node.parent.right == node) node.parent.right = null;
            else node.parent.left = null;
            node.parent = null;
        } else if (node.left == null)/*if it has a right child*/ {
            node.parent.right = node.right;
            node.right.parent = node.parent;
            node.right = null;
            node.parent = null;
        } else if (node.right == null)/*if it has a left child*/ {
            node.parent.left = node.left;
            node.left.parent = node.parent;
            node.left = null;
            node.parent = null;
        } else /*if it has both a left and a right child*/ {
            TreeNode<T> min = node.right;
            while (min.left != null) min = min.left;/*get minimum in right sub tree*/
            /*swap content of nodes*/
            T temp = min.content;
            min.content = node.content;
            node.content = temp;
            return min;
        }

        return node;
    }

    void makeAsOnlyLeft(TreeNode<T> x) {
        this.left = x;
        x.parent = this;
    }


    @Override
    public String toString() {
        return "{" +
                "content:" + content.toString() + ", " +
                "left:" + (left == null ? "null" : left.content.toString()) + ", " +
                "right:" + (right == null ? "null" : right.content.toString()) + ", " +
                "parent:" + (parent == null ? "null" : parent.content.toString()) +
                "}";
    }
}
