package cs355.drawable.shape.factory;

import cs355.definitions.ShapeType;
import cs355.drawable.shape.*;
import cs355.dto.ConvertShapeToDrawableShapeDto;

public class DrawableShapeFactory {
	private static DrawableShapeFactory instance;
	
	private DrawableShapeFactory(){}
	
	private static DrawableShapeFactory inst(){
		if(instance == null){
			instance = new DrawableShapeFactory();
		}
		return instance;
	}
	
	private DrawableShape _getDrawableShape(ConvertShapeToDrawableShapeDto dto){
		int shapeType = ShapeType.toInt(dto.shape.getShapeType());
		
		switch(shapeType){
		case 0:
			// if square
			return new DrawableSquare(dto);
		case 1:
			// if circle
			return new DrawableCircle(dto);
		case 2:
			// if ellipse
			return new DrawableEllipse(dto);
		case 3:
			// if line
			return new DrawableLine(dto);
		case 4: 
			// if rectangle
			return new DrawableRectangle(dto);
		case 5:
			// if triangle
			return new DrawableTriangle(dto);
		default:
			break;
		}
		return null;
	}
	
	public static DrawableShape getDrawableShape(ConvertShapeToDrawableShapeDto dto){
		return inst()._getDrawableShape(dto);
	}
}
