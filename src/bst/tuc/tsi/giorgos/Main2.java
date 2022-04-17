package bst.tuc.tsi.giorgos;

public class Main2 {

    public static void main(String args[]){
        BSTarrayImpl binarySearchTree = new BSTarrayImpl();
        System.out.println("Test insert Method:");

        binarySearchTree.insert(5);
        binarySearchTree.insert(3);
        binarySearchTree.insert(9);
        binarySearchTree.insert(10);
        binarySearchTree.insert(6);
        binarySearchTree.insert(4);

        binarySearchTree.printBST();

        binarySearchTree.insert(2);
        binarySearchTree.insert(14);
        binarySearchTree.insert(7);
        binarySearchTree.insert(1);
        System.out.println("*".repeat(60));
        binarySearchTree.printBST();

        System.out.println("Search find Method:");
        System.out.println("*".repeat(60));
        /*Existing keys: */
        System.out.println(binarySearchTree.find(10));
        System.out.println(binarySearchTree.find(14));
        System.out.println(binarySearchTree.find(5));
        System.out.println(binarySearchTree.find(6));
        System.out.println(binarySearchTree.find(4));
        System.out.println(binarySearchTree.find(2));

        /*Non existing keys */

        System.out.println(binarySearchTree.find(0));
        System.out.println(binarySearchTree.find(100));
        System.out.println(binarySearchTree.find(8));


        /*Test delete method" */
        System.out.println("Test remove method!\n");
        System.out.println("1)Remove a leaf:1");
        binarySearchTree.remove(1);
        binarySearchTree.printBST();
        System.out.println("\n\n2)Remove a node with one child(right child):6");
        binarySearchTree.remove(6);
        binarySearchTree.printBST();

        System.out.println("\n\n2)Remove a node leaf:4");
        binarySearchTree.remove(4);
        binarySearchTree.printBST();

        System.out.println("\n\n2)Remove a node with one child(left child):3");
        binarySearchTree.remove(3);
        binarySearchTree.printBST();

        System.out.println("\n\n3)Remove a node with two children:9");
        binarySearchTree.remove(9);
        binarySearchTree.printBST();

        System.out.println("\n\n3)Remove a node with two children:10");
        binarySearchTree.remove(10);
        binarySearchTree.printBST();

        System.out.println("\n\n3)Remove a node with two children(root):5");
        binarySearchTree.remove(5);
        binarySearchTree.printBST();

        System.out.println("\n\n3)Remove a node which does not exist: 23");
        binarySearchTree.remove(23);
        binarySearchTree.printBST();

        System.out.println("\n\n3)Remove all the nodes and try to remove a key from an empty tree:");
        binarySearchTree.remove(14);
        binarySearchTree.remove(2);
        binarySearchTree.remove(7);
        /* empty tree */
        binarySearchTree.remove(7);
        binarySearchTree.printBST();

    }
}

