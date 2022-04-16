package bst.tuc.tsi.giorgos;

public class Main2 {

    public static void main(String args[]){
        BSTarrayImpl bst = new BSTarrayImpl();
        bst.insert(5);
        bst.insert(7);
        bst.printBST();
        bst.insert(3);
        bst.insert(9);
        bst.insert(6);
        bst.printBST();
        System.out.println("\n\n Test find method.");
        System.out.println(bst.find(5));
        System.out.println(bst.find(7));
        System.out.println(bst.find(6));
        System.out.println(bst.find(9));
        System.out.println(bst.find(69));
    }
}
