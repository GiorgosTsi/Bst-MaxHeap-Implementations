package pQueue.tuc.tsi.giorgos;

public class TestHeap1 {

	public static void main(String args[]) {
		MaxHeapArrayImpl mheap = new MaxHeapArrayImpl();
		
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
		
		System.out.println("call remove method: ");
		mheap.remove();
		mheap.printHeap();
		
		System.out.println("call remove method: ");
		mheap.remove();
		mheap.printHeap();
		
		System.out.println("call remove method: ");
		mheap.remove();
		mheap.printHeap();
		
		System.out.println("call remove method: ");
		mheap.remove();
		mheap.printHeap();
		
		System.out.println("call remove method: ");
		mheap.remove();
		mheap.printHeap();
		
		System.out.println("call remove method: ");
		mheap.remove();
		mheap.printHeap();
		
		System.out.println("call remove method(Delete the last element): ");
		mheap.remove();
		mheap.printHeap();
		
		
	}
}
