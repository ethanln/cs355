package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your circle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Circle extends Shape {

	// The center of this shape.
	private Point2D.Double center;
	private Point2D.Double oppositePoint;

	// The radius.
	private double radius;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param radius the radius of the new shape.
	 */
	public Circle(Color color, Point2D.Double center, Point2D.Double oppositePoint, double radius) {

		// Initialize the superclass.
		super(color);

		// Set fields.
		this.center = center;
		this.oppositePoint = oppositePoint;
		this.radius = radius;
	}

	/**
	 * Getter for this shape's center.
	 * @return this shape's center as a Java point.
	 */
	public Point2D.Double getCenter() {
		return center;
	}

	/**
	 * Setter for this shape's center.
	 * @param center the new center as a Java point.
	 */
	public void setCenter(Point2D.Double center) {
		this.center = center;
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
	
	public Point2D.Double getOppositePoint() {
		return oppositePoint;
	}

	public void setOppositePoint(Point2D.Double oppositePoint) {
		this.oppositePoint = oppositePoint;
	}

	@Override
	public boolean isInShape(Point2D.Double p) {
		double r = this.radius / 2.0;
		Point2D.Double c = new Point2D.Double(this.center.getX() + r, this.center.getY() + r);
		
		double dX = Math.abs(p.getX() - c.getX());
		double dY = Math.abs(p.getY() - c.getY());
		
		double distX = Math.pow(dX, 2);
		double distY = Math.pow(dY, 2);
		
		double dist = Math.sqrt(distX + distY);
		
		return dist <= r;
	}
}
