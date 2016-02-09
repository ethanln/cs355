package cs355.drawable.shape;

import java.awt.geom.Ellipse2D;
import cs355.dto.ConvertShapeToDrawableShapeDto;
import cs355.model.drawing.Ellipse;


public class DrawableEllipse extends DrawableShape{
	public DrawableEllipse(ConvertShapeToDrawableShapeDto dto){
		
		Ellipse ellipse = (Ellipse)dto.shape;
		super.ConvertObjToScreen(ellipse.getCenter(), dto.screenOrigin, ellipse.getRotation(), dto.factor);
		
		super.shape = new Ellipse2D.Double(-(ellipse.getWidth() / 2), -(ellipse.getHeight() / 2), ellipse.getWidth(), ellipse.getHeight());
		super.color = ellipse.getColor();
	}
}
