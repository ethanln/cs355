package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;

public class ControllerCircleState extends ControllerState{

	@Override
	public void editShape(Shape shape, Double p) {
		Circle circle = (Circle)shape;
		
		double distX = p.getX() - circle.getOppositePoint().getX();
		double distY = p.getY() - circle.getOppositePoint().getY();
		double distXY = 0.0;
			
		if(distX < 0.0 && distY < 0.0){
			//top left grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			newPoint.setLocation(circle.getOppositePoint().getX() - minDist, circle.getOppositePoint().getY() - minDist);
			circle.setCenter(newPoint);
		}
		else if(distX < 0.0 && distY >= 0.0){
			//bottom left grid
			if(Math.abs(distY) > Math.abs(distX)){
				Point2D.Double newPoint = new Point2D.Double();
				newPoint.setLocation(p.getX(), circle.getOppositePoint().getY());
				circle.setCenter(newPoint);
			}
			else{
				Point2D.Double newPoint = new Point2D.Double();
				double subVal = Math.abs(distX) - Math.abs(distY);
				newPoint.setLocation(p.getX() + subVal, circle.getOppositePoint().getY());
				circle.setCenter(newPoint);
			}
		}
		else if(distX >= 0.0 && distY < 0.0){
			// top right grid
			if(Math.abs(distY) < Math.abs(distX)){
				Point2D.Double newPoint = new Point2D.Double();
				newPoint.setLocation(circle.getOppositePoint().getX(), p.getY());
				circle.setCenter(newPoint);
			}
			else{
				Point2D.Double newPoint = new Point2D.Double();
				double subVal = Math.abs(distY) - Math.abs(distX);
				newPoint.setLocation(circle.getOppositePoint().getX(), p.getY() + subVal);
				circle.setCenter(newPoint);
			}
		}
		else{
			circle.setCenter(circle.getOppositePoint());
		}
		
		distX = Math.abs(p.getX() - circle.getOppositePoint().getX());
		distY = Math.abs(p.getY() - circle.getOppositePoint().getY());
		
		distXY = distX < distY ? distX : distY;
		
		circle.setRadius(distXY);
		
	}

	@Override
	public Shape makeShape(Double p) {
		Circle circle = new Circle(super.selectedColor, p, p, 0.0);
		circle.setShapeType(ShapeType.CIRCLE);
		return circle;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}

}
