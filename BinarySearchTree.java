/*
Author: Schuyler Asplin

(code draws significantly from Professor Xu's lecture slides)

CSCD300 Professor Xu

Binary Search Tree class. Utilizes node class, sorting nodes by nodes' key field, with higher values on the right and
lower values on the left.
 */
    public class BinarySearchTree {


        protected BST_Node root;


        BinarySearchTree(){
            root = null;
        }

        /*methods*/

        public BST_Node insert(int k) {

            BST_Node new_node = new BST_Node(k);

            BST_Node xParent = null; // will trail x, keeping track of x's parent
            BST_Node x = root;

            // find correct parent position
            while (x != null){
                xParent=x;
                if (k == x.key){ return null; } // cannot be duplicates keys in BST
                else if (k < x.key) { x = x.left; }
                else { x = x.right; }
            }

            new_node.parent = xParent;

            // insert at correct position relative to parent
            if (xParent == null) { // if BST empty, makes new_node the root
                root = new_node;
            }
            else if (k < xParent.key) {
                xParent.left = new_node;
            }
            else {
                xParent.right = new_node;
            }

            return new_node;
        }

        // 2 delete methods, overloaded with different data-types
        public BST_Node delete(int k) {
            BST_Node z = search(k);
            if (z != null) {
                delete(z);
            }
            return z;
        }

        public void delete(BST_Node z){
            if(z.left == null && z.right == null){ //z is a leaf node - no children
                transplant(z, null);
            }
            else if (z.left == null){ //z has 1 child on the right
                transplant(z, z.right);
            }
            else if (z.right == null){ //z has 1 child on the left
                transplant(z, z.left);
            }
            else { //z has 2 children
                BST_Node replacement = Min(z.right); //we already know there's at least one node on the right,
                // so we can skip calling successor node which handles the possibility of no right child
                // replacement must be z's successor
                if(replacement.parent != z){ //z has a right child that has 1 or more child nodes
                    transplant(replacement, replacement.right); //make Min of z.right the root of the z.right subtree
                    replacement.right = z.right;
                    replacement.right.parent = replacement;
                }
                transplant(z, replacement); //replace z with the node on it's right, which is it's successor
                replacement.left = z.left; //node keys on left must be < z's successor
                replacement.left.parent = replacement;
            }
        }

        public void transplant(BST_Node old_subtree, BST_Node new_subtree){
            if(old_subtree.parent == null) // thus oldsubtree is root, and we replace entire tree
                root = new_subtree;
            else if(old_subtree == old_subtree.parent.left){ //old_subtree is the left child of its parent
                old_subtree.parent.left = new_subtree; // replace the parents' left child with new_subtree
            }
            else { // old_subtree is the right child of its parent
                old_subtree.parent.right = new_subtree; //replace the parents' right child with new_subtree
            }

            if(new_subtree != null){ // new_subtree was not a leaf node
                new_subtree.parent = old_subtree.parent; // connect new_subtree's parent pointer to parent
            }
        }


        public BST_Node search(int k){ //trace through tree comparing k with keys, returning null if the value is not found
            BST_Node temp = root;
            while(temp != null && k != temp.key){
                if(k < temp.key) {
                    temp = temp.left;
                }
                else {
                    temp = temp.right;
                }
            }
            return temp;
        }

        public BST_Node Min(BST_Node Subtree_root){ // smallest value in subtree will be furthest to the left
            BST_Node temp = Subtree_root;
            while (temp.left != null) { temp = temp.left; }
            return temp;
        }

        public BST_Node Max(BST_Node Subtree_root){ // largest value in subtree will be furthest to the right
            BST_Node temp = Subtree_root;
            while (temp.right != null) { temp = temp.right; }
            return temp;
        }

        public BST_Node Successor(BST_Node x){ // find node with lowest key that is > than x.key
            if (x.right != null){
                return Min(x.right); // if x has a right node, the furthest left value of the right node is the successor
            }
            BST_Node y = x.parent;
            while (y != null && x == y.right){ //travel upwards to the highest node that x is to the left of
                x = y;
                y = y.parent;
            }
            return y;
            // at the end of the while loop, y is one level higher than the furthest directly left node of x.
            // for the second condition to fail, x must now be to the left of y
            // original x.key is > all the node keys we travelled through, but < than where y ends up.
            // y is also < everything to the right of it, so it must be the successor to the x node we passed as an arg
        }

        public BST_Node Predecessor(BST_Node x){ // find node with highest key that is < x.key
            if (x.left != null){
                return Max(x.left);
            }
            BST_Node y = x.parent;
            while (y != null && x == y.left){
                x = y;
                y = y.parent;
            }
            return y;
        }

        // ~~~~~~~~~~~~ Traversals ~~~~~~~~~~~~~~

    // prints key values in ascending order
    public void InOrderTraversal(BST_Node subtree_root){
            if (subtree_root != null) {
                InOrderTraversal(subtree_root.left);
                System.out.print(subtree_root.key + "  ");
                InOrderTraversal(subtree_root.right);
            }
    }

    // prints root, then levelOrder printing of roots' left child tree and right child tree
    public void PreOrderTraversal(BST_Node subtree_root){
            if(subtree_root != null){
                System.out.print(subtree_root.key + "  ");
                PreOrderTraversal(subtree_root.left);
                PreOrderTraversal(subtree_root.right);
            }
    }

    //prints left to right, lowest nodes first. left side of root printed, right side of root, and finally root at end.
    public void PostOrderTraversal(BST_Node subtree_root){
            if(subtree_root != null){
                PostOrderTraversal(subtree_root.left);
                PostOrderTraversal(subtree_root.right);
                System.out.print(subtree_root.key + "  ");
            }
    }

    // prints rows sequentially from left to right, starting with root, then roots' children, roots; grandchildren, etc.
    public void LevelOrderTraversal(BST_Node subtree_root){
            /*
            It is necessary for enqueue to only create pointers to the BST_Nodes if we are to access the children of
            those BST_Nodes. Thus, FIFO_Q never creates new nodes, but just manages pointers to those nodes.
             */
        FIFO_Q Q = new FIFO_Q();
        Q.Enqueue(subtree_root);

        while(Q.size > 0){
            BST_Node delNode = Q.Dequeue(); // delNode = most recent node kicked out of Q
            System.out.print(delNode.key + "  ");
            if (delNode.left != null){ // add left child to the Q
                Q.Enqueue(delNode.left);
            }
            if (delNode.right != null){ // add right child to the Q
                Q.Enqueue(delNode.right);
            }

        }
    }


    }


