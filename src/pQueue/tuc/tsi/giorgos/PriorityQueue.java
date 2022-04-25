package pQueue.tuc.tsi.giorgos;

/**
 * @author giorgos tsi
 * The interface of the priority queue structure.
 * Internally,priority queue will be implemented
 * using a max heap structure.The abstract data type
 * of a priority queue has the above methods.
 * (Insert,Delete,isEmpty)
 *  */
public interface PriorityQueue {

	/*Insert an element with the inputed key */
	public void insert(int key);//insert with priority.Priority is given with the key!
	
	
	/*Removes the element with the maximum priority.(The root of the heap) */
	public int remove();//removes highest priority element.
	
	
	/*Checks if the data structure is empty */
	public boolean isEmpty();
	
	/*size of the queue */
	public int size();
}
