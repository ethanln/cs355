package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.dto.ConvertScreenToWorldDto;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;
import cs355.util.CoordinateConverterUtil;

public class ControllerCircleState extends ControllerState{
	
	@Override
	public void editShape(Shape shape, Double p) {
		
		Circle circle = (Circle)shape;
		
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		Point2D.Double Ow = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, this.origin, this.factor));
		
		double distX = Pw.getX() - Ow.getX();
		double distY = Pw.getY() - Ow.getY();
		
		if(distX < 0.0 && distY < 0.0){
			// top left grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			
			double newCenterX = (Ow.getX() - (minDist / 2));
			double newCenterY = (Ow.getY() - (minDist / 2));
			
			newPoint.setLocation(newCenterX, newCenterY);
			
			circle.setCenter(newPoint);
			circle.setRadius(minDist / 2);
		}
		else if(distX < 0.0 && distY >= 0.0){
			//bottom left grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			
			double newCenterX = (Ow.getX() - (minDist / 2));
			double newCenterY = (Ow.getY() + (minDist / 2));
			
			newPoint.setLocation(newCenterX, newCenterY);
			
			circle.setCenter(newPoint);
			circle.setRadius(minDist / 2);
		}
		else if(distX >= 0.0 && distY < 0.0){
			// top right grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			
			double newCenterX = (Ow.getX() + (minDist / 2));
			double newCenterY = (Ow.getY() - (minDist / 2));
			
			newPoint.setLocation(newCenterX, newCenterY);
			
			circle.setCenter(newPoint);
			circle.setRadius(minDist / 2);
		}
		else{
			// bottom right grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			
			double newCenterX = (Ow.getX() + (minDist / 2));
			double newCenterY = (Ow.getY() + (minDist / 2));
			
			newPoint.setLocation(newCenterX, newCenterY);
			
			circle.setCenter(newPoint);
			circle.setRadius(minDist / 2);
		}
	}

	@Override
	public Shape makeShape(Double p) {
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		Circle circle = new Circle(super.selectedColor, Pw, 0.0);
		circle.setShapeType(ShapeType.CIRCLE);
		return circle;
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
