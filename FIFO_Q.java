/*
Author: Schuyler Asplin

CSCD300 Professor Xu

FIFO Queue class. Used for Level-Order Traversal. Does not create new nodes, just manages pointers to garuntee access
to relationship data-fields of BST_Nodes in tree.
 */

public class FIFO_Q {

    protected int size;
    protected BST_Node head;
    protected BST_Node tail;

    //constructor
    public void FIFO_Q(){
        head = tail = null;
        size = 0;
    }

    //methods
    public void Enqueue(BST_Node node){
        BST_Node newNode = node;
        if (size == 0) { //Q is empty
            head = tail = newNode;
        } else { //node added to tail end
          tail.next = newNode;
          tail = newNode;
        }
        size++;
    }

    public BST_Node Dequeue(){
        if (size == 0){
            return null;
        }
        BST_Node retHead = head;
        if (size == 1){
            head = tail = null;
        }
        else {
            head = head.next; // would delete node from Q since links only move forward, but nodes are used in BST so they wont be deleted
        }
        size--;
        return retHead;
    }

}
