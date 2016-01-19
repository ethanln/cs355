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
		
		double x = square.getOppositePoint().getX();
		double y = square.getOppositePoint().getY();
		double w = p.getX() - x;
		double h = p.getY() - y;
		
		double minSize = 0.0;
		
		if(w < 0.0 && h < 0.0){

			x = Math.min(p.getX(), square.getOppositePoint().getX());
			w = Math.abs(square.getOppositePoint().getX() - p.getX());

			y = Math.min(p.getY(), square.getOppositePoint().getY());
			h = Math.abs(square.getOppositePoint().getY() - p.getY());
			
			minSize += w < h ? w : h;
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(square.getOppositePoint().getX() - minSize, square.getOppositePoint().getY() - minSize);
			
			square.setUpperLeft(newPoint);
		}
		else if(w < 0.0 && h >= 0.0){
			x = Math.min(p.getX(), square.getOppositePoint().getX());
			w = Math.abs(square.getOppositePoint().getX() - p.getX());

			y = Math.min(p.getY(), square.getOppositePoint().getY());
			h = Math.abs(square.getOppositePoint().getY() - p.getY());
			
			minSize += w < h ? w : h;
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(square.getOppositePoint().getX() - minSize, square.getOppositePoint().getY());
			
			square.setUpperLeft(newPoint);
		}
		else if(w >= 0.0 && h < 0.0){
			x = Math.min(p.getX(), square.getOppositePoint().getX());
			w = Math.abs(square.getOppositePoint().getX() - p.getX());

			y = Math.min(p.getY(), square.getOppositePoint().getY());
			h = Math.abs(square.getOppositePoint().getY() - p.getY());
			
			minSize += w < h ? w : h;
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(square.getOppositePoint().getX(), square.getOppositePoint().getY() - minSize);
			
			square.setUpperLeft(newPoint);
		}
		else{
			square.setUpperLeft(square.getOppositePoint());
			minSize = w < h ? w : h;
		}

		square.setSize(minSize);
	}

	@Override
	public Shape makeShape(Double p) {
		Square square = new Square(super.selectedColor, p, p, 0);
		square.setShapeType(ShapeType.SQUARE);
		return square;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}


}
