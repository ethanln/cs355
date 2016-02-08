package cs355.controller.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.dto.ConvertScreenToObjDto;
import cs355.dto.ConvertScreenToWorldDto;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Triangle;
import cs355.util.CoordinateConverterUtil;

public class ControllerTriangleState extends ControllerState{
	
	private Triangle constructedTriangle;
	
	@Override
	public void editShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
	}

	@Override
	public Shape makeShape(Double p) {
		
		if(this.constructedTriangle == null){
			// instantiate new triangle (if one doesn't not currently exist) with the first point
			this.constructedTriangle = new Triangle(super.selectedColor, p, p, null, null);
			this.constructedTriangle.setShapeType(ShapeType.TRIANGLE);
		}
		else if(this.constructedTriangle.getB() == null){
			// add second point to the triangle
			this.constructedTriangle.setB(p);
		}
		else if(this.constructedTriangle.getC() == null){
			// complete triangle
			this.constructedTriangle.setC(p);
			Triangle completeTriangle = this.constructedTriangle;
			
			// set center of the triangle
			double centerX = (completeTriangle.getA().getX() + completeTriangle.getB().getX() + completeTriangle.getC().getX()) / 3;
			double centerY = (completeTriangle.getA().getY() + completeTriangle.getB().getY() + completeTriangle.getC().getY()) / 3;
			ConvertScreenToWorldDto screenToWorldDto = new ConvertScreenToWorldDto(this.screenOrigin, new Point2D.Double(centerX, centerY), this.factor);
			
			completeTriangle.setCenter(CoordinateConverterUtil.convertScreenToWorld(screenToWorldDto));
			
			this.constructedTriangle = null;
			
			//ConvertWorldToObjDto dto = null;
			ConvertScreenToObjDto dto = new ConvertScreenToObjDto(super.factor, 
										  completeTriangle.getRotation(),
										  completeTriangle.getCenter(),
										  super.getScreenOrigin(),
										  completeTriangle.getA());
			//dto = new ConvertWorldToObjDto(completeTriangle.getA(), completeTriangle.getCenter(), completeTriangle.getRotation());
			completeTriangle.setA(CoordinateConverterUtil.convertScreenToObj(dto));
			
			dto.pt = completeTriangle.getB();
			completeTriangle.setB(CoordinateConverterUtil.convertScreenToObj(dto));
			
			dto.pt = completeTriangle.getC();
			completeTriangle.setC(CoordinateConverterUtil.convertScreenToObj(dto));
			
			// return the completed triangle
			return completeTriangle;
		}
		return null;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}
}
