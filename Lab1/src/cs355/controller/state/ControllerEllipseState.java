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
		double distX = p.getX() - this.origin.getX();
		double distY = p.getY() - this.origin.getY();
		
		if(distX < 0.0 && distY < 0.0){
			// top left grid
			distX = Math.abs(p.getX() - this.origin.getX());
			distY = Math.abs(p.getY() - this.origin.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(new Point2D.Double(p.getX() + (distX / 2), p.getY() + (distY / 2)));
			
			ellipse.setCenter(newPoint);
		}
		else if(distX < 0.0 && distY >= 0.0){
			// bottom left grid
			distX = Math.abs(p.getX() - this.origin.getX());
			distY = Math.abs(p.getY() - this.origin.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(p.getX() + (distX / 2), p.getY() - (distY / 2));
			
			ellipse.setCenter(newPoint);
		}
		else if(distX >= 0.0 && distY < 0.0){
			// top right grid
			distX = Math.abs(p.getX() - this.origin.getX());
			distY = Math.abs(p.getY() - this.origin.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(p.getX() - (distX / 2), p.getY() + (distY / 2));
			
			ellipse.setCenter(newPoint);
		}
		else{
			distX = Math.abs(p.getX() - this.origin.getX());
			distY = Math.abs(p.getY() - this.origin.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(p.getX() - (distX / 2), p.getY() - (distY / 2));
			
			ellipse.setCenter(newPoint);
		}

		
		ellipse.setWidth(distX);
		ellipse.setHeight(distY);
	}

	@Override
	public Shape makeShape(Double p) {
		Ellipse ellipse = new Ellipse(this.selectedColor, p, 0.0, 0.0);
		ellipse.setShapeType(ShapeType.ELLIPSE);
		return ellipse;
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
