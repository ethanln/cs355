package cs355.drawable.shape;

import java.awt.Polygon;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Triangle;

public class DrawableTriangle extends DrawableShape{
	public DrawableTriangle(Shape shape){
		
		Triangle triangle = (Triangle)shape;
		
		if(triangle.isHandle() || triangle.isSelectedBorder()){
			super.isSelectedOverlay = true;
		}
		
		super.ConvertObjToWorld(triangle.getCenter(), triangle.getRotation());
		super.shape = new Polygon(new int[]{(int)triangle.getA().getX(), (int)triangle.getB().getX(), (int)triangle.getC().getX()}, 
								  new int[]{(int)triangle.getA().getY(), (int)triangle.getB().getY(), (int)triangle.getC().getY()}, 3);
		super.color = triangle.getColor();
	}
}
