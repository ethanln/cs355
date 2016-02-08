package cs355.drawable.shape;

import java.awt.Polygon;
import java.awt.geom.Point2D;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Triangle;

public class DrawableTriangle extends DrawableShape{
	public DrawableTriangle(Shape shape, Point2D.Double screenOrigin, double factor){
		
		Triangle triangle = (Triangle)shape;
		super.ConvertObjToScreen(triangle.getCenter(), screenOrigin, triangle.getRotation(), factor);
		
		super.shape = new Polygon(new int[]{(int)triangle.getA().getX(), (int)triangle.getB().getX(), (int)triangle.getC().getX()}, 
								  new int[]{(int)triangle.getA().getY(), (int)triangle.getB().getY(), (int)triangle.getC().getY()}, 3);
		super.color = triangle.getColor();
	}
}
