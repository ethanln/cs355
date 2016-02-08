package cs355.drawable.shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.dto.ConvertObjToScreenDto;
import cs355.util.CoordinateConverterUtil;

public abstract class DrawableShape {
	
	protected Shape shape;
	protected Color color;
	protected AffineTransform objToScreen;
	
	public void draw(Graphics2D g2d, String fillType){
		
		g2d.setTransform(objToScreen);
		if(fillType == "fill"){
			g2d.setPaint(this.color);
			g2d.draw(this.shape);
			g2d.fill(this.shape);
		}
		else if(fillType == "border"){
			g2d.setPaint(Color.RED);
			g2d.setStroke(new BasicStroke(0));
			g2d.draw(this.shape);
		}
	}
	
	public Shape getShape(){
		return this.shape;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	//protected void ConvertObjToWorld(Point2D.Double center, double rotation){
	//	this.objToWorld = CoordinateConverterUtil.convertObjectToWorld(new ConvertObjToWorldDto(center, rotation));
	//}
	
	protected void ConvertObjToScreen(Point2D.Double center, Point2D.Double screenOrigin, double rotation, double factor){
		this.objToScreen = CoordinateConverterUtil.convertObjToScreen(new ConvertObjToScreenDto(center, screenOrigin, rotation, factor));
	}
}
