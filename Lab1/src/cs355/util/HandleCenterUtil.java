package cs355.util;

import java.awt.geom.Point2D;

import cs355.definitions.ShapeType;
import cs355.dto.ConvertWorldToObjDto;
import cs355.model.drawing.*;

public class HandleCenterUtil implements IUtil{

	@Override
	public Object doUtil(Object o) {
		Shape shape = (Shape)o;
		int type = ShapeType.toInt(shape.getShapeType());
		
		switch(type){
			case 0:
				// square
				Square square = (Square)shape;
				return this.getCenter(shape, (square.getSize() / 2));
			case 1:
				// circle
				Circle circle = (Circle)shape;
				return this.getCenter(shape, (circle.getRadius()));
				
			case 2:
				// ellipse
				Ellipse ellipse = (Ellipse)shape;
				return this.getCenter(shape, (ellipse.getHeight() / 2));
				
			case 3:
				// line
				 Line line = (Line)shape;
				return line.getEnd();
				
			case 4:
				// rectangle
				Rectangle rectangle = (Rectangle)shape;
				return this.getCenter(shape, ((rectangle.getHeight() / 2)));
				
			case 5:
				// triangle
				Triangle triangle = (Triangle)shape;
				
				double lowestYPoint = Math.min(triangle.getA().getY(), triangle.getB().getY());
				lowestYPoint = Math.min(lowestYPoint, triangle.getC().getY());
				
				if(lowestYPoint < 0.0){
					return this.getCenter(shape, -(lowestYPoint));
				}
				else{
					return this.getCenter(shape, (lowestYPoint));
				}	
				
			default:
				return null;
		}
		
	}
	
	private Point2D.Double getCenter(Shape shape, double dist){
		// get world to object coordinates converter from util factory
		WorldToObjectConverterUtil converter = (WorldToObjectConverterUtil)UtilFactory.makeUtil("world_to_object_converter");
		
		// instantiate dto to be passed into the converter
		ConvertWorldToObjDto dto = new ConvertWorldToObjDto(new Point2D.Double(shape.getCenter().getX(), shape.getCenter().getY() - dist - 20), shape.getCenter(), shape.getRotation());
		
		// convert the point of interst to object coordinates
		Point2D.Double objCoor = (Point2D.Double)converter.doUtil(dto);
		
		return new Point2D.Double(shape.getCenter().getX() - objCoor.getX(), shape.getCenter().getY() + objCoor.getY());
	}

}
