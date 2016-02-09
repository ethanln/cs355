package cs355.drawable.shape;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import cs355.dto.ConvertShapeToDrawableShapeDto;
import cs355.model.drawing.Line;

public class DrawableLine extends DrawableShape{

	public DrawableLine(ConvertShapeToDrawableShapeDto dto){
		
		Line line = (Line)dto.shape;

		super.ConvertObjToScreen(new Point2D.Double(0.0, 0.0), dto.screenOrigin, line.getRotation(), dto.factor);
		super.shape = new Line2D.Double(line.getCenter().getX(), line.getCenter().getY(), line.getEnd().getX(), line.getEnd().getY());
		super.color = line.getColor();
	}
}
