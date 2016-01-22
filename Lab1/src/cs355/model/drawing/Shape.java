package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.definitions.ShapeType;

/**
 * This is the base class for all of your shapes.
 * Make sure they all extend this class.
 */
public abstract class Shape {

	// The color of this shape.
	protected Color color;

	// The center of this shape.
	protected Point2D.Double center;

	// The rotation of this shape.
	protected double rotation;
	
	// additional fields
	protected ShapeType shapeType;
	protected boolean isSelected;

	/**
	 * Basic constructor that sets the field.
	 * @param color the color for this new shape.
	 */
	public Shape(Color color, Point2D.Double center) {
		this.color = color;
		this.center = center;
		this.rotation = 0.0;

		this.isSelected = false;
	}

	/**
	 * Getter for this shape's color.
	 * @return the color of this shape.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setter for this shape's color
	 * @param color the new color for the shape.
	 */
	public void setColor(Color color) {
		this.color = color;
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
	 * Getter for this shape's rotation.
	 * @return the rotation as a double.
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Setter for this shape's rotation.
	 * @param rotation the new rotation.
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	
	public ShapeType getShapeType(){
		return this.shapeType;
	}
	
	public void setShapeType(ShapeType type){
		this.shapeType = type;
	}
	
	public boolean isSelected(){
		return this.isSelected;
	}
	
	public void setIsSelected(boolean value){
		this.isSelected = value;
	}

	/**
	 * Used to test for whether the user clicked inside a shape or not.
	 * @param pt = the point to test whether it's in the shape or not.
	 * @param tolerance = the tolerance for testing. Mostly used for lines.
	 * @return true if pt is in the shape, false otherwise.
	 */
	public abstract boolean pointInShape(Point2D.Double pt, double tolerance);
	
	public abstract Point2D.Double getHandleCenter();
	
	public boolean isInHandle(Point2D.Double pt){
		Circle handle = new Circle(Color.RED, this.getHandleCenter(), 10.0 / 2.0);
		return handle.pointInShape(pt, 0.0);
	}

}
