/*
Author: Schuyler Asplin

CSCD300 Professor Xu

Node class for a Binary Search Tree
 */


public class BST_Node {

    protected int key;
    protected BST_Node left;
    protected BST_Node right;
    protected BST_Node parent;
    protected BST_Node next;
    //protected BST_Node grand_parent = this.parent.parent;

    /*constructors*/
    BST_Node(){}
    BST_Node(int key){
        this.key = key;
        parent = left = right = null;
    }


}
