package cs355.drawable.shape;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;

public class DrawableRectangle extends DrawableShape{
	
	public DrawableRectangle(Shape shape, Point2D.Double screenOrigin, double factor){
		
		Rectangle rect = (Rectangle)shape;
		super.ConvertObjToScreen(rect.getCenter(), screenOrigin, rect.getRotation(), factor);
		
		super.shape = new Rectangle2D.Double(-(rect.getWidth() / 2), -(rect.getHeight() / 2), rect.getWidth(), rect.getHeight());
		super.color = rect.getColor();
	}
}
