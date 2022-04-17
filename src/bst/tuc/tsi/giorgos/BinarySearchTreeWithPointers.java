package bst.tuc.tsi.giorgos;

public class BinarySearchTreeWithPointers implements  BST{
	
    private Node root; //root of the tree.
    private int size; //number of nodes on the bst structure.

    public BinarySearchTreeWithPointers() {
		this.root = null;
		this.size = 0;
	}
    
    
    @Override
    public void insert(int key) {
       // this.root = this.insertHelpRecursive(this.root,key);
        this.insertHelpIterative(key);
        this.size++;
    }


    
    @Override
    public void remove(int key) {
        //this.root = this.removeHelpRecursive(this.root,key);
        /*Need to assign the return value of the help method
        * because the root may be changed. */
        this.removeHelpIterative(key);
    }

    /**
     * Method to print the binary search tree.
     * We do something like reverse inorder traversal.
     * First print right subtree,then root,and finally left subtree.
     * */
    @Override
    public void printBST(){
        if(this.isEmpty()) {
            System.out.println("Tree is empty!");
            return;
        }
        this.printHelp(this.root,0);//root will be printed without space offset.
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public boolean find(int key) {
       // return this.findHelpRecursive(key,this.root);
        return this.findHelpIterative(key,this.root);
    }

/***************************************HELP METHODS****************************************/
    /**
     * Method used to insert a key in the bst structure.
     * Uses recursion.We need to return the new root
     * because it may be updated if the tree is empty.
     * */
    private Node insertHelpRecursive(Node root,int key){

        /*1) if tree or subtree is empty,insert the new element */
        if(root == null)
           return new Node(key);

        /*2) if key's value is smaller than node's key's value
        *    insert the element on the left subtree. */
        else if(key < root.getKey())
           root.setLeft(insertHelpRecursive(root.getLeft(),key));

        /*3) if key's value is greater than node's key's value
         *    insert the element on the right subtree. */
        else
            root.setRight(insertHelpRecursive(root.getRight(),key));

        return root;//return the new updated tree.
    }

    /**
     * @param key to be inserted in the bst structure.
     * This method inserts a key to the structure,
     * using a while loop only and not recursion.
     * */
    private void insertHelpIterative(int key){

        Node current,parent;
        Node newNode = new Node(key);
        current = parent = this.root;

        /*1) If tree is empty: */
        if(root == null) {
            this.root = newNode;
            return;
        }

        /*2) If tree is not empty: */
        while(current != null){//iterate until you reach a leaf.
            //traverse the tree until you find the correct position to insert the key.
            parent=current;

            if( key < current.getKey())
                current = current.getLeft();
            else
                current = current.getRight();
        }
        //assertion: current=null and parent is the father of the new node to be inserted.

        /*parent now is the parent of the new node to be inserted. */
        if(key < parent.getKey())
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);

    }



    /**
     * Method used to print the bst on inorder traversal.
     * Uses recursion.
     * @param root of the tree/subtree and the number of spaces
     *             to print on the line in which the element will
     *             be printed.
     * */
    private void printHelp(Node root,int spaces){

        if(root == null)
            return;

        /*Print Right subtree */
        printHelp(root.getRight(),spaces + 10);

        /*print the root node */
        System.out.print("\n");
        for(int i=0; i<spaces; i++)
            System.out.print(" ");

        System.out.print(root.getKey());
        System.out.print("\n");

        /*Print Left subtree */
        printHelp(root.getLeft(),spaces + 10);

    }

    private boolean findHelpIterative(int key,Node root){

        Node current = root;

        while(current != null){

            if(current.getKey() == key)//if current root is the one to search:
                return true;

            /*If key to find is smaller than
            * this node's key,traverse the left subtree. */
            else if(key < current.getKey()) {
                current = current.getLeft();
                if (current!=null  && key == current.getKey())
                    return true;
            }

            /*If key to find is greater than
             * this node's key,traverse the right subtree. */
            else if(key > current.getKey()) {
                current = current.getRight();
                if (current!=null  && key == current.getKey())
                    return true;
            }
        }

        //to be here the element does not exist.
        return false;
    }

    private boolean findHelpRecursive(int key,Node root){

        //base statement
        if(root == null)//return false if the key given does not exist!
            return false;

        /*Search root */
        if(root.getKey() == key)
            return true;
        /*Search left subtree */
        else if(key < root.getKey())
            return findHelpRecursive(key,root.getLeft());
        /*Search right subtree */
        else
            return findHelpRecursive(key,root.getRight());

    }

