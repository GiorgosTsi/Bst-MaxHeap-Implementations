package pQueue.tuc.tsi.giorgos;

/**
 * Class that represents the node of the max heap
 * dynamic implementation.This node needs also a pointer
 * to the parent node.
 *  */
public class HeapNode {
	
	private HeapNode parent;//pointer to the father node:
	private int key;//key of the node
    private HeapNode left;//pointer to the left subtree.
    private HeapNode right;//pointer to the right subtree.

    /**
     * Constructor 1.
     * */
    public HeapNode(){
        this.key = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /**
     * Constructor 2.
     * @param key of the node
     * */
    public HeapNode(int key){
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
    public HeapNode(int key , HeapNode left, HeapNode right){
        this.key = key;
        this.left = null;
        this.right = null;
    }
    
    /**
     * Constructor 4.
     * @param key of the node
     * @param parent of the node	
     *  
     *  */
    public HeapNode(int key, HeapNode parent) {
    	this(key);
    	this.parent = parent;
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

    public HeapNode getLeft() {
        return left;
    }

    public HeapNode getRight() {
        return right;
    }

    public void setLeft(HeapNode left) {
        this.left = left;
    }

    public void setRight(HeapNode right) {
        this.right = right;
    }
    

	public HeapNode getParent() {
		return parent;
	}

	public void setParent(HeapNode parent) {
		this.parent = parent;
	}
    
    

}
