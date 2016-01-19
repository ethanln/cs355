package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

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
	public Triangle(Color color, Point2D.Double a, Point2D.Double b, Point2D.Double c) {

		// Initialize the superclass.
		super(color);

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

	@Override
	public boolean isInShape(Point2D.Double p) {
		Point2D.Double p2 = new Point2D.Double((b.getX() - a.getX()), (b.getY() - a.getY()));
		Point2D.Double p3 = new Point2D.Double((c.getX() - a.getX()), (c.getY() - a.getY()));
		Point2D.Double p0 = new Point2D.Double((p.getX() - a.getX()), (p.getY() - a.getY()));
		
		double d = (p2.getX() * p3.getY()) - (p3.getX() * p2.getY());
		
		double w1 = (p0.getX() * (p2.getY() - p3.getY()) + p0.getY() * (p3.getX() - p2.getX()) + (p2.getX() * p3.getY()) - (p3.getX() * p2.getY())) / d;
		double w2 = ((p0.getX() * p3.getY()) - (p0.getY() * p3.getX())) / d;
		double w3 = ((p0.getY() * p2.getX()) - (p0.getX() * p2.getY())) / d;
		
		
		
		return w1 <= 1 && w1 >= 0
				&& w2 <= 1 && w2 >= 0
				&& w3 <= 1 && w3 >= 0;
	}
}
