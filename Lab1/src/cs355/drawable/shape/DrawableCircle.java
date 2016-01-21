package cs355.drawable.shape;

import java.awt.geom.Ellipse2D;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;

public class DrawableCircle extends DrawableShape{
	
	public DrawableCircle(Shape shape){
		
		Circle circle = (Circle)shape;
		if(circle.isHandle() || circle.isSelectedBorder()){
			super.isSelectedOverlay = true;
		}
		super.ConvertObjToWorld(circle.getCenter(), circle.getRotation());
		super.shape = new Ellipse2D.Double(-(circle.getRadius()), -(circle.getRadius()), circle.getRadius() * 2, circle.getRadius() * 2);
		super.color = circle.getColor();
	}
}
