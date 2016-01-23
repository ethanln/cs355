package cs355.definitions;

/**
 * Enumeration Tool types
 * @author ethan
 *
 */
public enum ToolType {
	SHAPE,
	SELECT,
	ZOOM_IN,
	ZOOM_OUT,
	DEFAULT;
	
	/**
	 * Convert tool types to integer values
	 * @param type
	 * @return
	 */
	public static int toInt(ToolType type){
		switch(type){
			case SHAPE:
				return 1;
			case SELECT:
				return 2;
			case ZOOM_IN:
				return 3;
			case ZOOM_OUT:
				return 4;
			case DEFAULT:
				return 5;
			default:
				return -1;
		}
	}
}
