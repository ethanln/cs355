package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.dto.ConvertScreenToWorldDto;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;
import cs355.util.CoordinateConverterUtil;

public class ControllerSquareState extends ControllerState{

	@Override
	public void editShape(Shape shape, Double p) {
		
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		Point2D.Double Ow = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, this.origin, this.factor));
		
		Square square = (Square)shape;
		
		double x = Pw.getX() - Ow.getX();
		double y = Pw.getY() - Ow.getY();
		
		double minSize = 0.0;
		
		Point2D.Double newPoint = new Point2D.Double();
		if(x < 0.0 && y < 0.0){
			x = Math.abs(x);
			y = Math.abs(y);
			minSize += x < y ? x : y;
			
			newPoint.setLocation(Ow.getX() - (minSize / 2), Ow.getY() - (minSize / 2));
		}
		else if(x < 0.0 && y >= 0.0){
			x = Math.abs(x);
			y = Math.abs(y);
			minSize += x < y ? x : y;

			newPoint.setLocation(Ow.getX() - (minSize / 2), Ow.getY() + (minSize / 2));
		}
		else if(x >= 0.0 && y < 0.0){
			x = Math.abs(x);
			y = Math.abs(y);
			minSize += x < y ? x : y;

			newPoint.setLocation(Ow.getX() + (minSize / 2), Ow.getY() - (minSize / 2));
		}
		else{
			x = Math.abs(x);
			y = Math.abs(y);
			minSize += x < y ? x : y;

			newPoint.setLocation(Ow.getX() + (minSize / 2), Ow.getY() + (minSize / 2));
		}

		square.setCenter(newPoint);
		square.setSize(minSize);
	}

	@Override
	public Shape makeShape(Double p) {
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		Square square = new Square(super.selectedColor, Pw, 0);
		square.setShapeType(ShapeType.SQUARE);
		return square;
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
