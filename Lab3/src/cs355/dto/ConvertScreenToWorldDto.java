package cs355.dto;

import java.awt.geom.Point2D;

public class ConvertScreenToWorldDto implements IDto{
	public Point2D.Double screenOrigin;
	public Point2D.Double pt;
	public double factor;
	
	public ConvertScreenToWorldDto(Point2D.Double _screenOrigin, Point2D.Double _pt, double _factor){
		this.screenOrigin = _screenOrigin;
		this.pt = _pt;
		this.factor = _factor;
	}
}
