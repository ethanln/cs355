package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * Add your line code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Line extends Shape {

	// The ending point of the line.
	private Point2D.Double end;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param start the starting point.
	 * @param end the ending point.
	 */
	public Line(Color color, Point2D.Double start, Point2D.Double end) {

		// Initialize the superclass.
		super(color, start);

		// Set fields.
		this.end = end;
	}

	/**
	 * Getter for this Line's ending point.
	 * @return the ending point as a Java point.
	 */
	public Point2D.Double getEnd() {
		return end;
	}

	/**
	 * Setter for this Line's ending point.
	 * @param end the new ending point for the Line.
	 */
	public void setEnd(Point2D.Double end) {
		this.end = end;
	}

	/**
	 * Add your code to do an intersection test
	 * here. You <i>will</i> need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Double pt, double tolerance) {

		double vectorAX = this.center.getX() - this.end.getX();
		double vectorAY = this.center.getY() - this.end.getY();
		
		double vectorBX = this.center.getX() - pt.getX();
		double vectorBY = this.center.getY() - pt.getY();
		
		double magnitudeA = Math.sqrt(Math.pow(vectorAX, 2) + Math.pow(vectorAY, 2));
		double magnitudeB = Math.sqrt(Math.pow(vectorBX, 2) + Math.pow(vectorBY, 2));
		double magnitudeC = ((vectorAX * vectorBX) + (vectorAY * vectorBY)) / magnitudeA;
		
		double distance = Math.sqrt(Math.pow(magnitudeB, 2) - Math.pow(magnitudeC, 2));
		
		//I need to check if corresponding point is on the line from where the line was selected
		return distance <= tolerance;
	}
}
