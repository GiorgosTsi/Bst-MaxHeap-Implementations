package testing.tuc.tsi.giorgos;

import java.io.IOException;

import org.tuc.counter.MultiCounter;

import bst.tuc.tsi.giorgos.BST;
import bst.tuc.tsi.giorgos.BSTarrayImpl;
import bst.tuc.tsi.giorgos.BinarySearchTreeWithPointers;
import pQueue.tuc.tsi.giorgos.MaxHeapArrayImpl;
import pQueue.tuc.tsi.giorgos.MaxHeapPointers;
import pQueue.tuc.tsi.giorgos.PriorityQueue;

/**
 * @author giorgos tsi
 * Class that contains the methods
 * to do the measurements.
 *  */
public class Tester {

	private int[] elementsToInsert;
	private int[] elementsToDelete;
	
	public Tester() throws IOException {
		
		/*Load The elements to insert/delete */
		System.out.println("Loading elements...");
		this.elementsToInsert = Load.elementsToInsert();
		this.elementsToDelete = Load.elementsToDelete();
		System.out.println("Starting process:");
	}
	
	public void doTest() {
	
		BinarySearchTreeWithPointers bstp = new BinarySearchTreeWithPointers();
		BSTarrayImpl bsts = new BSTarrayImpl();
		MaxHeapArrayImpl mHeapArrayImpl = new MaxHeapArrayImpl();
		MaxHeapPointers maxHeapPointers = new MaxHeapPointers();
		
		/*1)TEST for bst with pointers: */
		System.out.println("*".repeat(80));
		System.out.println("*Test for binary search tree DYNAMIC implementation: ");
		doBinarySearchTreeTest(bstp);
		
		/*2)TEST for bst with array:  */
		System.out.println("\n");
		System.out.println("*".repeat(80));
		System.out.println("*Test for binary search tree STATIC implementation: ");
		doBinarySearchTreeTest(bsts);
		
		/*3)TEST for priority queue array implementation: */
		System.out.println("\n");
		System.out.println("*".repeat(80));
		System.out.println("*Test for priority queue STATIC implementation: ");
		doPriorityQueueTest(mHeapArrayImpl);
		
		/*4)TEST for priority queue dynamic implementation: */
		System.out.println("\n");
		System.out.println("*".repeat(80));
		System.out.println("*Test for priority queue DYNAMIC implementation: ");
		doPriorityQueueTest(maxHeapPointers);
		
		/*BUILD HEAP TESTS: */
		System.out.println("\n");
		System.out.println("*".repeat(80));
		System.out.println("*BUILD HEAP:");
		this.doBuildHeapTest();
		
		for (int i = 0; i < elementsToDelete.length; i++) 
			for (int j = i + 1 ; j < elementsToDelete.length; j++) 
				if (elementsToDelete[i] == (elementsToDelete[j]))  // got the duplicate element 
					System.out.println("duplicate element in delete keys list: " + elementsToDelete[i]);
				
			
		
		
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
			bst.remove(elementsToDelete[i]);
			totalOperations+=MultiCounter.getCount(1);
			MultiCounter.resetCounter(1);
			//System.out.println("size " + bst.size());
		}
		end = System.nanoTime();
		System.out.println("	FOR DELETIONS: ");
		System.out.println("		Size of the bst structure after removing 100 elements: " + bst.size());
		System.out.println("		Total time for 100 deletions  : " + (end-begin) + " nanoseconds" );
		System.out.println("		Mean Operations while deleting " + (totalOperations/this.elementsToDelete.length));
	}
	
	public void doPriorityQueueTest(PriorityQueue q) {
		
		double totalOperations = 0;
		long end,begin = System.nanoTime();//starting time.
		
		MultiCounter.resetCounter(2);
		for(int i=0; i<elementsToInsert.length; i++) {
			q.insert(elementsToInsert[i]);
			totalOperations+=MultiCounter.getCount(2);
			MultiCounter.resetCounter(2);//reset the counter after every insert!
		}
		end = System.nanoTime();//ending time for insertions.
		
		System.out.println("	FOR INSERTIONS: ");
		System.out.println("		Size of the Pqueue structure: " + q.size());
		System.out.println("		Total time for 10^6 insertions  : " + (end-begin) + " nanoseconds" );
		System.out.println("		Mean Operations while inserting " + (totalOperations/elementsToInsert.length));
		
		totalOperations = 0;
		MultiCounter.resetCounter(2);
		begin = System.nanoTime();
		for(int i=0; i<100; i++) {
			q.remove();
			totalOperations+=MultiCounter.getCount(2);
			MultiCounter.resetCounter(2);//reset the counter after every remove!
		}
		
		end = System.nanoTime();
		System.out.println("\tFOR DELETIONS: ");
		System.out.println("\t\tSize of the Pqueue structure after removing 100 elements: " + q.size());
		System.out.println("\t\tTotal time for 100 deletions  : " + (end-begin) + " nanoseconds" );
		System.out.println("\t\tMean Operations while deleting " + (totalOperations/100));
	}
	
	public void doBuildHeapTest() {
		
		MaxHeapArrayImpl mHeapArrayImpl;
		MaxHeapPointers maxHeapPointers;
		
		/*BUILD HEAP FOR STATIC IMPLEMENTATION: */
		double end,start = System.nanoTime();
		MultiCounter.resetCounter(2);//reset the counter.
		System.out.println("\tMeasurements for build heap with heap static implemented:");
		mHeapArrayImpl = new MaxHeapArrayImpl(this.elementsToInsert);//build heap with given keys from the begin.
		end = System.nanoTime();
		System.out.println("\t\tMean Operations for build Heap: " + MultiCounter.getCount(2));
		System.out.println("\t\tTotal time for build heap: " + (end-start) + " nanoseconds" );
		
		/*BUILD HEAP FOR DYNAMIC IMPLEMENTATION: */
		start = System.nanoTime();
		MultiCounter.resetCounter(2);//reset the counter.
		System.out.println("\tMeasurements for build heap with heap dynamic implemented:");
		maxHeapPointers = new MaxHeapPointers(elementsToInsert);//build heap with given keys from the begin.
		end = System.nanoTime();
		System.out.println("\t\tMean Operations for build Heap: " + MultiCounter.getCount(2));
		System.out.println("\t\tTotal time for build heap: " + (end-start) + " nanoseconds" );
		
	}
}
