package testing.tuc.tsi.giorgos;

import java.io.IOException;

import org.tuc.counter.MultiCounter;

import bst.tuc.tsi.giorgos.BinarySearchTreeWithPointers;

/**
 * @author giorgos tsi
 * Class that contains the methods
 * to do the measurements.
 *  */
public class Tester {

	private BinarySearchTreeWithPointers bstP;
	private int totalOperationsInsertionBstPointers;
	private int[] elementsToInsert;
	private int[] elementsToDelete;
	
	public Tester() throws IOException {
		
		/* */
		this.bstP = new BinarySearchTreeWithPointers();
		this.totalOperationsInsertionBstPointers = 0;
		
		/*Load The elements to insert/delete */
		this.elementsToInsert = Load.elementsToInsert();
		this.elementsToDelete = Load.elementsToDelete();
	}
	
	public void doBinarySearchTreeTest() {
		
		double totalOperations = 0;
		
		for(int i=0; i<elementsToInsert.length; i++) {
			this.bstP.insert(elementsToInsert[i]);
			totalOperations+=MultiCounter.getCount(1);
		}
		
		
		
		
		System.out.println("Mean Operations while inserting " + (totalOperations/elementsToInsert.length));
		
	}
}
