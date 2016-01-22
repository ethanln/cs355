package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;

public class ControllerLineState extends ControllerState{

	@Override
	public void editShape(Shape shape, Point2D.Double p) {
		Line line = (Line)shape;
		line.setEnd(p);
	}

	@Override
	public Shape makeShape(Point2D.Double p) {
		Line line = new Line(super.selectedColor, p, p);
		line.setShapeType(ShapeType.LINE);
		return line;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}
}
