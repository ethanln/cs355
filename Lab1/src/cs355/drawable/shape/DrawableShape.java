package cs355.drawable.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class DrawableShape {
	
	protected Shape shape;
	protected Color color;
	
	public void draw(Graphics2D g2d){
		g2d.setPaint(this.color);
		g2d.draw(this.shape);
		g2d.fill(this.shape);
	}
	
	public Shape getShape(){
		return this.shape;
	}
	
	public Color getColor(){
		return this.color;
	}
}