    /**
     * @return the minimum key of the binary search tree.
     * */
    public int getMinimumKey(){
        return this.getMinimumKey(this.root);
    }

    /**
     * @return the minimum key of the bst
     * with root the node given as parameter.
     * */
    private int getMinimumKey(Node root){
        /*The minimum key of the bst,is always in
        * the leftmost node.Traverse the bst and find the
        * left most node.Then return it's key.
        *  */
        Node current = root;
        if(root == null)//if the bst is empty.
            return -1;
        /* traverse the bst until you reach the leftmost node.
        * i.e until the next left node is null.
        * */
        while(current.getLeft() != null){
            current = current.getLeft();
        }

        /*Now current points to the left most node. */
        return current.getKey();
    }

    /**
     * Deletes the node with the given key from the bst with given root,
     * using recursion.
     * @param  root of the tree,
     *          key of the node to delete.
     * */
    private Node removeHelpRecursive(Node root,int key){
        /*
        * There are three possibilities when you have to delete a node.
        * 1)Node to delete is a leaf.
        * 2)Node to delete has only one child.
        * 3)Node to delete has two children.
        *  */

        /*Search if the node to delete does not exist */
        if(!this.find(key))
            return root;//if key does not exist,return the same tree unchanged.

        /*Base case: If root=null, i.e tree/subtree is empty */
        if(root == null)
            return null;

        //if key is smaller than root's key,delete the element from the left subtree
        if(key < root.getKey())
            root.setLeft(removeHelpRecursive(root.getLeft(),key));
        else if(key > root.getKey())
            root.setRight(removeHelpRecursive(root.getRight(),key));
        else{//key == root.getKey()

            if(root.getLeft() == null)//node to delete has only one child(right one) or is a leaf.
                return root.getRight();//this can cover the 2 possibilities.Leaf or one child

            else if(root.getRight() == null)//node to delete has only one child,the left one
                return root.getLeft();

            else{//node to delete has 2 children.
                /*Find the minimum element from right subtree,
                * delete it,and make it new root of this tree. */
                int minimumKey = this.getMinimumKey(root.getRight());
                remove(minimumKey);
                root.setKey(minimumKey);
            }

        }
        return root;//return the new updated tree.
    }

    /**
     * Deletes the node with the given key from the bst with given root,
     * using loops.
     * @param key of the node to delete.
     * */
    private void removeHelpIterative(int key){

        if(this.root == null)//tree is empty.
            return;

        /*Search if the node to delete does not exist */
        if(!this.find(key))
            return ;//if key does not exist,return.
        Node parent,node;
        parent = node = this.root;

        /*Find the node to delete,and it's parent: */
        while(node.getKey() != key){
            parent = node;

            if(key < node.getKey())
                node = node.getLeft();
            else
                node = node.getRight();
        }

        /*1) Node to delete,is a leaf. */
        if(node.getLeft() == null && node.getRight() == null) {
            if(node != this.root)//if node to delete is not the root.
                if (parent.getRight() == node)
                     parent.setRight(null);
                else
                    parent.setLeft(null);
            else
                this.root = null;//else set the root to null.
            
            this.size--;//decrease the size of the structure
        }

        /*2) Node to delete has one child(left or right) */
        else if(node.getLeft() == null || node.getRight() == null) {
            /*Find the child of the node: */
            Node childOfNodeToDelete = node.getLeft() == null ? node.getRight() : node.getLeft();
            /*If the node to delete is not the root: */
            if (node != this.root)
                if(parent.getLeft() == node)
                    parent.setLeft(childOfNodeToDelete);
                else
                    parent.setRight(childOfNodeToDelete);
            else
                this.root = childOfNodeToDelete;
            
            this.size--;//decrease the size of the structure

        }
        /*3) Node to delete has two children(left and right) */
        else {
            int minRightSubtreeElement = this.getMinimumKey(node.getRight());
            /*Delete the minimum element from right subtree node */
            this.remove(minRightSubtreeElement);
            /*Make the minimum element of the right subtree,the new root of this subtree */
            node.setKey(minRightSubtreeElement);
            /*There is no need to update the size.The size will be updated from the above remove call. */
        }
    }

    /**
     * @param key
     * @return the parent of the node contains this key.
     * */
    private Node getParent(int key){
        /*If key does not exist return null*/
        if(!find(key))
            return null;

        Node current = this.root;
        Node parent = current;

        while(current != null && current.getKey() != key){
            parent = current;

            if(key < current.getKey())
                current = current.getLeft();
            else
                current = current.getRight();
        }

        return parent;
    }
    
    @Override
    public int size() {
    	return this.size;
    }


}
