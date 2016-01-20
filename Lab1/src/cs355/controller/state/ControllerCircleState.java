package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;

public class ControllerCircleState extends ControllerState{

	private Point2D.Double origin;
	
	@Override
	public void editShape(Shape shape, Double p) {
		
		Circle circle = (Circle)shape;
		
		double distX = p.getX() - this.origin.getX();
		double distY = p.getY() - this.origin.getY();
		
		if(distX < 0.0 && distY < 0.0){
			// top left grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			
			double newCenterX = (this.origin.getX() - (minDist / 2));
			double newCenterY = (this.origin.getY() - (minDist / 2));
			
			newPoint.setLocation(newCenterX, newCenterY);
			
			circle.setCenter(newPoint);
			circle.setRadius(minDist / 2);
		}
		else if(distX < 0.0 && distY >= 0.0){
			//bottom left grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			
			double newCenterX = (this.origin.getX() - (minDist / 2));
			double newCenterY = (this.origin.getY() + (minDist / 2));
			
			newPoint.setLocation(newCenterX, newCenterY);
			
			circle.setCenter(newPoint);
			circle.setRadius(minDist / 2);
		}
		else if(distX >= 0.0 && distY < 0.0){
			// top right grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			
			double newCenterX = (this.origin.getX() + (minDist / 2));
			double newCenterY = (this.origin.getY() - (minDist / 2));
			
			newPoint.setLocation(newCenterX, newCenterY);
			
			circle.setCenter(newPoint);
			circle.setRadius(minDist / 2);
		}
		else{
			// bottom right grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			
			double newCenterX = (this.origin.getX() + (minDist / 2));
			double newCenterY = (this.origin.getY() + (minDist / 2));
			
			newPoint.setLocation(newCenterX, newCenterY);
			
			circle.setCenter(newPoint);
			circle.setRadius(minDist / 2);
		}
	}

	@Override
	public Shape makeShape(Double p) {
		Circle circle = new Circle(super.selectedColor, p, 0.0);
		circle.setShapeType(ShapeType.CIRCLE);
		this.origin = p;
		return circle;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}

}
