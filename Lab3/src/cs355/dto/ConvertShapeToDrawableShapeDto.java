package cs355.dto;

import java.awt.geom.Point2D;

import cs355.model.drawing.Shape;

public class ConvertShapeToDrawableShapeDto implements IDto{

	public Shape shape;
	public Point2D.Double screenOrigin;
	public double factor;
	
	public ConvertShapeToDrawableShapeDto(Shape _shape, Point2D.Double _screenOrigin, double _factor){
		this.shape = _shape;
		this.screenOrigin = _screenOrigin;
		this.factor = _factor;
	}
}
