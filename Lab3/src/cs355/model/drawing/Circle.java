package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.dto.ConvertScreenToObjDto;
import cs355.util.CoordinateConverterUtil;

/**
 * Add your circle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Circle extends Shape {

	// The radius.
	private double radius;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param radius the radius of the new shape.
	 */
	public Circle(Color color, Point2D.Double center, double radius) {

		// Initialize the superclass.
		super(color, center);

		// Set fields.
		this.radius = radius;
	}

	/**
	 * Getter for this Circle's radius.
	 * @return the radius of this Circle as a double.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Setter for this Circle's radius.
	 * @param radius the new radius of this Circle.
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Add your code to do an intersection test
	 * here. You shouldn't need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Point2D.Double pt, Point2D.Double screenOrigin, double tolerance, double factor) {
		// instantiate dto to be passed into the converter
		//ConvertWorldToObjDto dto = new ConvertWorldToObjDto(pt, super.center, super.rotation);
		ConvertScreenToObjDto dto = new ConvertScreenToObjDto(factor, super.rotation, super.center, screenOrigin, pt);
		
		// convert the point of interst to object coordinates
		//Point2D.Double objCoor = CoordinateConverterUtil.convertWorldToObject(dto);
		Point2D.Double objCoor = CoordinateConverterUtil.convertScreenToObj(dto);
		
		// calculate distance from the center of circle to the point of interest
		double r = this.radius;
		
		double dX = Math.abs(objCoor.getX());
		double dY = Math.abs(objCoor.getY());
		
		double distX = Math.pow(dX, 2);
		double distY = Math.pow(dY, 2);
		
		double dist = Math.sqrt(distX + distY);
		
		// return true if the distance is less than the radius
		return dist <= r;
	}
}
