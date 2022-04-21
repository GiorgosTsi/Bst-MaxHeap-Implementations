package pQueue.tuc.tsi.giorgos;

public class TestHeap2 {

	public static void main(String[] args) {
		// Test Dynamic Memory Implementation heap.
		System.out.println("Test Dynamic Memory Implementation heap.");
		MaxHeapPointers mheap = new MaxHeapPointers();
		
		
		System.out.println("Test insert,remove routines,and also test the construction of the heap with given keys one by one.(O(nlogn))");
		mheap.insert(57);
		mheap.insert(25);
		mheap.insert(48);
		mheap.insert(12);
		mheap.insert(15);
		mheap.insert(64);
		mheap.insert(65);
		mheap.insert(67);
		mheap.printHeap();
		
		mheap.clear();
		
		System.out.println("New heap:\n");
		mheap.insert(50);
		mheap.insert(7);
		mheap.insert(15);
		mheap.insert(12);
		mheap.insert(17);
		mheap.insert(70);
		mheap.insert(6);
		mheap.printHeap();

	}

}
