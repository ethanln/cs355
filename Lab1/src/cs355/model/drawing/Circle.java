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
	public void editShape(Point2D.Double p){
		double distX = p.getX() - this.oppositePoint.getX();
		double distY = p.getY() - this.oppositePoint.getY();
		double distXY = 0.0;
			
		if(distX < 0.0 && distY < 0.0){
			//top left grid
			Point2D.Double newPoint = new Point2D.Double();
			double minDist = Math.abs(distX) < Math.abs(distY) ? Math.abs(distX) : Math.abs(distY);
			newPoint.setLocation(this.oppositePoint.getX() - minDist, this.oppositePoint.getY() - minDist);
			this.setCenter(newPoint);
		}
		else if(distX < 0.0 && distY >= 0.0){
			//bottom left grid
			if(Math.abs(distY) > Math.abs(distX)){
				Point2D.Double newPoint = new Point2D.Double();
				newPoint.setLocation(p.getX(), this.oppositePoint.getY());
				this.setCenter(newPoint);
			}
			else{
				Point2D.Double newPoint = new Point2D.Double();
				double subVal = Math.abs(distX) - Math.abs(distY);
				newPoint.setLocation(p.getX() + subVal, this.oppositePoint.getY());
				this.setCenter(newPoint);
			}
		}
		else if(distX >= 0.0 && distY < 0.0){
			// top right grid
			if(Math.abs(distY) < Math.abs(distX)){
				Point2D.Double newPoint = new Point2D.Double();
				newPoint.setLocation(this.oppositePoint.getX(), p.getY());
				this.setCenter(newPoint);
			}
			else{
				Point2D.Double newPoint = new Point2D.Double();
				double subVal = Math.abs(distY) - Math.abs(distX);
				newPoint.setLocation(this.oppositePoint.getX(), p.getY() + subVal);
				this.setCenter(newPoint);
			}
		}
		else{
			this.setCenter(this.oppositePoint);
		}
		
		distX = Math.abs(p.getX() - this.oppositePoint.getX());
		distY = Math.abs(p.getY() - this.oppositePoint.getY());
		
		distXY = distX < distY ? distX : distY;
		
		this.radius = distXY;
	}

	@Override
	public boolean isShapeSelected(Point2D.Double p){
		double distX = Math.abs(Math.abs(p.getX()) - Math.abs(Math.abs(this.center.getX()) + this.radius/2));
		double distY = Math.abs(Math.abs(p.getY()) - Math.abs(Math.abs(this.center.getY()) + this.radius/2));
		
		distX = Math.pow(distX, 2.0);
		distY = Math.pow(distY, 2.0);
		
		double distance = Math.sqrt(distX + distY);
		return distance <= this.radius / 2;
	}
	
}
