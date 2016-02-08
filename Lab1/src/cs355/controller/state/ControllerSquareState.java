package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;

public class ControllerSquareState extends ControllerState{

	@Override
	public void editShape(Shape shape, Double p) {
		
		Square square = (Square)shape;
		
		double x = p.getX() - origin.getX();
		double y = p.getY() - origin.getY();
		
		double minSize = 0.0;
		
		Point2D.Double newPoint = new Point2D.Double();
		if(x < 0.0 && y < 0.0){
			x = Math.abs(x);
			y = Math.abs(y);
			minSize += x < y ? x : y;
			
			newPoint.setLocation(this.origin.getX() - (minSize / 2), this.origin.getY() - (minSize / 2));
		}
		else if(x < 0.0 && y >= 0.0){
			x = Math.abs(x);
			y = Math.abs(y);
			minSize += x < y ? x : y;

			newPoint.setLocation(this.origin.getX() - (minSize / 2), this.origin.getY() + (minSize / 2));
		}
		else if(x >= 0.0 && y < 0.0){
			x = Math.abs(x);
			y = Math.abs(y);
			minSize += x < y ? x : y;

			newPoint.setLocation(this.origin.getX() + (minSize / 2), this.origin.getY() - (minSize / 2));
		}
		else{
			x = Math.abs(x);
			y = Math.abs(y);
			minSize += x < y ? x : y;

			newPoint.setLocation(this.origin.getX() + (minSize / 2), this.origin.getY() + (minSize / 2));
		}

		square.setCenter(newPoint);
		square.setSize(minSize);
	}

	@Override
	public Shape makeShape(Double p) {
		Square square = new Square(super.selectedColor, p, 0);
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
