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
	protected ShapeType shapeType;

	/**
	 * Basic constructor that sets the field.
	 * @param color the color for this new shape.
	 */
	public Shape(Color color) {
		this.color = color;
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
	
	
	public ShapeType getShapeType(){
		return this.shapeType;
	}
	
	public void setShapeType(ShapeType type){
		this.shapeType = type;
	}
	
	public void editShape(Point2D.Double p){
		
	}
	
	public boolean isShapeSelected(Point2D.Double p){
		return false;
	}
}
