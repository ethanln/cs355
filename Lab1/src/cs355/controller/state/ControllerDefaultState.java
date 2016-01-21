package cs355.controller.state;

import java.awt.geom.Point2D.Double;

import cs355.model.drawing.Shape;

public class ControllerDefaultState extends ControllerState{

	@Override
	public void editShape(Shape shape, Double p) {	
	}

	@Override
	public Shape makeShape(Double p) {
		return null;
	}

	@Override
	public void moveShape(Shape shape, Shape overlayBorder, Shape overlayHandle, Double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape makeShapeBorder(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape makeHandle(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}

}
