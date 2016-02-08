package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.dto.ConvertScreenToWorldDto;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.util.CoordinateConverterUtil;

public class ControllerRectangleState extends ControllerState{

	@Override
	public void editShape(Shape shape, Double p) {
		Rectangle rect = (Rectangle)shape;
		
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		Point2D.Double Ow = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, this.origin, this.factor));
		
		double distX = Pw.getX() - Ow.getX();
		double distY = Pw.getY() - Ow.getY();
		
		if(distX < 0.0 && distY < 0.0){
			// top left grid
			distX = Math.abs(Pw.getX() - Ow.getX());
			distY = Math.abs(Pw.getY() - Ow.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(new Point2D.Double(Pw.getX() + (distX / 2), Pw.getY() + (distY / 2)));
			
			rect.setCenter(newPoint);
		}
		else if(distX < 0.0 && distY >= 0.0){
			// bottom left grid
			distX = Math.abs(Pw.getX() - Ow.getX());
			distY = Math.abs(Pw.getY() - Ow.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(Pw.getX() + (distX / 2), Pw.getY() - (distY / 2));
			
			rect.setCenter(newPoint);
		}
		else if(distX >= 0.0 && distY < 0.0){
			// top right grid
			distX = Math.abs(Pw.getX() - Ow.getX());
			distY = Math.abs(Pw.getY() - Ow.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(Pw.getX() - (distX / 2), Pw.getY() + (distY / 2));
			
			rect.setCenter(newPoint);
		}
		else{
			distX = Math.abs(Pw.getX() - Ow.getX());
			distY = Math.abs(Pw.getY() - Ow.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(Pw.getX() - (distX / 2), Pw.getY() - (distY / 2));
			
			rect.setCenter(newPoint);
		}

		
		rect.setWidth(distX);
		rect.setHeight(distY);
	}

	@Override
	public Shape makeShape(Double p) {
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		Rectangle rect = new Rectangle(super.selectedColor, Pw, 0.0, 0.0);
		rect.setShapeType(ShapeType.RECTANGLE);
		return rect;
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
