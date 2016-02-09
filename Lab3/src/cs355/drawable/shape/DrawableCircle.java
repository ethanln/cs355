package cs355.drawable.shape;

import java.awt.geom.Ellipse2D;

import cs355.dto.ConvertShapeToDrawableShapeDto;
import cs355.model.drawing.Circle;

public class DrawableCircle extends DrawableShape{
	
	public DrawableCircle(ConvertShapeToDrawableShapeDto dto){
		
		Circle circle = (Circle)dto.shape;
		super.ConvertObjToScreen(circle.getCenter(), dto.screenOrigin, circle.getRotation(), dto.factor);
		super.shape = new Ellipse2D.Double(-(circle.getRadius()), -(circle.getRadius()), circle.getRadius() * 2, circle.getRadius() * 2);
		super.color = circle.getColor();
	}
}
