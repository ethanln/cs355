package cs355.drawable.shape.factory;

import java.awt.geom.Point2D;

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
	
	private DrawableShape _getDrawableShape(Shape shape, Point2D.Double screenOrigin, double factor){
		int shapeType = ShapeType.toInt(shape.getShapeType());
		
		switch(shapeType){
		case 0:
			// if square
			return new DrawableSquare(shape, screenOrigin, factor);
		case 1:
			// if circle
			return new DrawableCircle(shape, screenOrigin, factor);
		case 2:
			// if ellipse
			return new DrawableEllipse(shape, screenOrigin, factor);
		case 3:
			// if line
			return new DrawableLine(shape, screenOrigin, factor);
		case 4: 
			// if rectangle
			return new DrawableRectangle(shape, screenOrigin, factor);
		case 5:
			// if triangle
			return new DrawableTriangle(shape, screenOrigin, factor);
		default:
			break;
		}
		return null;
	}
	
	public static DrawableShape getDrawableShape(Shape shape, Point2D.Double screenOrigin, double factor){
		return inst()._getDrawableShape(shape, screenOrigin, factor);
	}
}
