package cs355.util;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.definitions.ShapeType;
import cs355.dto.PointInHandleDto;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Line;

public class PointInHandleUtil implements IUtil{

	@Override
	public Object doUtil(Object o) {
		// TODO Auto-generated method stub
		PointInHandleDto dto = (PointInHandleDto)o;
		Point2D.Double center = null;
		
		// HANDLE THIS, MAY NEED REFACTORING, ESPECIALLY FOR THE LINE CLASS
		if(dto.shape.getShapeType() == ShapeType.LINE){
			Line line = (Line)dto.shape;
			Circle startHandle = new Circle(Color.RED, line.getCenter(), 10.0 / 2.0);
			boolean isInStartHandle = startHandle.pointInShape(dto.point, 0.0);
			
			Circle endHandle = new Circle(Color.RED, line.getEnd(), 10.0 / 2.0);
			boolean isEndHandle = endHandle.pointInShape(dto.point, 0.0);
			
			if(isInStartHandle){
				line.setRotatePoint(0);
				return true;
			}
			
			if(isEndHandle){
				line.setRotatePoint(1);
				return true;
			}
			line.setRotatePoint(-1);
			return false;
		}
		else{
			center = (Point2D.Double)UtilFactory.makeUtil("handle-center").doUtil(dto.shape);
		}

		Circle handle = new Circle(Color.RED, center, 10.0 / 2.0);
		return handle.pointInShape(dto.point, 0.0);
	}
}
