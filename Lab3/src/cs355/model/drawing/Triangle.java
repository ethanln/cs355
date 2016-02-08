package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.dto.ConvertScreenToObjDto;
import cs355.util.CoordinateConverterUtil;

/**
 * Add your triangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Triangle extends Shape {

	// The three points of the triangle.
	private Point2D.Double a;
	private Point2D.Double b;
	private Point2D.Double c;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param a the first point.
	 * @param b the second point.
	 * @param c the third point.
	 */
	public Triangle(Color color, Point2D.Double center, Point2D.Double a, Point2D.Double b, Point2D.Double c) {

		// Initialize the superclass.
		super(color, center);

		// Set fields.
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Getter for the first point.
	 * @return the first point as a Java point.
	 */
	public Point2D.Double getA() {
		return a;
	}

	/**
	 * Setter for the first point.
	 * @param a the new first point.
	 */
	public void setA(Point2D.Double a) {
		this.a = a;
	}

	/**
	 * Getter for the second point.
	 * @return the second point as a Java point.
	 */
	public Point2D.Double getB() {
		return b;
	}

	/**
	 * Setter for the second point.
	 * @param b the new second point.
	 */
	public void setB(Point2D.Double b) {
		this.b = b;
	}

	/**
	 * Getter for the third point.
	 * @return the third point as a Java point.
	 */
	public Point2D.Double getC() {
		return c;
	}

	/**
	 * Setter for the third point.
	 * @param c the new third point.
	 */
	public void setC(Point2D.Double c) {
		this.c = c;
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
		//instantiate dto to be passed into the converter
		//ConvertWorldToObjDto dto = new ConvertWorldToObjDto(pt, super.center, super.rotation);
		ConvertScreenToObjDto dto = new ConvertScreenToObjDto(factor, super.rotation, super.center, screenOrigin, pt);
		
		// convert the point of interst to object coordinates
		//Point2D.Double objCoor = (Point2D.Double)CoordinateConverterUtil.convertWorldToObject(dto);
		Point2D.Double objCoor = CoordinateConverterUtil.convertScreenToObj(dto);
		
		// calulate weights
		Point2D.Double p2 = new Point2D.Double((this.b.getX() - this.a.getX()), (this.b.getY() - this.a.getY()));
		Point2D.Double p3 = new Point2D.Double((this.c.getX() - this.a.getX()), (this.c.getY() - this.a.getY()));
		Point2D.Double p0 = new Point2D.Double((objCoor.getX() - this.a.getX()), (objCoor.getY() - this.a.getY()));
		
		double d = (p2.getX() * p3.getY()) - (p3.getX() * p2.getY());
		
		double w1 = (p0.getX() * (p2.getY() - p3.getY()) + p0.getY() * (p3.getX() - p2.getX()) + (p2.getX() * p3.getY()) - (p3.getX() * p2.getY())) / d;
		double w2 = ((p0.getX() * p3.getY()) - (p0.getY() * p3.getX())) / d;
		double w3 = ((p0.getY() * p2.getX()) - (p0.getX() * p2.getY())) / d;
		
		// if all the weights are between 1 and 0, then return true
		return w1 <= 1 && w1 >= 0
				&& w2 <= 1 && w2 >= 0
				&& w3 <= 1 && w3 >= 0;
	}
}
