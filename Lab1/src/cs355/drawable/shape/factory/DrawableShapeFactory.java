package cs355.drawable.shape.factory;

import cs355.definitions.ShapeType;
import cs355.drawable.shape.*;
import cs355.model.drawing.Shape;

public class DrawableShapeFactory {
	private static DrawableShapeFactory instance;
	
	private DrawableShapeFactory(){}
	
	private static DrawableShapeFactory inst(){
		if(instance == null){
			instance = new DrawableShapeFactory();
		}
		return instance;
	}
	
	private DrawableShape _getDrawableShape(Shape shape){
		int shapeType = ShapeType.toInt(shape.getShapeType());
		
		switch(shapeType){
		case 0:
			// if square
			return new DrawableSquare(shape);
		case 1:
			// if circle
			return new DrawableCircle(shape);
		case 2:
			// if ellipse
			return new DrawableEllipse(shape);
		case 3:
			// if line
			return new DrawableLine(shape);
		case 4: 
			// if rectangle
			return new DrawableRectangle(shape);
		case 5:
			// if triangle
			return new DrawableTriangle(shape);
		default:
			break;
		}
		return null;
	}
	
	public static DrawableShape getDrawableShape(Shape shape){
		return inst()._getDrawableShape(shape);
	}
}
