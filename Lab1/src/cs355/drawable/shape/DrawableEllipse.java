package cs355.drawable.shape;

import java.awt.geom.Ellipse2D;

import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Shape;


public class DrawableEllipse extends DrawableShape{
	public DrawableEllipse(Shape shape){
		
		Ellipse ellipse = (Ellipse)shape;
		super.ConvertObjToWorld(ellipse.getCenter(), ellipse.getRotation());
		super.shape = new Ellipse2D.Double(-(ellipse.getWidth() / 2), -(ellipse.getHeight() / 2), ellipse.getWidth(), ellipse.getHeight());
		super.color = ellipse.getColor();
	}
}
