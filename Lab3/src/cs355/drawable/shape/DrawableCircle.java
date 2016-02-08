package cs355.drawable.shape;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;

public class DrawableCircle extends DrawableShape{
	
	public DrawableCircle(Shape shape, Point2D.Double screenOrigin, double factor){
		
		Circle circle = (Circle)shape;
		super.ConvertObjToScreen(circle.getCenter(), screenOrigin, circle.getRotation(), factor);
		super.shape = new Ellipse2D.Double(-(circle.getRadius()), -(circle.getRadius()), circle.getRadius() * 2, circle.getRadius() * 2);
		super.color = circle.getColor();
	}
}
