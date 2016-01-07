package cs355.definitions;

public enum ToolType {
	SQUARE,
	CIRCLE,
	ELLIPSE,
	LINE,
	RECTANGLE,
	TRIANGLE,
	SELECT,
	ZOOM_IN,
	ZOOM_OUT,
	NONE;
	
	public static int toInt(ToolType type){
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
			case SELECT:
				return 6;
			case ZOOM_IN:
				return 7;
			case ZOOM_OUT:
				return 8;
			case NONE:
				return 7;
			default:
				return -1;
		}
	}
}
