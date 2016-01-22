package cs355.dto;

import java.awt.geom.Point2D;

import cs355.model.drawing.Shape;

public class PointInHandleDto implements IDto{
	public Shape shape;
	public Point2D.Double point;
	
	public PointInHandleDto(Shape _shape, Point2D.Double _point){
		this.shape = _shape;
		this.point = _point;
	}
}
