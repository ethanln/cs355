package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your rectangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Rectangle extends Shape {

	// The upper left corner of this shape.
	private Point2D.Double upperLeft;
	private Point2D.Double oppositePoint;

	// The width of this shape.
	private double width;

	// The height of this shape.
	private double height;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param upperLeft the upper left corner of the new shape.
	 * @param width the width of the new shape.
	 * @param height the height of the new shape.
	 */
	public Rectangle(Color color, Point2D.Double upperLeft, Point2D.Double oppositePoint, double width, double height) {

		// Initialize the superclass.
		super(color);

		// Set fields.
		this.upperLeft = upperLeft;
		this.oppositePoint = oppositePoint;
		this.width = width;
		this.height = height;
	}

	/**
	 * Getter for this Rectangle's upper left corner.
	 * @return the upper left corner as a Java point.
	 */
	public Point2D.Double getUpperLeft() {
		return upperLeft;
	}

	/**
	 * Setter for this Rectangle's upper left corner.
	 * @param upperLeft the new upper left corner.
	 */
	public void setUpperLeft(Point2D.Double upperLeft) {
		this.upperLeft = upperLeft;
	}

	/**
	 * Getter for this shape's width.
	 * @return this shape's width as a double.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Setter for this shape's width.
	 * @param width the new width.
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Getter for this shape's height.
	 * @return this shape's height as a double.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Setter for this shape's height.
	 * @param height the new height.
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	public Point2D.Double getOppositePoint() {
		return oppositePoint;
	}

	public void setOppositePoint(Point2D.Double oppositePoint) {
		this.oppositePoint = oppositePoint;
	}
	
	@Override
	public void editShape(Point2D.Double p){
		double x = this.oppositePoint.getX();
		double y = this.oppositePoint.getY();
		double w = p.getX() - x;
		double h = p.getY() - y;
		
		if(w < 0.0 && h < 0.0){
			x = Math.min(p.getX(), this.oppositePoint.getX());
			w = Math.abs(this.oppositePoint.getX() - p.getX());

			y = Math.min(p.getY(), this.oppositePoint.getY());
			h = Math.abs(this.oppositePoint.getY() - p.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(this.oppositePoint.getX() - w, this.oppositePoint.getY() - h);
			
			this.setUpperLeft(newPoint);
			
		}
		else if(w < 0.0 && h >= 0.0){
			x = Math.min(p.getX(), this.oppositePoint.getX());
			w = Math.abs(this.oppositePoint.getX() - p.getX());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(this.oppositePoint.getX() - w, this.oppositePoint.getY());
			this.setUpperLeft(newPoint);
		}
		else if(w >= 0.0 && h < 0.0){
			y = Math.min(p.getY(), this.oppositePoint.getY());
			h = Math.abs(this.oppositePoint.getY() - p.getY());
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(this.oppositePoint.getX(), this.oppositePoint.getY() - h);
			this.setUpperLeft(newPoint);
		}
		else{
			this.setUpperLeft(this.oppositePoint);
		}
		
		this.setWidth(w);
		this.setHeight(h);
	}
	
	@Override
	public boolean isShapeSelected(Point2D.Double p){
		// TODO
		return false;
	}
}
