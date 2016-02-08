package cs355.drawable.shape;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;


public class DrawableSquare extends DrawableShape{
	public DrawableSquare(Shape shape, Point2D.Double screenOrigin, double factor){
		
		Square square = (Square)shape;
		super.ConvertObjToScreen(square.getCenter(), screenOrigin, square.getRotation(), factor);
		
		super.shape = new Rectangle2D.Double(-(square.getSize() / 2), -(square.getSize() / 2), square.getSize(), square.getSize());
		super.color = square.getColor();
	}
}
