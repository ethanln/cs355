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
		double distX = p.getX() - this.origin.getX();
		double distY = p.getY() - this.origin.getY();
		
		if(distX < 0.0 && distY < 0.0){
			// top left grid
			distX = Math.abs(p.getX() - this.origin.getX());
			distY = Math.abs(p.getY() - this.origin.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(new Point2D.Double(p.getX() + (distX / 2), p.getY() + (distY / 2)));
			
			rect.setCenter(newPoint);
		}
		else if(distX < 0.0 && distY >= 0.0){
			// bottom left grid
			distX = Math.abs(p.getX() - this.origin.getX());
			distY = Math.abs(p.getY() - this.origin.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(p.getX() + (distX / 2), p.getY() - (distY / 2));
			
			rect.setCenter(newPoint);
		}
		else if(distX >= 0.0 && distY < 0.0){
			// top right grid
			distX = Math.abs(p.getX() - this.origin.getX());
			distY = Math.abs(p.getY() - this.origin.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(p.getX() - (distX / 2), p.getY() + (distY / 2));
			
			rect.setCenter(newPoint);
		}
		else{
			distX = Math.abs(p.getX() - this.origin.getX());
			distY = Math.abs(p.getY() - this.origin.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(p.getX() - (distX / 2), p.getY() - (distY / 2));
			
			rect.setCenter(newPoint);
		}

		
		rect.setWidth(distX);
		rect.setHeight(distY);
	}

	@Override
	public Shape makeShape(Double p) {
		Rectangle rect = new Rectangle(super.selectedColor, p, 0.0, 0.0);
		rect.setShapeType(ShapeType.RECTANGLE);
		return rect;
	}

	@Override
	public void moveShape(Shape shape, Shape overlayBorder, Shape overlayHandle, Double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape makeShapeBorder(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape makeHandle(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}


}
