package cs355.definitions;

public enum ShapeType {
	SQUARE,
	CIRCLE,
	ELLIPSE,
	LINE,
	RECTANGLE,
	TRIANGLE,
	NONE;
	
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
