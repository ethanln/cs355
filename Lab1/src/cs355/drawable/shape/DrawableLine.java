package cs355.drawable.shape;

import java.awt.geom.Line2D;

import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;

public class DrawableLine extends DrawableShape{

	public DrawableLine(Shape shape){
		
		Line line = (Line)shape;
		super.shape = new Line2D.Double(line.getCenter().getX(), line.getCenter().getY(), line.getEnd().getX(), line.getEnd().getY());
		super.color = line.getColor();
	}
}
