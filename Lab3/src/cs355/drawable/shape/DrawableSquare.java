package cs355.drawable.shape;

import java.awt.geom.Rectangle2D;
import cs355.dto.ConvertShapeToDrawableShapeDto;
import cs355.model.drawing.Square;


public class DrawableSquare extends DrawableShape{
	public DrawableSquare(ConvertShapeToDrawableShapeDto dto){
		
		Square square = (Square)dto.shape;
		super.ConvertObjToScreen(square.getCenter(), dto.screenOrigin, square.getRotation(), dto.factor);
		
		super.shape = new Rectangle2D.Double(-(square.getSize() / 2), -(square.getSize() / 2), square.getSize(), square.getSize());
		super.color = square.getColor();
	}
}
