package cs355.drawable.shape;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Shape;


public class DrawableEllipse extends DrawableShape{
	public DrawableEllipse(Shape shape, Point2D.Double screenOrigin, double factor){
		
		Ellipse ellipse = (Ellipse)shape;
		super.ConvertObjToScreen(ellipse.getCenter(), screenOrigin, ellipse.getRotation(), factor);
		
		super.shape = new Ellipse2D.Double(-(ellipse.getWidth() / 2), -(ellipse.getHeight() / 2), ellipse.getWidth(), ellipse.getHeight());
		super.color = ellipse.getColor();
	}
}
