package cs355.dto;

import java.awt.geom.Point2D;

public class ConvertScreenToObjDto implements IDto{
	public double factor;
	public double rotation;
	public Point2D.Double center;
	public Point2D.Double screenOrigin;
	public Point2D.Double pt;
	
	public ConvertScreenToObjDto(double _factor, double _rotation, Point2D.Double _center, Point2D.Double _screenOrigin, Point2D.Double _pt){
		this.factor = _factor;
		this.rotation = _rotation;
		this.center = _center;
		this.screenOrigin = _screenOrigin;
		this.pt = _pt;
	}
}
