package bst.tuc.tsi.giorgos;

/**
 * @author giorgos tsi
 * A static implementation of a binary search tree
 * data structure using a 2D array.Max Number of Nodes is N.
 * We use an imaginary 'free list' so we know where to add
 * a new node.Practically this free list is an index,which
 * indicates the index of the array where the new node must be inserted.
 * It's like a stack structure.Every time you delete an element,you insert the
 * array index of this node in the list,and this is the index where the next new node
 * will be inserted.After every insertion you update the index with the value
 * of the previous free index.In the 3rd column of the avail free index is stored
 * the index of the next available index to store.So if you add a new node to the structure
 * it will be inserted to the avail index and then this index will be updated with the value
 * stores in the 3rd column.
 * */
public class BSTarrayImpl implements BST{

    /*Max number of Nodes */
    private static final int MAX_NODES = 1000000;

    /*The array where the structure will be implemented: */
    private int[][] array;

    /*Root index of the bst: */
    private int rootIndex;

    /*Next available insertion index(top of the stack): */
    private int avail;

    /* Constants */
    //-2 to an array index indicates that the (left or right) pointer is null.
    private static final int NULL = -2;

    private static final int END_OF_SPACE = -1;

    /*left pointer of every node is stored in array[1] idx */
    private static final int left = 1;

    /*right pointer of every node is stored in array[2] idx */
    private static final int right = 2;

    public BSTarrayImpl(){
        this.rootIndex = -1;//there is no root node yet.
        /*It's better to make a 3xN matrix
        * than a Nx3.In a 3xN matrix every column is
        * a node and the first row indicates the key(data)
        * the second row indicates the index of the left
        * subtree root and the third row indicates
        * right's subtree root index.
        *  */
        this.array = new int[3][MAX_NODES];
        this.initializeFreeList();
    }

    /*
    * Method used to initialize the free list of the
    * bst array structure when starting the program.
    * Used only on the constructor.
    *  */
    private void initializeFreeList(){
        /*
         * Initialize the 'free list':
         * Initialize the avail index with 0.
         * (The first node will be inserted in array index 0).
         * Store in every matrix row in 3rd column, the number
         * of the next row.This indicates that the next free index
         * position after this one is at the next row.So when the first insertion will
         * be made, the avail index will be 1,Then after the second,avail
         * index will be 2... e.t.c
         * Beware of the 3rd column of row N-1.Value -1.
         * That means that there is no other free index.i.e no other
         * insertions can be made.
         *  */
        this.avail = 0;
        for(int i=0; i<MAX_NODES -1; i++)
            this.array[right][i] = i+1;

        this.array[right][MAX_NODES - 1] = END_OF_SPACE;


    }


    /**
     * Method to insert a node with the given key in the bst structure.
     * @param key of the node to insert.
     * value -2 to a left or right pointer means that pointer is NULL.
     * value -1 to root index means that the tree is empty.
     * */
    @Override
    public void insert(int key) {

        /*Check if there is empty space to insert a node: */
        if(avail == END_OF_SPACE)//if avail=-1 there is available position.
            return;

        int arrayCurrentIdx = this.rootIndex;//current pointer.
        int parent = this.rootIndex;
        int newAvail;

        /*If tree is empty: */
        if(this.isEmpty()) {//avail==0
            this.rootIndex = avail;//new index of the root is avail.
            this.array[0][avail] = key;
            //update the two pointers:
            this.array[left][avail] = NULL;//left pointer is null.
            /*Before setting the right pointer equal to null
            * We have to update the avail value.The next avail value
            * is stored in the array[2][avail].*/
            newAvail = this.array[right][avail];
            this.array[right][avail] = NULL;//right pointer is null.
            this.avail = newAvail;
        }

        /*If tree is not empty: */
        else{
            /*traverse the bst until you find
            * the correct position to insert the new node. */
            //if current == -2 that means, current =Null.
            while(arrayCurrentIdx != NULL) {
                parent = arrayCurrentIdx;

                if (key < this.array[0][arrayCurrentIdx])//if key<currentNode.key
                    arrayCurrentIdx = this.array[left][arrayCurrentIdx];//current = current.left
                else//if key > currentNode.key
                    arrayCurrentIdx = this.array[right][arrayCurrentIdx];//current = current.right
            }
            //we found the parent of the new node.
            //find which child the new node will be.
            if(key < this.array[0][parent])//if key < parent.key , insert in the left pointer
                this.array[left][parent] = avail;//new node will be stored in avail idx!
            else
                this.array[right][parent] = avail;//new node will be stored in avail idx!

            //insert the new key:
            this.array[0][avail] = key;

            //update the pointers to null.
            this.array[left][avail] = NULL;//left = null

            //update the avail index
            newAvail = this.array[right][avail];

            this.array[right][avail] = NULL;//right = null
            this.avail = newAvail;

        }
    }

