package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.dto.ConvertWorldToObjDto;
import cs355.util.UtilFactory;
import cs355.util.WorldToObjectConverterUtil;

/**
 * Add your rectangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Rectangle extends Shape {

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
	public Rectangle(Color color, Point2D.Double center, double width, double height) {

		// Initialize the superclass.
		super(color, center);

		// Set fields.
		this.width = width;
		this.height = height;
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

	/**
	 * Add your code to do an intersection test
	 * here. You shouldn't need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Double pt, double tolerance) {
		// get world to object coordinates converter from util factory
		WorldToObjectConverterUtil converter = (WorldToObjectConverterUtil)UtilFactory.makeUtil("world_to_object_converter");
		
		// instantiate dto to be passed into the converter
		ConvertWorldToObjDto dto = new ConvertWorldToObjDto(pt, super.center, super.rotation);
		
		// convert the point of interst to object coordinates
		Point2D.Double objCoor = (Point2D.Double)converter.doUtil(dto);
		
		// calculate bottom left corner and top right corner
		Point2D.Double upperRight = new Point2D.Double((this.width / 2), -(this.height / 2));
		Point2D.Double bottomLeft = new Point2D.Double(-(this.width / 2), (this.height / 2));
		
		boolean isBetweenXCoor = objCoor.getX() <= upperRight.getX() && objCoor.getX() >= bottomLeft.getX();
		boolean isBetweenYCoor = objCoor.getY() <= bottomLeft.getY() && objCoor.getY() >= upperRight.getY();
		
		// if point of interest is between bottom left corner and top right corner, then return true
		return isBetweenXCoor && isBetweenYCoor;
	}

	@Override
	public Double getHandleCenter() {
		return new Point2D.Double(super.center.getX(), super.center.getY() - (this.height / 2) - 20);
	}
}
