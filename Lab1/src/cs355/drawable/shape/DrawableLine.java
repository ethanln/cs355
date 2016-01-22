package cs355.drawable.shape;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;

public class DrawableLine extends DrawableShape{

	public DrawableLine(Shape shape){
		
		Line line = (Line)shape;

		super.ConvertObjToWorld(new Point2D.Double(0.0, 0.0), line.getRotation());
		super.shape = new Line2D.Double(line.getCenter().getX(), line.getCenter().getY(), line.getEnd().getX(), line.getEnd().getY());
		super.color = line.getColor();
	}
}
