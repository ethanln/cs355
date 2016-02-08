package cs355.dto;

import java.awt.geom.Point2D;

import cs355.model.drawing.Shape;

public class PointInHandleDto implements IDto{
	public Shape shape;
	public Point2D.Double point;
	public double factor;
	public Point2D.Double screenOrigin;
	
	public PointInHandleDto(Shape _shape, Point2D.Double _point, double _factor, Point2D.Double _screenOrigin){
		this.shape = _shape;
		this.point = _point;
		this.factor = _factor;
		this.screenOrigin = _screenOrigin;
	}
}
