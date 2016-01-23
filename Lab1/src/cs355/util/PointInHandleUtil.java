package cs355.util;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.definitions.HandleType;
import cs355.definitions.ShapeType;
import cs355.dto.PointInHandleDto;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Line;

/**
 * Point in Handle Utility, checks if a click falls within any of the handles
 * @author ethan
 *
 */
public class PointInHandleUtil implements IUtil{

	@Override
	public Object doUtil(Object o) {
		PointInHandleDto dto = (PointInHandleDto)o;
		Point2D.Double center = null;
		
		// if the shape is a line
		if(dto.shape.getShapeType() == ShapeType.LINE){
			Line line = (Line)dto.shape;
			
			// create circle shape for the start coordinates
			Circle startHandle = new Circle(Color.RED, line.getCenter(), 10.0 / 2.0);
			boolean isInStartHandle = startHandle.pointInShape(dto.point, 0.0);
			
			// create circle shape for the end coordinates
			Circle endHandle = new Circle(Color.RED, line.getEnd(), 10.0 / 2.0);
			boolean isEndHandle = endHandle.pointInShape(dto.point, 0.0);
			
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
			center = (Point2D.Double)UtilFactory.makeUtil("handle-center").doUtil(dto.shape);
		}

		// create handle for the shape
		Circle handle = new Circle(Color.RED, center, 10.0 / 2.0);
		if(handle.pointInShape(dto.point, 0.0)){
			// if click falls within the shape handle, return SHAPE_HANDLE type
			return HandleType.SHAPE_HANDLE;
		}
		
		// if no handle has been selected, return none.
		return HandleType.NONE;
	}
}
