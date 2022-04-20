package pQueue.tuc.tsi.giorgos;

/**
 * Max Heap is a way to implement
 * a priority queue.We are going to 
 * implement a max heap data structure
 * with static way(array implementation).
 * Max number of elements on the structure
 * is 10^6,if we build a heap with given keys one by one. 
 *  */
public class MaxHeapArrayImpl implements PriorityQueue{
	
	private int[] heap;//pointer to the heap array.
	
	private int size;//# of elements on the heap
	
	private int maxSize;
	
	
	/*Create an empty Max Heap structure.The default array size is 10^6. */
	public MaxHeapArrayImpl() {
		this.heap = new int[(int) Math.pow(10, 6)];//default max size is 1000000
		this.size = 0;//size of the heap is 0
	}
	
	
	/* Heapify.(Build a heap with given keys) */
	public MaxHeapArrayImpl(int[] keys) {
		this.heap = keys; // new heap array
		this.size = keys.length;//the new size of the heap
		this.buildHeap();//fix the heap array so it satisfies the heap property!!
	}


	/**
	 * Method to fix the heap array 
	 * so it satisfies the max heap Property:
	 * In a max heap, for any given node C, 
	 * if P is a parent node of C, 
	 * then the key (the value) of P is 
	 * greater than or equal to the key of C 
	 *  */
	private void buildHeap() {	
		
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	
	@Override
	public void insert(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int remove() {
		// TODO Auto-generated method stub
		return 0;
	}

}
