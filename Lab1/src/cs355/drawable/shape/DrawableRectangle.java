package cs355.drawable.shape;

import java.awt.geom.Rectangle2D;

import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;

public class DrawableRectangle extends DrawableShape{
	
	public DrawableRectangle(Shape shape){
		
		Rectangle rect = (Rectangle)shape;
		if(rect.isHandle() || rect.isSelectedBorder()){
			super.isSelectedOverlay = true;
		}
		super.ConvertObjToWorld(rect.getCenter(), rect.getRotation());
		super.shape = new Rectangle2D.Double(-(rect.getWidth() / 2), -(rect.getHeight() / 2), rect.getWidth(), rect.getHeight());
		super.color = rect.getColor();
	}
}
