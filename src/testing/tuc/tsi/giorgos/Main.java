package testing.tuc.tsi.giorgos;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int[] elDelete = Load.elementsToDelete();
		
		for(int i=0 ; i<elDelete.length; i++)
			System.out.print( elDelete[i] + " ");
		
		int[] elInsert = Load.elementsToInsert();
		System.out.println("\n");
		for(int i=0 ; i< 10; i++)
			System.out.println(elInsert[i] + " ");

	}

}
