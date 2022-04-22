package testing.tuc.tsi.giorgos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author giorgos tsi
 * An auxiliary class to load
 * from the files the given elements.
 * These elements will be inserted in 
 * every structure of this project to count
 * total time taken for insertion and deletion.
 * keys_1000000_BE.bin file has the elements for
 * insertion,and the other file has the elements
 * for deletion.We will load these elements from the file
 * and return an integer array with these elements.All the
 * tests will be in another class.
 *  */
public class Load {
	
	/*Random access file object to read from the file. */
	private static RandomAccessFile raf;
	
	
	/**
	 * Static method to load all the elements to insert
	 * in an int array.
	 * @return int array with the elements to insert.
	 * @throws IOException
	 *  */
	public static int[] elementsToInsert() throws IOException {
		
		/*Open the stream with the keys to insert file: */
		raf = new RandomAccessFile("keys_1000000_BE.bin", "r");
		
		/*int array to return: */
		int[] elementsToInsert = new int[1000000];//10^6 elements
		
		for(int i=0 ; i<1000000; i++)
			elementsToInsert[i] = raf.readInt();//read integers from the file and store them to the array.
		
		
		return elementsToInsert;
	}
	
	/**
	 * Static method to load all the elements to delete
	 * in an int array.
	 * @return int array with the elements to delete.
	 * @throws IOException
	 *  */
	public static int[] elementsToDelete() throws IOException {
		
		/*Open the stream with the keys to insert file: */
		raf = new RandomAccessFile("keys_del_100_BE.bin", "r");
		
		/*int array to return: */
		int[] elementsToDelete = new int[100];//100 elements
		
		for(int i=0 ; i<100; i++)
			elementsToDelete[i] = raf.readInt();//read integers from the file and store them to the array.
		
		
		return elementsToDelete;
	}

}
