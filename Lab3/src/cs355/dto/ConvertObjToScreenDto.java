package cs355.dto;

import java.awt.geom.Point2D;

public class ConvertObjToScreenDto implements IDto{
	public Point2D.Double center;
	public Point2D.Double screenOrigin;
	public double rotation;
	public double factor;
	
	public ConvertObjToScreenDto(Point2D.Double _center, Point2D.Double _screenOrigin, double _rotation, double _factor){
		this.center = _center;
		this.screenOrigin = _screenOrigin;
		this.rotation = _rotation;
		this.factor = _factor;
	}
	
}
