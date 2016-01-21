package cs355.drawable.shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public abstract class DrawableShape {
	
	protected Shape shape;
	protected Color color;
	protected AffineTransform objToWorld;
	protected boolean isSelectedOverlay;
	
	public void draw(Graphics2D g2d){
		g2d.setTransform(objToWorld);
		
		
		if(!this.isSelectedOverlay){
			g2d.setPaint(this.color);
			g2d.draw(this.shape);
			g2d.fill(this.shape);
		}
		else{
			g2d.setPaint(Color.RED);
			g2d.setStroke(new BasicStroke(1));
			g2d.draw(this.shape);
		}
	}
	
	public Shape getShape(){
		return this.shape;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	protected void ConvertObjToWorld(Point2D.Double center, double rotation){
		this.objToWorld = new AffineTransform();
		this.objToWorld.translate(center.getX(), center.getY());
		this.objToWorld.rotate(rotation);
	}
}
