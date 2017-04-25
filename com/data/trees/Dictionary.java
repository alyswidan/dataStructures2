package data.trees;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Mohamed Mahmoud on 4/22/2017.
 */
public class Dictionary<T extends Comparable>{
    public static int size = 0;
    private static AVLTree<String> tree = new AVLTree<>();

    private TreeNode<T> insert(T content){
        if(tree.find((String) content) == null){
            System.out.println("Word not in the dictionary");
            System.out.println("adding " + content + " to the list");
            TreeNode<T> node = (TreeNode<T>)tree.insert((String) content);
            size++;
            BTreePrinter.printNode(tree.root);
            System.out.println("size of the dictionary is " + size);
            return node;
        }
        System.out.println("ERROR: Word already in the dictionary!");
        return null;
    }

    private void isFound(T content){
        if (tree.find((String) content) == null){
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }

    private TreeNode<T> delete(T content){
        if(tree.delete((String) content) == null){
            System.out.println("ERROR: Word is not in the dictionary!");
            return null;
        }
        TreeNode<T> node = (TreeNode<T>) tree.delete((String) content);
        size--;
        BTreePrinter.printNode(tree.root);
        System.out.println("size of the dictionary is " + size);
        return node;
    }

    private void initialize(){
        try {
            BufferedReader sourceReader = new BufferedReader(new FileReader(".\\com\\data\\trees\\dictionary"));
            String line;
            while ((line = sourceReader.readLine()) != null){
                System.out.println(line);
                tree.insert(line);
                size++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BTreePrinter.printNode(tree.root);
        System.out.println("size of the dictionary is " + size);

    }

    private void batchLookUps(){
        try {
            BufferedReader sourceReader = new BufferedReader(new FileReader(".\\com\\data\\trees\\queries"));
            String line;
            while ((line = sourceReader.readLine()) != null){
                System.out.println("Searching for " + line);
                if (tree.find(line) == null){
                    System.out.println("NO");
                }
                else System.out.println("YES");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void batchDeletions(){
        try {
            BufferedReader sourceReader = new BufferedReader(new FileReader(".\\com\\data\\trees\\deletions"));
            String line;
            while ((line = sourceReader.readLine()) != null){
                System.out.println("Deleting the word " + line);
                if(tree.delete(line) == null){
                    System.out.println("ERROR: Word is not in the dictionary!");
                }
                else {
                    TreeNode<T> node = (TreeNode<T>) tree.delete(line);
                    size--;
                    BTreePrinter.printNode(tree.root);
                    System.out.println("size of the dictionary is " + size);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BTreePrinter.printNode(tree.root);
        System.out.println("The height of the tree: " + tree.root.getHeight());
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String word = null;
        String in = null;

        Dictionary<String> dictionary = new Dictionary<>();
        dictionary.initialize();

        do {
            in = input.next();
            if (in.equalsIgnoreCase("1")){
                word = input.next();
                dictionary.insert(word);

            }
            else if (in.equalsIgnoreCase("2")){
                word = input.next();
                dictionary.isFound(word);
            }
            else if (in.equalsIgnoreCase("3")){
                word = input.next();
                dictionary.delete(word);
            }
            else{
                System.out.println("ERROR: wrong instruction number");
            }
        }while (!in.equalsIgnoreCase("q"));
        System.out.println("exited from the program");

        dictionary.batchLookUps();
        dictionary.batchDeletions();
    }
}
