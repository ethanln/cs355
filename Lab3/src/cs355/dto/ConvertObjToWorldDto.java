package cs355.dto;

import java.awt.geom.Point2D;

public class ConvertObjToWorldDto implements IDto{
	public Point2D.Double center;
	public double rotation;
	
	public ConvertObjToWorldDto(Point2D.Double _center, double _rotation){
		this.center = _center;
		this.rotation = _rotation;
	}
}
