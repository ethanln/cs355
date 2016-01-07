package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your square code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Square extends Shape {

	// The upper left corner of this shape.
	private Point2D.Double upperLeft;
	private Point2D.Double oppositePoint;
	// The size of this Square.
	private double size;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param upperLeft the upper left corner of the new shape.
	 * @param size the size of the new shape.
	 */
	public Square(Color color, Point2D.Double upperLeft, Point2D.Double oppositePoint, double size) {

		// Initialize the superclass.
		super(color);

		// Set fields.
		this.upperLeft = upperLeft;
		this.oppositePoint = oppositePoint;
		this.size = size;
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
	 * Getter for this Square's size.
	 * @return the size as a double.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Setter for this Square's size.
	 * @param size the new size.
	 */
	public void setSize(double size) {
		this.size = size;
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
		
		double minSize = 0.0;
		
		if(w < 0.0 && h < 0.0){

			x = Math.min(p.getX(), this.oppositePoint.getX());
			w = Math.abs(this.oppositePoint.getX() - p.getX());

			y = Math.min(p.getY(), this.oppositePoint.getY());
			h = Math.abs(this.oppositePoint.getY() - p.getY());
			
			minSize += w < h ? w : h;
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(this.oppositePoint.getX() - minSize, this.oppositePoint.getY() - minSize);
			
			this.setUpperLeft(newPoint);
		}
		else if(w < 0.0 && h >= 0.0){
			x = Math.min(p.getX(), this.oppositePoint.getX());
			w = Math.abs(this.oppositePoint.getX() - p.getX());

			y = Math.min(p.getY(), this.oppositePoint.getY());
			h = Math.abs(this.oppositePoint.getY() - p.getY());
			
			minSize += w < h ? w : h;
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(this.oppositePoint.getX() - minSize, this.oppositePoint.getY());
			
			this.setUpperLeft(newPoint);
		}
		else if(w >= 0.0 && h < 0.0){
			x = Math.min(p.getX(), this.oppositePoint.getX());
			w = Math.abs(this.oppositePoint.getX() - p.getX());

			y = Math.min(p.getY(), this.oppositePoint.getY());
			h = Math.abs(this.oppositePoint.getY() - p.getY());
			
			minSize += w < h ? w : h;
			
			Point2D.Double newPoint = new Point2D.Double();
			newPoint.setLocation(this.oppositePoint.getX(), this.oppositePoint.getY() - minSize);
			
			this.setUpperLeft(newPoint);
		}
		else{
			this.setUpperLeft(this.oppositePoint);
			minSize = w < h ? w : h;
		}

		this.setSize(minSize);
	}
	
	@Override
	public boolean isShapeSelected(Point2D.Double p){
		// TODO
		return false;
	}
}
