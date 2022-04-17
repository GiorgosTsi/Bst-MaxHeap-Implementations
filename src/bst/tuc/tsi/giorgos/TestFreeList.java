package bst.tuc.tsi.giorgos;

public class TestFreeList {

	public static void main(String[] args) {
		BSTarrayImpl binarySearchTree = new BSTarrayImpl();

        binarySearchTree.insert(5);
        binarySearchTree.insert(3);
        binarySearchTree.insert(9);
        binarySearchTree.insert(10);
        binarySearchTree.insert(6);
        binarySearchTree.insert(4);
        
        binarySearchTree.printArray();//free list works properly for insertions
        System.out.println(" avail: " + binarySearchTree.getAvail());//6
        
        //let's check free list with deletions.
        
        binarySearchTree.remove(4);
        //now we wait new avail = 5, and array[5][right] = old avail = 6
        
        binarySearchTree.printArray();
        System.out.println(" avail: " + binarySearchTree.getAvail());
        
        binarySearchTree.remove(5);//root
        //now we wait new avail = indexof(6) = 4 because when we delete a node with
        //2 children we delete the min node from right subtree and make it's value the new root of this subtree.
        
        binarySearchTree.printArray();
        System.out.println(" avail: " + binarySearchTree.getAvail());//4
        
        binarySearchTree.remove(9);
        //now we wait new avail = 2, and array[2][right] = old avail = 4
        
        binarySearchTree.printArray();
        System.out.println(" avail: " + binarySearchTree.getAvail());//2
	}

}
