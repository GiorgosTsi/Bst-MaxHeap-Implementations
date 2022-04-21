package pQueue.tuc.tsi.giorgos;

import bst.tuc.tsi.giorgos.Node;

/**
 * Class that represents the node of the max heap
 * dynamic implementation.This node needs also a pointer
 * to the parent node.
 *  */
public class HeapNode extends Node {
	
	//pointer to the father node:
	Node parent;
	
	 /**
     * Constructor 1.
     * */
    public HeapNode() {
        super(0, null, null);
        this.parent = null;
    }

    /**
     * Constructor 2.
     * @param key of the node
     * */
    public HeapNode(int key) {
        super(key);
    }

    /**
     * Constructor 3.
     * @param key of the node
     *        the left node
     *        the right node
     * */
    public HeapNode(int key , Node left, Node right,Node parent){
        super(key, left, right);
        this.parent = parent;
    }
    
    /**
     * Constructor 4.
     * @param key of the node
     * @param parent of the node	
     *  
     *  */
    public HeapNode(int key, Node parent) {
    	super(key);
    	this.parent = parent;
    }

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
    
    

}
