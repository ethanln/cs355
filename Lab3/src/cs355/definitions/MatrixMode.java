package cs355.definitions;

public enum MatrixMode {
	NONE,
	MODELVIEW,
	PROJECTION;
	/**
	 * Convert handle types to integer values
	 * @param type
	 * @return
	 */
	public static int toInt(MatrixMode mode){
		switch(mode){
			case NONE:
				return 0;
			case MODELVIEW:
				return 1;
			case PROJECTION:
				return 2;
			default:
				return 0;
		}
	}
}
