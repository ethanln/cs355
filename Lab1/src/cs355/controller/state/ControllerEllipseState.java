package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Shape;

public class ControllerEllipseState extends ControllerState{

	@Override
	public void editShape(Shape shape, Double p) {
		Ellipse ellipse = (Ellipse)shape;
		double distX = p.getX() - ellipse.getOppositePoint().getX();
		double distY = p.getY() - ellipse.getOppositePoint().getY();
		
		if(distX < 0.0 && distY < 0.0){
			ellipse.setCenter(p);
		}
		else if(distX < 0.0 && distY >= 0.0){
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(p.getX(), ellipse.getOppositePoint().getY());
			ellipse.setCenter(newPoint);
		}
		else if(distX >= 0.0 && distY < 0.0){
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(ellipse.getOppositePoint().getX(), p.getY());
			ellipse.setCenter(newPoint);
		}
		else{
			ellipse.setCenter(ellipse.getOppositePoint());
		}
		
		distX = Math.abs(p.getX() - ellipse.getOppositePoint().getX());
		distY = Math.abs(p.getY() - ellipse.getOppositePoint().getY());
		
		ellipse.setWidth(distX);
		ellipse.setHeight(distY);
	}

	@Override
	public Shape makeShape(Double p) {
		Ellipse ellipse = new Ellipse(this.selectedColor, p, p, 0.0, 0.0);
		ellipse.setShapeType(ShapeType.ELLIPSE);
		return ellipse;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}


}
