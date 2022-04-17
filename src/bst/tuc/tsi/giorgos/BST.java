package bst.tuc.tsi.giorgos;

public interface BST {

    /**
     * Inserts a new element in the bst
     * */
    public void insert(int key);

    /**deletes the element given from the bst if it exists */
    public void remove(int key);

    /**
     * searches the element in the bst data structure.
    * @return true if the key exists,false if does not exists
    * */
    public boolean find(int key);

    /**
     * Prints the binary search tree
     * */
    public void printBST();

    /**
     * empty the bst.
     * */
    public void clear();

    /**
     * @return true if the tree is empty.
     * */
    public boolean isEmpty();
    
    /**
     * @return the total number of nodes on the bst structure.
     *  */
    public int size();


}