    /**
     * Method to remove a node from the bst structure.
     * @param key of the node to delete.
     * When we remove a node the index of this node
     * will be the new available position.The old available index
     * will be stored on the right index of the new avail.It's like
     * adding an element on a stack.So append the free list. 
     *  */
    @Override
    public void remove(int key) {
    	
    	if(this.isEmpty())
    		return;
    	
    	/*Firstly we need to check if the element exists. */
    	if(!this.find(key))
    		return;//if key does not exist,we cannot remove it.return.
    	int oldAvail,parent,curr = this.rootIndex;
    	parent = curr;
    	
    	/*Find the parent of the node to delete: */
    	while(this.array[0][curr] != key) {//iterate until curr index reach the node to delete.
    		parent = curr;
    		
    		if(key < this.array[0][curr])
    			curr = this.array[left][curr];
    		else 
    			curr = this.array[right][curr];	
    	}
    	//curr index points to the node to delete,and parent index is the parent of curr.
    	
    	
    	/*1) If node to delete is a leaf: */
    	
    	if(this.array[left][curr] == 0  &&  this.array[right][curr] == 0) //if left and right pointers are null,node is a leaf.
    		if(curr != this.rootIndex) {
    			
    			if(this.array[left][parent] == curr){//if curr is the left child of the parent.
    				//set the left pointer of the parent null
    				this.array[left][parent] = NULL;
    				oldAvail = this.avail;
    				// append the free list:
    				this.avail = curr; 
    				this.array[right][curr] = oldAvail;//old avail value will be the next position to insert an element.
    			}
    			else {//curr is the right child of the parent.
    				//set the right pointer of the parent null
    				this.array[right][parent] = NULL;
    				oldAvail = this.avail;
    				// append the free list:
    				this.avail = curr; 
    				this.array[right][curr] = oldAvail;//old avail value will be the next position to insert an element.
				}
    		}
    	
    		else // if node to delete is the root:
    			this.rootIndex = -1;
    	
    	/*2) Node to delete has one child(left or right) */
    	
    	else if(this.array[left][curr] == 0  ||  this.array[right][curr] == 0) {
    		
    		if(curr != this.rootIndex) {
    			
    		}
    		else {
				
			}
    	}
    	

    }

    
    /**
     * Method to find a key in the bst structure.
     * @param key to search
     * @return true if exists,else false.
     *  */
    @Override
    public boolean find(int key) {
    	
    	int curr = this.rootIndex;
    	
    	while(curr != NULL) 
    		if(key == this.array[0][curr])
    			return true;
    		
    		else if(key < this.array[0][curr])//if key < currentNode.getKey
    			curr = this.array[left][curr]; // curr = curr.left
    		
    		else // key > currentNode.getKey
    			curr = this.array[right][curr]; // curr = curr.right
    	
    	//to be here tree is empty or the key does not exist => return false
        return false;
    }

    /**
     * Method to print the binary search tree.
     * We do something like reverse inorder traversal.
     * First print right subtree,then root,and finally left subtree.
     * */
    @Override
    public void printBST() {
        if(this.isEmpty()) {
            System.out.println("Tree is empty!");
            return;
        }
        this.printHelp(this.rootIndex,0);
    }

    
    /**
     * Method to clear the bst data structure.
     * Initialize again the free list
     * and reset the root index. 
     *  */
    @Override
    public void clear() {
    	this.rootIndex = -1;
    	this.initializeFreeList();
    }

    @Override
    public boolean isEmpty(){
        return this.rootIndex == -1;
    }

    /**
     * Method used to print the bst on inorder traversal.
     * Uses recursion.
     * @param root of the tree/subtree and the number of spaces
     *             to print on the line in which the element will
     *             be printed.
     * We print 10 spaces for every tree level.
     * */
    private void printHelp(int root , int spaces){
        if(root == -2)//if index is -2 that means that pointer is null.Base case.
            return;

        /*Print Right subtree */
        printHelp(array[right][root],spaces + 10);//10 spaces for every tree level.

        /*print the root node */
        System.out.print("\n");
        for(int i=0; i<spaces; i++)
            System.out.print(" ");

        System.out.print(array[0][root]);
        System.out.print("\n");

        /*Print Left subtree */
        printHelp(this.array[left][root],spaces + 10);
    }
}
