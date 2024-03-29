package Assert.tuc.tsi.giorgos;

/**
 * A simple assertion mechanism for asserting validity of arguments.
 *
 */
public class Assert {

	static public void notFalse(boolean b) throws IllegalArgumentException {
		if (b == false)
			throw new IllegalArgumentException("boolean expression false");
	}

	
	static public void notNull(Object obj) throws IllegalArgumentException {
		if (obj == null)
			throw new IllegalArgumentException("null argument");
	}

	
	static public void notFalse(boolean b, String s) throws IllegalArgumentException {
		if (b == false)
			throw new IllegalArgumentException(s);
	}

	
	static public void notNull(Object obj, String s) throws IllegalArgumentException {
		if (obj == null)
			throw new IllegalArgumentException(s);
	}
	
}
