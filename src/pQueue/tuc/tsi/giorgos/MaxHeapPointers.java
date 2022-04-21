package pQueue.tuc.tsi.giorgos;

import Assert.tuc.tsi.giorgos.Assert;

/**
 * @author giorgos tsi
 * Max Heap is a way to implement
 * a priority queue.We are going to 
 * implement a max heap data structure
 * with dynamic way(implementation with pointers).
 * Insertion and deletion in a heap need access to the right most child of the tree. For array implementation, 
 * this is easy. For dynamic memory implementation of a heap, this is trickier, as we do not want to exceed 
 * O(logn) of insert/delete itself.So we made the getParentOfRightMostNode method.
 *  */
public class MaxHeapPointers implements PriorityQueue{

	private HeapNode root;//root of the binary tree.
	
	private int size;//# of nodes in the tree.
	
	/**
	 * Constructor 1
	 *  */
	public MaxHeapPointers() {
		this.root = null;
		this.size = 0;
	}
	
	
	/**
	 * Method to insert a key in the max heap structure.
	 * @param key to be inserted. 
	 *  */
	@Override
	public void insert(int key) {
		
		if(this.isEmpty()) {//if heap is empty:
			this.root = new HeapNode(key);
			size++;
			return;
		}
		//else
		
		/*1) Insert the element in the next right most node available! */
		
		//to insert an element to the right most available node we
		//will find the father of the right most node without changing logn complexity for insertion.
		HeapNode curr,parent = this.getParentOfRightMost(root, ++size);//increase the size!!
		curr = new HeapNode(key, parent);
		
		if(parent.getLeft() == null) 
			parent.setLeft(curr);
		else 
			parent.setRight(curr);
		
		
		/*2) Sift Up the node,because we have to satisfy the heap property! */
		this.siftUp(curr);
	}

	/**
	 * Method to remove the higher priority element from
	 * the max heap structure.
	 * @return the key of the element deleted. 
	 *  */
	@Override
	public int remove() {
		
		Assert.notFalse(size > 0, "Heap is empty");
		int elementDeleted = root.getKey();
		
		if(this.size == 1) {//if heap tree has only the root.
			this.root = null;
			this.size--;;
			return elementDeleted;
		}
		
		/*1) swap keys between root and right most node.(Firstly we need to find the right most node!) */
		
		HeapNode rightMostNode = this.getRightMostNode(root, size);
		this.swapKeys(rightMostNode, root);//swap their keys.
		
		/*2) delete the new right most node(the old root!) */
		
		HeapNode parent = rightMostNode.getParent();
		if(parent.getRight() != null)//parent.right is the right most child!Should be deleted
			parent.setRight(null);
		else //parent.left is the right most child!Should be deleted.
			parent.setLeft(null);
		
		/*3) Sift Down the new root so we keep the property satisfied */
		this.siftDown(root);
		//decrease the size of the heap!
		this.size--;
		
		//return the deleted key
		return elementDeleted;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
     * Method to print the heap tree.
     * We do something like reverse inorder traversal.
     * First print right subtree,then root,and finally left subtree.
     * */
    public void printHeap() {
        if(this.isEmpty()) {
            System.out.println("Heap is empty!");
            return;
        }
        this.printHelp(this.root,0);//root will be printed without space offset.
    }
    
    public void clear() {
    	this.root = null;
    	this.size = 0;
    }

/*****************************Auxiliary Methods used in the dynamic memory implementation ************************************/
	
	/**
	 * Assuming that a tree starting at rootNode is complete, get the parent node of the right most tree(Heaps are complete)
	 * if the tree has numberOfNodes nodes
	 * Based on slide 18 of https://appsrv.cse.cuhk.edu.hk/~taoyf/course/2100/21-fall/lec/priority-q.pdf
	 * Convert int to binary representation string https://mkyong.com/java/java-convert-an-integer-to-a-binary-string/
	 * numberOfNodes must be at least 1.
	 * This method will find the parent of the right most node in the tree.When inserting an element
	 * we have to put as parameter the new size(increased),so we can find the parent of the node in which
	 * we will insert the key.We assume that this node is already in the tree so we can find his parent.
	 * When removing an element we put as parameter the actual size of the tree before decreasing it.
	 * @param rootNode
	 * @param numberOfElements(+1 when needed for adding element)
	 * @return parent of right most node
	 */
	 private HeapNode getParentOfRightMost(HeapNode rootNode, int numberOfNodes) {
		 
		 if (numberOfNodes == 0 || numberOfNodes < 0) { // invalid
			 // ERROR
			 return null;
		 }
		 
		 HeapNode current = rootNode;
		 // binary of numberOfElements without leading zeros
		 String asBinary= Integer.toBinaryString(numberOfNodes); 
		 // iterate from 2nd bit up to second to last bit 
		 for (int currentPos = 1; currentPos< asBinary.length()-1; currentPos++) 
			 if (asBinary.charAt(currentPos) == '0') 
				 current = current.getLeft();//cast to heap node
			 
			 else 
				 current = current.getRight();//cast to heap node
		 	
		 
	 
		 return current;
	 }
	 
	 /**
	  * Method to get the right most node in the heap tree,
	  * using the get parent of right most node method.
	  * @param root node
	  * @param number of nodes
	  * @return right most node of the tree 
	  *  */
	 private HeapNode getRightMostNode(HeapNode rootNode,int numberOfNodes) {
		 
		 HeapNode parent = this.getParentOfRightMost(rootNode, numberOfNodes);
		 Assert.notNull(parent);//parent should not be null!
		 return parent.getRight() == null ? parent.getLeft() : parent.getRight() ;
	 }
	 
	 /**
	 * Method to sift up a key from curr node,to the appropriate node
	 * to satisfy the heap property.Used when inserting an element!
	 * @param node of the key to sift up,if we have to.
	 * the element will be sifted up only if his value(key)
	 * is greater than it's parent value.
	 *  */
	private void siftUp(HeapNode curr) {
		
		while( (curr != root) && ( curr.getParent().getKey() < curr.getKey() ) ) {
			swapKeys(curr, curr.getParent());
			curr = curr.getParent();
		}
			
	}
	
	/**
	 * Method to sift down a key from the curr node
	 * to the appropriate node so that the max heap
	 * property is satisfied.Used when removing the
	 * highest priority element.
	 * @param node of the key to sift down
	 * The element will be sifted down until it's not a leaf
	 * or his children have smaller keys and so the property is satisfied.  
	 *  */
	private void siftDown(HeapNode curr) {
		
		while(!curr.isLeaf()) {
			//find the greater value child:
			HeapNode greaterValueNode = curr.getLeft();//left child exists for sure.
			if(curr.getRight() != null && curr.getRight().getKey() > curr.getLeft().getKey() )
				greaterValueNode = curr.getRight();
			
			//if curr's node key < greater value child's key swap their keys.
			if(curr.getKey() < greaterValueNode.getKey())
				swapKeys(greaterValueNode, curr);
			else
				return;//property is satisfied!
			
			curr = greaterValueNode;
		}
	
	}
	/**
	 * Method to swap the keys of two nodes.
	 * @param node1
	 * @param node2
	 *  */
	private void swapKeys(HeapNode node1,HeapNode node2) {
		int node1Key = node1.getKey();
		node1.setKey(node2.getKey());
		node2.setKey(node1Key);
	}
	
	/**
     * Method used to print the heap on inorder traversal.
     * Uses recursion.
     * @param root of the tree/subtree and the number of spaces
     *             to print on the line in which the element will
     *             be printed.
     * */
    private void printHelp(HeapNode root,int spaces){

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
		
		
	 
	 
}
