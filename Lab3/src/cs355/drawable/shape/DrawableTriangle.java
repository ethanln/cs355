package cs355.drawable.shape;

import java.awt.Polygon;
import cs355.dto.ConvertShapeToDrawableShapeDto;
import cs355.model.drawing.Triangle;

public class DrawableTriangle extends DrawableShape{
	public DrawableTriangle(ConvertShapeToDrawableShapeDto dto){
		
		Triangle triangle = (Triangle)dto.shape;
		super.ConvertObjToScreen(triangle.getCenter(), dto.screenOrigin, triangle.getRotation(), dto.factor);
		
		super.shape = new Polygon(new int[]{(int)triangle.getA().getX(), (int)triangle.getB().getX(), (int)triangle.getC().getX()}, 
								  new int[]{(int)triangle.getA().getY(), (int)triangle.getB().getY(), (int)triangle.getC().getY()}, 3);
		super.color = triangle.getColor();
	}
}
