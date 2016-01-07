package cs355.drawable.shape;

import java.awt.geom.Rectangle2D;

import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;

public class DrawableRectangle extends DrawableShape{
	
	public DrawableRectangle(Shape shape){
		
		Rectangle rect = (Rectangle)shape;
		super.shape = new Rectangle2D.Double(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(), rect.getWidth(), rect.getHeight());
		super.color = rect.getColor();
	}
}
