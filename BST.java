/*
Author: Schuyler Asplin

CSCD300 Professor Xu

BST.java contains main class which consists of a user operated menu for interacting with the Binary Search Tree

It was hard to come up with better, more concise code than what was in the slides from class, so this code draws heavily
from those slides. I took time, however, to understand everything I was writing, and tried to demonstrate that via
comments.
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BST{
    public static void main(String[] args){


        File file = new File(args[0]);
        BinarySearchTree tree = new BinarySearchTree();
    //  ~~~~~~ fill tree from lines in file ~~~~~~
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                int k = Integer.parseInt(scanner.nextLine());
                tree.insert(k);

            }


        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }


    // ~~~~~~~~~ menu for interacting with BST ~~~~~~~~~~~~~
    displayMenu();

        try {
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.next();

            while(!userChoice.equals("x")){

                if (userChoice.equals("1")) { //search
                    System.out.println("Input the key: ");
                    int key = scanner.nextInt();
                    BST_Node result = tree.search(key);
                    if (result == null) {
                        System.out.println("The given key does not exist. ");
                    } else {
                        System.out.println("The given key exists. ");
                    }
                }

                else if(userChoice.equals("2")){ //insert
                    System.out.println("Input the key: ");
                    int key = scanner.nextInt();
                    BST_Node result = tree.insert(key);
                    if (result == null) {
                        System.out.println("The given key already exists. ");
                    } else {
                        System.out.println("The given key has been inserted. ");
                    }
                }

                else if(userChoice.equals("3")){ // delete
                    System.out.println("Input the key: ");
                    int key = scanner.nextInt();
                    try {
                        //BST_Node query = ;
                        tree.delete(tree.search(key));
                        System.out.println("The given key has been successfully deleted. ");
                    } catch (NullPointerException D) {
                        System.out.println("No such key exists. ");
                    }

                }
                else if(userChoice.equals("4")){
                    tree.InOrderTraversal(tree.root);
                }
                else if(userChoice.equals("5")){
                    tree.PreOrderTraversal(tree.root);
                }
                else if(userChoice.equals("6")){
                    tree.PostOrderTraversal(tree.root);
                }
                else if(userChoice.equals("7")){ // level order
                    try {
                        tree.LevelOrderTraversal(tree.root);
                    } catch (NullPointerException eLevelTrav) {}
//

                }

                else if(userChoice.equals("8")) { // min
                    try {
                        BST_Node min = tree.Min(tree.root);
                        System.out.println(min.key);
                    } catch (NullPointerException eMin){
                        System.out.println("The list is empty.");
                    }
                }

                else if(userChoice.equals("9")){ // max
                    try {
                        BST_Node max = tree.Max(tree.root);
                        System.out.println(max.key);
                    } catch (NullPointerException eMax) {
                        System.out.println("The tree is empty. ");
                    }
                }

                else if(userChoice.equals("a")){ //successor
                    System.out.println("Input the key: ");
                    int key = scanner.nextInt();

                    try {
                        BST_Node query = tree.search(key);
                        BST_Node successor = tree.Successor(query);
                        if (successor.key <= key) {
                            System.out.println("The given key exists but does not have a successor. ");
                        } else { //query.key > key - we've found a successor
                            System.out.println(successor.key);
                        }
                    } catch (NullPointerException zzz) {
                        System.out.println("No node exists with the given key");

                    }

                }
                else if(userChoice.equals("b")){ //predecessor
                    System.out.println("Input the key: ");
                    int key = scanner.nextInt();
                    BST_Node query = tree.search(key);
                    if (query == null) {
                        System.out.println("No node exists with the given key");
                    }
                    else {
                        BST_Node predecessor = tree.Predecessor(query);
                        if (predecessor.key >= key) {
                            System.out.println("The given key exists but does not have a predecessor. ");
                        } else { // predecessor.key < key
                            System.out.println(predecessor.key);
                        }
                    }
                }
                else {
                    System.out.println("invalid choice entered. Please try again.");
                }

                displayMenu();
                userChoice = scanner.next();
            }


        } catch (Exception e) {
            System.out.println("Exception e triggered.");
        }





    }

    public static void displayMenu() {
        System.out.println(
                "\n\n1) Search for a key\n" +
                "2) Insert a new key\n" +
                "3) Delete an existing key\n" +
                "4) Inorder traversal of the BST\n" +
                "5) Preorder traversal of the BST \n" +
                "6) Postorder traversal of the BST \n" +
                "7) Level-order traversal of the BST\n" +
                "8) Find the smallest key\n" +
                "9) Find the largest key\n" +
                "a) Find the successor of a given key\n" +
                "b) Find the predecessor of a given key\n" +
                "x) quit\n");
    }



}