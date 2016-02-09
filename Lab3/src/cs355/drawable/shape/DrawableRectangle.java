package cs355.drawable.shape;

import java.awt.geom.Rectangle2D;
import cs355.dto.ConvertShapeToDrawableShapeDto;
import cs355.model.drawing.Rectangle;

public class DrawableRectangle extends DrawableShape{
	
	public DrawableRectangle(ConvertShapeToDrawableShapeDto dto){
		
		Rectangle rect = (Rectangle)dto.shape;
		super.ConvertObjToScreen(rect.getCenter(), dto.screenOrigin, rect.getRotation(), dto.factor);
		
		super.shape = new Rectangle2D.Double(-(rect.getWidth() / 2), -(rect.getHeight() / 2), rect.getWidth(), rect.getHeight());
		super.color = rect.getColor();
	}
}
