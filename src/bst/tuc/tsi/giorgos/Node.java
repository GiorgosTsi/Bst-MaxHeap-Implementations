package bst.tuc.tsi.giorgos;

public class Node {

    private int key;
    private Node left;//pointer to the left subtree.
    private Node right;//pointer to the right subtree.

    /**
     * Constructor 1.
     * */
    public Node(){
        this.key = 0;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructor 2.
     * @param key of the node
     * */
    public Node(int key){
        this.key = key;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructor 3.
     * @param key of the node
     *        the left node
     *        the right node
     * */
    public Node(int key , Node left, Node right){
        this.key = key;
        this.left = null;
        this.right = null;
    }

    /**
     * Updates the key of the node.
     * @param  key of the node
     * */
    public void setKey(int key){
        this.key = key;
    }

    public boolean isLeaf() // Return true if this is a leaf node
    {
        return (left == null) && (right == null);
    }

    public int getKey() {
        return key;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}

