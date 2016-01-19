package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;

public class ControllerRectangleState extends ControllerState{

	@Override
	public void editShape(Shape shape, Double p) {
		Rectangle rect = (Rectangle)shape;
		double x = rect.getOppositePoint().getX();
		double y = rect.getOppositePoint().getY();
		double w = p.getX() - x;
		double h = p.getY() - y;
		
		if(w < 0.0 && h < 0.0){
			x = Math.min(p.getX(), rect.getOppositePoint().getX());
			w = Math.abs(rect.getOppositePoint().getX() - p.getX());

			y = Math.min(p.getY(), rect.getOppositePoint().getY());
			h = Math.abs(rect.getOppositePoint().getY() - p.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(rect.getOppositePoint().getX() - w, rect.getOppositePoint().getY() - h);
			
			rect.setUpperLeft(newPoint);
			
		}
		else if(w < 0.0 && h >= 0.0){
			x = Math.min(p.getX(), rect.getOppositePoint().getX());
			w = Math.abs(rect.getOppositePoint().getX() - p.getX());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(rect.getOppositePoint().getX() - w, rect.getOppositePoint().getY());
			rect.setUpperLeft(newPoint);
		}
		else if(w >= 0.0 && h < 0.0){
			y = Math.min(p.getY(), rect.getOppositePoint().getY());
			h = Math.abs(rect.getOppositePoint().getY() - p.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(rect.getOppositePoint().getX(), rect.getOppositePoint().getY() - h);
			rect.setUpperLeft(newPoint);
		}
		else{
			rect.setUpperLeft(rect.getOppositePoint());
		}
		
		rect.setWidth(w);
		rect.setHeight(h);
	}

	@Override
	public Shape makeShape(Double p) {
		Rectangle rect = new Rectangle(super.selectedColor, p, p, 0.0, 0.0);
		rect.setShapeType(ShapeType.RECTANGLE);
		return rect;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}


}
