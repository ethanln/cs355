package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.dto.ConvertWorldToObjDto;
import cs355.util.CoordinateConverterUtil;

/**
 * Add your square code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Square extends Shape {

	// The size of this Square.
	private double size;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param upperLeft the upper left corner of the new shape.
	 * @param size the size of the new shape.
	 */
	public Square(Color color, Point2D.Double center, double size) {

		// Initialize the superclass.
		super(color, center);

		this.size = size;
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
		// instantiate dto to be passed into the converter
		ConvertWorldToObjDto dto = new ConvertWorldToObjDto(pt, super.center, super.rotation);
		
		// convert the point of interst to object coordinates
		Point2D.Double objCoor = CoordinateConverterUtil.convertWorldToObject(dto);
		
		// calculate bottom left corner and top right corner
		Point2D.Double upperRight = new Point2D.Double((this.size / 2), -(this.size / 2));
		Point2D.Double bottomLeft = new Point2D.Double(-(this.size / 2), (this.size / 2));
		
		boolean isBetweenXCoor = objCoor.getX() <= upperRight.getX() && objCoor.getX() >= bottomLeft.getX();
		boolean isBetweenYCoor = objCoor.getY() <= bottomLeft.getY() && objCoor.getY() >= upperRight.getY();
		
		// if point of interest is between bottom left corner and top right corner, then return true
		return isBetweenXCoor && isBetweenYCoor;
	}
}
