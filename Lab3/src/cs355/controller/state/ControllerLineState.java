package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.dto.ConvertScreenToWorldDto;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;
import cs355.util.CoordinateConverterUtil;

public class ControllerLineState extends ControllerState{

	@Override
	public void editShape(Shape shape, Point2D.Double p) {
		
		Line line = (Line)shape;
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		line.setEnd(Pw);
	}

	@Override
	public Shape makeShape(Point2D.Double p) {
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		
		Line line = new Line(super.selectedColor, Pw, Pw);
		line.setShapeType(ShapeType.LINE);
		return line;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}
}
