package cs355.definitions;

/**
 * Enumeration handle types
 * @author ethan
 */
public enum HandleType {
	NONE,
	SHAPE_HANDLE,
	LINE_START,
	LINE_END;
	/**
	 * Convert handle types to integer values
	 * @param type
	 * @return
	 */
	public static int toInt(HandleType type){
		switch(type){
			case NONE:
				return 0;
			case SHAPE_HANDLE:
				return 1;
			case LINE_START:
				return 2;
			case LINE_END:
				return 3;
			default:
				return 0;
		}
	}
}
