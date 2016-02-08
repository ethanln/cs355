package cs355.definitions;
/**
 * Enumeration shape types
 * @author ethan
 */
public enum ShapeType {
	SQUARE,
	CIRCLE,
	ELLIPSE,
	LINE,
	RECTANGLE,
	TRIANGLE,
	NONE;
	
	/**
	 * Convert shape types to integer values
	 * @param type
	 * @return
	 */
	public static int toInt(ShapeType type){
		switch(type){
			case SQUARE:
				return 0;
			case CIRCLE:
				return 1;
			case ELLIPSE:
				return 2;
			case LINE:
				return 3;
			case RECTANGLE:
				return 4;
			case TRIANGLE:
				return 5;
			default:
				return -1;
		}
	}
}
