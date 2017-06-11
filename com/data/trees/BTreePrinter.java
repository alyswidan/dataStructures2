package data.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mohamed Mahmoud on 4/21/2017.
 */
public class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(TreeNode<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<TreeNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int edgeLines = 1 << (Math.max(floor - 1, 0));//2^(max(0,floor-1))
        int firstSpaces = (1 << (floor)) - 1;//(2^floor)-1
        int betweenSpaces = (1 << (floor + 1)) - 1;//(2^floor+1)-1

        BTreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode<T>> newNodes = new ArrayList<>();

        nodes.forEach(node -> {
            if (node != null) {
                System.out.print(node.content);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            BTreePrinter.printWhitespaces(betweenSpaces);
        });
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {

        while (count-- > 0) System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(TreeNode<T> node) {
        return node == null ? 0 : (Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1);
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        return list.stream().noneMatch(Objects::nonNull);
    }

}