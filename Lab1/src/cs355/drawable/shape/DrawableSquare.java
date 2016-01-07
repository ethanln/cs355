package cs355.drawable.shape;

import java.awt.geom.Rectangle2D;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;


public class DrawableSquare extends DrawableShape{
	public DrawableSquare(Shape shape){
		
		Square square = (Square)shape;
		super.shape = new Rectangle2D.Double(square.getUpperLeft().getX(), square.getUpperLeft().getY(), square.getSize(), square.getSize());
		super.color = square.getColor();
	}
}
