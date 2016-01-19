package cs355.definitions;

public enum ToolType {
	SHAPE,
	SELECT,
	ZOOM_IN,
	ZOOM_OUT,
	DEFAULT;
	
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
