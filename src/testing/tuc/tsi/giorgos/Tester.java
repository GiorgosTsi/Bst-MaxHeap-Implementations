package testing.tuc.tsi.giorgos;

import java.io.IOException;

import org.tuc.counter.MultiCounter;

import bst.tuc.tsi.giorgos.BST;
import bst.tuc.tsi.giorgos.BSTarrayImpl;
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
		System.out.println("Loading elements...");
		this.elementsToInsert = Load.elementsToInsert();
		this.elementsToDelete = Load.elementsToDelete();
		System.out.println("Starting process:");
	}
	
	public void doTest() {
		StringBuilder builder = new StringBuilder();
		BinarySearchTreeWithPointers bstp = new BinarySearchTreeWithPointers();
		BSTarrayImpl bsts = new BSTarrayImpl();
		
		/*1)TEST for bst with pointers: */
		System.out.println("*".repeat(80));
		System.out.println("**Test for binary search tree DYNAMIC implementation: ");
		doBinarySearchTreeTest(bstp);
		
		/*2)TEST for bst with array:  */
		System.out.println("\n");
		System.out.println("*".repeat(80));
		System.out.println("*Test for binary search tree STATIC implementation: ");
		doBinarySearchTreeTest(bsts);
		
		
	}
	public void doBinarySearchTreeTest(BST bst) {
		
		double totalOperations = 0;
		long end,begin = System.nanoTime();//starting time.
		
		for(int i=0; i<elementsToInsert.length; i++) {
			bst.insert(elementsToInsert[i]);
			totalOperations+=MultiCounter.getCount(1);
		}
		end = System.nanoTime();//ending time for insertions.
		System.out.println("	FOR INSERTIONS: ");
		System.out.println("		Size of the bst structure: " + bst.size());
		System.out.println("		Total time for 10^6 insertions  : " + (end-begin) + " nanoseconds" );
		System.out.println("		Mean Operations while inserting " + (totalOperations/elementsToInsert.length));
		
		totalOperations = 0;
		
		begin = System.nanoTime();
		for(int i=0; i<elementsToDelete.length; i++) {
			bst.remove(elementsToInsert[i]);
			totalOperations+=MultiCounter.getCount(1);
			MultiCounter.resetCounter(1);
		}
		end = System.nanoTime();
		System.out.println("	FOR DELETIONS: ");
		System.out.println("		Size of the bst structure after removing 100 elements: " + bst.size());
		System.out.println("		Total time for 100 deletions  : " + (end-begin) + " nanoseconds" );
		System.out.println("		Mean Operations while deleting " + (totalOperations/this.elementsToDelete.length));
	}
}
