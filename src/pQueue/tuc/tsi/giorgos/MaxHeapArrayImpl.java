package pQueue.tuc.tsi.giorgos;

import Assert.tuc.tsi.giorgos.Assert;

/**
 * @author giorgos tsi
 * Max Heap is a way to implement
 * a priority queue.We are going to 
 * implement a max heap data structure
 * with static way(array implementation).
 * Max number of elements on the structure
 * is 10^6,if we build a heap with given keys one by one. 
 * Max Heap property:Every node has a key >= key of children.
 *  */
public class MaxHeapArrayImpl implements PriorityQueue{
	
	private int[] heap;//pointer to the heap array.
	
	private int size;//# of elements on the heap
	
	private int maxSize;// max size of the heap.
	
	
	/*Create an empty Max Heap structure.The default array size is 10^6. */
	public MaxHeapArrayImpl() {
		this.heap = new int[(int) Math.pow(10, 6)];//default max size is 1000000
		this.size = 0;//size of the heap is 0
		this.maxSize = this.heap.length;
	}
	
	
	/* Heapify.(Build a heap with given keys) */
	public MaxHeapArrayImpl(int[] keys) {
		this.heap = keys; // new heap array
		this.size = keys.length;//the new size of the heap
		this.maxSize = keys.length;
		this.buildHeap();//fix the heap array so it satisfies the heap property!!
	}


