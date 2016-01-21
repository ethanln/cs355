package cs355.dto;

import java.awt.geom.Point2D;

public class ConvertWorldToObjDto implements IDto{
	public Point2D.Double pointToConvert;
	public Point2D.Double center;
	public double rotation;
	
	public ConvertWorldToObjDto(Point2D.Double _pointToConvert, Point2D.Double _center, double _rotation){
		this.pointToConvert = _pointToConvert;
		this.center = _center;
		this.rotation = _rotation;
	}
}
