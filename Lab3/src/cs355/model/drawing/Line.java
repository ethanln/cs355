package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.dto.ConvertScreenToWorldDto;
import cs355.util.CoordinateConverterUtil;


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
	public boolean pointInShape(Point2D.Double pt, Point2D.Double screenOrigin, double tolerance, double factor) {
		
		Point2D.Double pW = CoordinateConverterUtil.convertScreenToWorld(new ConvertScreenToWorldDto(screenOrigin, 
						pt, factor));
		
		// get distance perpendicular to the actual line
		double vectorAX = this.end.getX() - this.center.getX();
		double vectorAY = this.end.getY() - this.center.getY();
		
		double vectorBX = pW.getX() - this.center.getX();
		double vectorBY = pW.getY() - this.center.getY();
		
		double magnitudeA = Math.sqrt(Math.pow(vectorAX, 2) + Math.pow(vectorAY, 2));
		double magnitudeB = Math.sqrt(Math.pow(vectorBX, 2) + Math.pow(vectorBY, 2));
		double magnitudeC = ((vectorAX * vectorBX) + (vectorAY * vectorBY)) / magnitudeA;
		
		double distance = Math.sqrt(Math.abs(Math.pow(magnitudeB, 2) - Math.pow(magnitudeC, 2)));
		
		// Check to see if the the click is with in the line segment
		double vectorOriginX = this.center.getX();
		double vectorOriginY = this.center.getY();
		
		double t = magnitudeC / magnitudeA;
		double pointX = 0.0;
		double pointY = 0.0;

		pointX = (t * vectorAX) + vectorOriginX;
		pointY = (t * vectorAY) + vectorOriginY;
		
		boolean withinX = false;
		boolean withinY = false;
		

		
		if(this.center.getX() < this.end.getX()
				&& this.center.getY() < this.end.getY()){ // if start point of line is on upper left of the grid
			withinX = pointX > (this.center.getX() - tolerance) && pointX < (this.end.getX() + tolerance);
			withinY = pointY > (this.center.getY() - tolerance) && pointY < (this.end.getY() + tolerance);
		}
		else if(this.center.getX() > this.end.getX()
				&& this.center.getY() < this.end.getY()){ // if start point of line is on upper right of the grid
			withinX = pointX < (this.center.getX() + tolerance) && pointX > (this.end.getX() - tolerance);
			withinY = pointY > (this.center.getY() - tolerance) && pointY < (this.end.getY() + tolerance);
		}
		else if(this.center.getX() < this.end.getX()
				&& this.center.getY() > this.end.getY()){ // if start point of line is on bottom left of the grid
			withinX = pointX > (this.center.getX() - tolerance) && pointX < (this.end.getX() + tolerance);
			withinY = pointY < (this.center.getY() + tolerance) && pointY > (this.end.getY() - tolerance);
		}
		else if(this.center.getX() == this.end.getX()){
			if(this.center.getY() < this.end.getY()){
				withinX = pointX > (this.center.getX() - tolerance) && pointX < (this.end.getX() + tolerance);
				withinY = pointY > (this.center.getY() - tolerance) && pointY < (this.end.getY() + tolerance);
			}
			else{
				withinX = pointX < (this.center.getX() + tolerance) && pointX > (this.end.getX() - tolerance);
				withinY = pointY < (this.center.getY() + tolerance) && pointY > (this.end.getY() - tolerance);
			}
		}
		else if(this.center.getY() == this.end.getY()){
			if(this.center.getX() < this.end.getX()){
				withinX = pointX > (this.center.getX() - tolerance) && pointX < (this.end.getX() + tolerance);
				withinY = pointY > (this.center.getY() - tolerance) && pointY < (this.end.getY() + tolerance);
			}
			else{
				withinX = pointX < (this.center.getX() + tolerance) && pointX > (this.end.getX() - tolerance);
				withinY = pointY < (this.center.getY() + tolerance) && pointY > (this.end.getY() - tolerance);
			}
		}
		else{ // if start point of line is on bottom right of the grid
			withinX = pointX < (this.center.getX() + tolerance) && pointX > (this.end.getX() - tolerance);
			withinY = pointY < (this.center.getY() + tolerance) && pointY > (this.end.getY() - tolerance);
		}
		
		return distance <= tolerance && withinX && withinY;
	}
	/*
	@Override
	public boolean pointInShape(Double pt, double tolerance) {
		//calculate all point vectors from origin
		Vector2D p0Vector = new Vector2D(this.center.getX(), this.center.getY());
		Vector2D p1Vector = new Vector2D(this.end.getX(), this.end.getY());
		Vector2D qVector = new Vector2D(pt.getX(), pt.getY());
		
		Vector2D segmentVector = Vector2D.subtractVectors(p0Vector, p1Vector);
		double segmentMagnitude = segmentVector.getMagnitude();
		
		Vector2D dHat = new Vector2D((segmentVector.getVectorX() / segmentMagnitude), (segmentVector.getVectorY() / segmentMagnitude));
		
		// subtract vectors p0 and q to get the scalar
		Vector2D vectorsP0SubQ = Vector2D.subtractVectors(p0Vector, qVector);
		Vector2D scalarT = Vector2D.multiplyVectors(vectorsP0SubQ, dHat);
		
		double scalarValue = Vector2D.getDotProduct(scalarT, dHat);
		
		Vector2D qPrimeVector = new Vector2D(p0Vector.getVectorX() + scalarValue, p0Vector.getVectorY() + scalarValue);
		
		boolean isOnLine = qPrimeVector.getVectorX() <= Math.max(this.center.getX(), this.end.getX()) + tolerance
							&& qPrimeVector.getVectorX() >= Math.min(this.center.getX(), this.end.getX()) - tolerance
							&& qPrimeVector.getVectorY() <= Math.max(this.center.getY(), this.end.getY()) + tolerance
							&& qPrimeVector.getVectorY() >= Math.min(this.center.getY(), this.end.getY()) - tolerance;
		
		return isOnLine && Vector2D.subtractVectors(qVector, qPrimeVector).getMagnitude() <= tolerance;
	}*/
}
