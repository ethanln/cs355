package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.HandleType;
import cs355.definitions.ShapeType;
import cs355.dto.ConvertScreenToObjDto;
import cs355.dto.ConvertScreenToWorldDto;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;
import cs355.util.CoordinateConverterUtil;

public class ControllerSelectState extends ControllerState{
	
	@Override
	public void editShape(Shape shape, Double p) {
		shape.setColor(this.selectedColor);
	}

	@Override
	public Shape makeShape(Double p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		
		//Point2D.Double _screenOrigin, Point2D.Double _pt, double _factor
		Point2D.Double Pw = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, p, this.factor));
		Point2D.Double Ow = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(this.screenOrigin, this.origin, this.factor));
		
		//calculate vector
		double vectorX = Ow.getX() - Pw.getX();
		double vectorY = Ow.getY() - Pw.getY();
		
		// if the shape is a line
		if(shape.getShapeType() == ShapeType.LINE){
			
			//calculate end point
			Line line = (Line)shape;
			Point2D.Double newEnd = new Point2D.Double();
			newEnd.setLocation(line.getEnd().getX() - vectorX, line.getEnd().getY() - vectorY);
			
			// set end point of line
			line.setEnd(newEnd);
		}
		
		// calculate center
		Point2D.Double newCenter = new Point2D.Double();
		newCenter.setLocation(shape.getCenter().getX() - vectorX, shape.getCenter().getY() - vectorY);
		
		// set the shape center
		shape.setCenter(newCenter);

		// set the new origin
		this.origin = p;
	}

	@Override
	public void rotateShape(Shape shape, Double p) {
		
		if(shape.getShapeType() == ShapeType.LINE){ // if the shape is a line
			Point2D.Double pW = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(screenOrigin, 
																											p, factor));
			Line line = (Line)shape;
			if(super.getRotationHandle() == HandleType.LINE_START){ // if the handle is at the start of the line
				line.setCenter(pW);
			}
			else if(super.getRotationHandle() == HandleType.LINE_END){ // if the handle is at the end of the line
				line.setEnd(pW);
			}
		}
		else{ // if the shape is not a line
			// instantiate dto to be passed into the converter
			//ConvertWorldToObjDto dto = new ConvertWorldToObjDto(p, shape.getCenter(), shape.getRotation());
			// convert the point of interst to object coordinates
			//Point2D.Double objCoor = CoordinateConverterUtil.convertWorldToObject(dto);
			Point2D.Double objCoor = CoordinateConverterUtil.convertScreenToObj(new ConvertScreenToObjDto(super.factor, 
																										  shape.getRotation(),
																										  shape.getCenter(),
																										  super.getScreenOrigin(),
																										  p));
			
			//double newRotation = shape.getRotation() + Math.atan(-(objCoor.getX() / objCoor.getY()));
			double newRotation = shape.getRotation() + Math.atan2(objCoor.getX(), -objCoor.getY());
			shape.setRotation(newRotation);
		}
	}
}
