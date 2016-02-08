package cs355.util;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.definitions.HandleType;
import cs355.definitions.ShapeType;
import cs355.dto.ConvertWorldToObjDto;
import cs355.dto.PointInHandleDto;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Line;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;
import cs355.model.drawing.Triangle;

public class HandleUtil {
	private static HandleUtil instance;
	
	private HandleUtil(){}
	
	private static HandleUtil inst(){
		if(instance == null){
			instance = new HandleUtil();
		}
		return instance;
	}
	
	private Point2D.Double _getHandleCenter(Shape shape, double factor) {
		int type = ShapeType.toInt(shape.getShapeType());
		
		switch(type){
			case 0:
				// square
				Square square = (Square)shape;
				return this.getCenter(shape, (square.getSize() / 2), factor);
			case 1:
				// circle
				Circle circle = (Circle)shape;
				return this.getCenter(shape, (circle.getRadius()), factor);
				
			case 2:
				// ellipse
				Ellipse ellipse = (Ellipse)shape;
				return this.getCenter(shape, (ellipse.getHeight() / 2), factor);
				
			case 3:
				// line
				 Line line = (Line)shape;
				return line.getEnd();
				
			case 4:
				// rectangle
				Rectangle rectangle = (Rectangle)shape;
				return this.getCenter(shape, ((rectangle.getHeight() / 2)), factor);
				
			case 5:
				// triangle
				Triangle triangle = (Triangle)shape;
				
				double lowestYPoint = Math.min(triangle.getA().getY(), triangle.getB().getY());
				lowestYPoint = Math.min(lowestYPoint, triangle.getC().getY());
				
				if(lowestYPoint < 0.0){
					return this.getCenter(shape, -(lowestYPoint), factor);
				}
				else{
					return this.getCenter(shape, (lowestYPoint), factor);
				}	
				
			default:
				return null;
		}
		
	}
	
	private Point2D.Double getCenter(Shape shape, double dist, double factor){
		
		// instantiate dto to be passed into the converter
		ConvertWorldToObjDto dto = new ConvertWorldToObjDto(new Point2D.Double(shape.getCenter().getX(), shape.getCenter().getY() - dist - (20 / factor)), shape.getCenter(), shape.getRotation());
		
		// convert the point of interst to object coordinates
		Point2D.Double objCoor = (Point2D.Double)CoordinateConverterUtil.convertWorldToObject(dto);
		
		return new Point2D.Double(shape.getCenter().getX() - objCoor.getX(), shape.getCenter().getY() + objCoor.getY());
	}
	
	public static Point2D.Double getHandleCenter(Shape shape, double factor) { 
		return inst()._getHandleCenter(shape, factor);
	}
	
	private HandleType _isPointInHandle(PointInHandleDto dto) {
		Point2D.Double center = null;
		
		// if the shape is a line
		if(dto.shape.getShapeType() == ShapeType.LINE){
			Line line = (Line)dto.shape;
			
			// create circle shape for the start coordinates
			Circle startHandle = new Circle(Color.RED, line.getCenter(), 5.0 / dto.factor);
			boolean isInStartHandle = startHandle.pointInShape(dto.point, dto.screenOrigin, 0.0, dto.factor);
			
			// create circle shape for the end coordinates
			Circle endHandle = new Circle(Color.RED, line.getEnd(), 5.0 / dto.factor);
			boolean isEndHandle = endHandle.pointInShape(dto.point, dto.screenOrigin, 0.0, dto.factor);
			
			if(isInStartHandle){ 
				// if click falls in the start handle, return LINE_START handle type
				return HandleType.LINE_START;
			}
			
			if(isEndHandle){
				// if click falls in the end handle, return LINE_END handle type
				return HandleType.LINE_END;
			}
			
			// if the click does not fall with in one of the handles, return none
			return HandleType.NONE;
		}
		else{
			// if other than a line
			center = inst()._getHandleCenter(dto.shape, dto.factor);
		}

		// create handle for the shape
		Circle handle = new Circle(Color.RED, center, 5.0 / dto.factor);
		if(handle.pointInShape(dto.point, dto.screenOrigin, 0.0, dto.factor)){
			// if click falls within the shape handle, return SHAPE_HANDLE type
			return HandleType.SHAPE_HANDLE;
		}
		
		// if no handle has been selected, return none.
		return HandleType.NONE;
	}
	
	public static HandleType isPointInHandle(PointInHandleDto dto) {
		return inst()._isPointInHandle(dto);
	}
}
