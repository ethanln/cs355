package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.dto.ConvertScreenToObjDto;
import cs355.util.CoordinateConverterUtil;

/**
 * Add your ellipse code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Ellipse extends Shape {

	// The width of this shape.
	private double width;

	// The height of this shape.
	private double height;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param width the width of the new shape.
	 * @param height the height of the new shape.
	 */
	public Ellipse(Color color, Point2D.Double center, double width, double height) {

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
	public boolean pointInShape(Point2D.Double pt, Point2D.Double screenOrigin, double tolerance, double factor) {
		// instantiate dto to be passed into the converter
		//ConvertWorldToObjDto dto = new ConvertWorldToObjDto(pt, super.center, super.rotation);
		ConvertScreenToObjDto dto = new ConvertScreenToObjDto(factor, super.rotation, super.center, screenOrigin, pt);
		
		// convert the point of interst to object coordinates
		//Point2D.Double objCoor = CoordinateConverterUtil.convertWorldToObject(dto);
		Point2D.Double objCoor = CoordinateConverterUtil.convertScreenToObj(dto);
		
		// calculate bounds
		double widthR = this.width / 2;
		double heightR = this.height / 2;
		
		double dX = Math.abs(objCoor.getX());
		double dY = Math.abs(objCoor.getY());
		
		double result = (Math.pow(dX, 2) / Math.pow(widthR, 2)) + (Math.pow(dY, 2) / Math.pow(heightR, 2));
		
		// if result is less than or equal to 1, return true;
		return result <= 1.0;
	}
}
