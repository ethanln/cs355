package cs355.controller.state;

import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Triangle;

public class ControllerTriangleState extends ControllerState{
	
	private Triangle constructedTriangle;
	
	@Override
	public void editShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
	}

	@Override
	public Shape makeShape(Double p) {
		
		if(this.constructedTriangle == null){
			this.constructedTriangle = new Triangle(super.selectedColor, p, null, null);
			this.constructedTriangle.setShapeType(ShapeType.TRIANGLE);
		}
		else if(this.constructedTriangle.getB() == null){
			this.constructedTriangle.setB(p);
		}
		else if(this.constructedTriangle.getC() == null){
			this.constructedTriangle.setC(p);
			Triangle completeTriangle = this.constructedTriangle;
			this.constructedTriangle = null;
			return completeTriangle;
		}
		return null;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}


}