	/**
	 * Method to fix the heap array 
	 * so it satisfies the max heap Property:
	 * In a max heap, for any given node C, 
	 * if P is a parent node of C, 
	 * then the key (the value) of P is 
	 * greater than or equal to the key of C. 
	 *  */
	private void buildHeap() {	
		/*To build a max heap structure with given keys:
		 * 	1)Construct a random binary tree with these keys,(already constructed.We have an array implemented tree!)
		 * 	and siftDown every node to make the property satisfied.
		 *	2)The leaf nodes are already heaps.(Their children have smaller keys(0)).
		 * 	  Start from the last non leaf node to the first node and sift down every node
		 * 	  so the property to be satisfied.	 	
		 *  */
		// leaf nodes are all the nodes which have: pos >= (size/2) 
		for(int pos=size/2 - 1; pos>=0 ; pos--)
			siftDown(pos);
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	
	/**
	 * Method to insert a key in the max heap structure.
	 * @param key to be inserted. 
	 *  */
	@Override
	public void insert(int key) {
	
		Assert.notFalse(size < maxSize, "Max Heap is full.");
		
		/*1) Insert the key in the next free array index:(The next right most node available!) */
		
		this.heap[this.size++] = key;
		
		/*2) Sift up the element if it's needed,to satisfy the heap property: */
		
		this.siftUp(this.size - 1);
	}

	/**
	 * Method to remove the higher priority element from
	 * the max heap structure.
	 * @return the key of the element deleted. 
	 *  */
	@Override
	public int remove() {
		
		Assert.notFalse(size > 0, "Heap is empty");
		/*1) Swap the 'root' with the last item inserted.(Last node is in the pos size-1) */
		swap(0, size - 1);//in pos 0 is the root, and in pos size-1 the last element inserted.
		
		/*2)Delete the last element(old root) */
		size--;//by decreasing the size of the array,we 'explicitly' delete that element.
		//The new element inserted will override that value,so it is like deleting it.
		
		/*3) Sift down the new root,because we have to satisfy the max heap property .*/
		siftDown(0);
		
		return this.heap[size];//heap[size] is the element 'deleted',the old 'root'.
	}
	
	/**
     * Method to print the max Heap.
     * We do something like reverse inorder traversal.
     * First print right subtree,then root,and finally left subtree.
     * */
	public void printHeap() {
		
		if(this.isEmpty()) {
            System.out.println("Heap is empty!");
            return;
        }
        this.printHelp(0,0);
	}
	
	/**
     * Method used to print the max heap on inorder traversal.
     * Uses recursion.
     * @param root index of the tree/subtree and the number of spaces
     *             to print on the line in which the element will
     *             be printed.
     * We print 10 spaces for every tree level.
     * */
	public void printHelp(int rootIdx,int spaces) {
		
		if(rootIdx > this.size -1 )//if index is greater than the size -> Base case.
            return;

        /*Print Right subtree */
        printHelp(rightChild(rootIdx),spaces + 10);//10 spaces for every tree level.

        /*print the root node */
        System.out.print("\n");
        for(int i=0; i<spaces; i++)
            System.out.print(" ");

        System.out.print(this.heap[rootIdx]);
        System.out.print("\n");

        /*Print Left subtree */
        printHelp(leftChild(rootIdx),spaces + 10);
	}
	
	public void clear() {
		this.heap = new int[(int) Math.pow(10, 6)];//default max size is 1000000
		this.size = 0;//size of the heap is 0
	}
	
	/**
	 * Method to find if the node in pos index is a leaf.
	 *  */
	public boolean isLeaf(int pos) {
		
		return (pos >= size / 2) && (pos < size);
	}
	
/************************* Auxiliary methods for the array implementation of the heap ********************************/
	
	/**
	 * Method to get the parent's index in the heap array.
	 * @param position of a node in the array
	 * @return the parent of the given position index.
	 *  */
	private int parent(int pos) {
		return (pos -1)/2 ;
	}
	
	/**
	 * Method to get the left child's index in the heap array.
	 * @param position of a node in the array
	 * @return left child's position in the heap array.
	 *  */
	private int leftChild(int pos) {
		return (2*pos + 1);
	}
	
	/**
	 * Method to get the right child's index in the heap array.
	 * @param position of a node in the array
	 * @return right child's position in the heap array.
	 *  */
	private int rightChild(int pos) {
		return (2*pos + 2);
	}
	
	
	/**
	 * Method to sift up a key from pos index,to the appropriate index
	 * to satisfy the heap property.Used when inserting an element!
	 * @param pos of the key to sift up,if we have to.
	 * the element will be sifted up only if his value(key)
	 * is greater than it's parent value.
	 *  */
	private void siftUp(int pos) {
		/*sift up the element until it is the root or the heap property is already satisfied. */
		
		//pos>0 check is needed when the pos will get the index of the root!
		//else we will have array index out of bounds exception for heap[parent(pos)]
		while(pos>0 && ( this.heap[parent(pos)] < this.heap[pos] ) ) {
			this.swap(pos, parent(pos));
			pos = parent(pos);//new pos to sift up is the parent's pos!
		}
	}
	
	/**
	 * Method to sift down a key from the pos index
	 * to the appropriate index so that the max heap
	 * property is satisfied.Used when removing the
	 * highest priority element.
	 * @param pos of the key to sift down
	 * The element will be sifted down until it's not a leaf
	 * or his children have smaller keys and so the property is satisfied.  
	 *  */
	private void siftDown(int pos) {
		
		while(!this.isLeaf(pos)) {
			int higherChild = this.leftChild(pos);
			int rightChild = this.rightChild(pos);
			
			/*Find the higher of the two children(if there are 2 children,else the higher is the left one(only that exists!)) */
			if((rightChild < size) && ( this.heap[rightChild] > this.heap[leftChild(pos)] ) )//if rightChild>=size,then there is only the left child.
				higherChild = rightChild;// Set this variable to greater child's value
			
			/*swap pos node with higher child node,only if pos node has smaller key value! */
			if(this.heap[pos] < this.heap[higherChild]) 
				this.swap(pos, higherChild);
			else 
				return;//property is satisfied!
			
			pos = higherChild;
		}
	}
	
	
	/**
	 * swap the values of the array on indices idx1 and idx2.
	 * @param idx1 idx2
	 *  */
	private void swap(int idx1, int idx2) {
		
		int temp = this.heap[idx1];
		this.heap[idx1] = this.heap[idx2];
		this.heap[idx2] = temp;
	}
}
