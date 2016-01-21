package cs355.controller.state;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import cs355.definitions.ShapeType;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;
import cs355.model.drawing.Triangle;

public class ControllerSelectState extends ControllerState{
	
	@Override
	public void editShape(Shape shape, Double p) {
		shape.setColor(this.selectedColor);
	}

	@Override
	public Shape makeShape(Double p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveShape(Shape shape, Shape overlayBorder, Shape overlayHandle, Double p) {
		//shape.
		double vectorX = this.origin.getX() - p.getX();
		double vectorY = this.origin.getY() - p.getY();
		
		Point2D.Double newCenter = new Point2D.Double();
		newCenter.setLocation(shape.getCenter().getX() - vectorX, shape.getCenter().getY() - vectorY);
		
		
		if(overlayBorder != null && overlayHandle != null){
			Point2D.Double newCenterHandle = new Point2D.Double();
			newCenterHandle.setLocation(overlayHandle.getCenter().getX() - vectorX, overlayHandle.getCenter().getY() - vectorY);
			
			// set the border overlay center
			overlayBorder.setCenter(newCenter);
			overlayHandle.setCenter(newCenterHandle);
		}
		
		// set the shape center
		shape.setCenter(newCenter);

		// set the new origin
		this.origin = p;
	}

	@Override
	public Shape makeShapeBorder(Shape shape) {
		
		int type = ShapeType.toInt(shape.getShapeType());
		switch(type){
			case 0:
				// Square
				Square square = (Square)shape;
				Square borderSquare = new Square(Color.RED, square.getCenter(), square.getSize());
				borderSquare.setShapeType(ShapeType.SQUARE);
				borderSquare.setIsSelectedBorder(true);
				return borderSquare;
				
			case 1:
				// Circle
				Circle circle = (Circle)shape;
				Square borderCircle = new Square(Color.RED, circle.getCenter(), circle.getRadius() * 2);
				borderCircle.setShapeType(ShapeType.SQUARE);
				borderCircle.setIsSelectedBorder(true);
				return borderCircle;
				
			case 2:
				// Ellipse
				Ellipse ellipse = (Ellipse)shape;
				Rectangle borderEllipse = new Rectangle(Color.RED, ellipse.getCenter(), ellipse.getWidth(), ellipse.getHeight());
				borderEllipse.setShapeType(ShapeType.RECTANGLE);
				borderEllipse.setIsSelectedBorder(true);
				return borderEllipse;
				
			case 3:
				// Line
				return null;
			case 4:
				// Rectangle
				Rectangle rect = (Rectangle)shape;
				Rectangle borderRect = new Rectangle(Color.RED, rect.getCenter(), rect.getWidth(), rect.getHeight());
				borderRect.setShapeType(ShapeType.RECTANGLE);
				borderRect.setIsSelectedBorder(true);
				return borderRect;
				
			case 5:
				// Triangle
				Triangle triangle = (Triangle)shape;
				Triangle borderTriangle = new Triangle(Color.RED, triangle.getCenter(), triangle.getA(), triangle.getB(), triangle.getC());
				borderTriangle.setShapeType(ShapeType.TRIANGLE);
				borderTriangle.setIsSelectedBorder(true);
				return borderTriangle;
				
			default:	
				return null;
		}
	}

	@Override
	public Shape makeHandle(Shape shape) {
		int type = ShapeType.toInt(shape.getShapeType());
		
		Circle handle = null;
		Point2D.Double newCenter = new Point2D.Double();
		
		switch(type){
			case 0:
				// Square
				Square square = (Square)shape;
				newCenter.setLocation(square.getCenter().getX(), square.getCenter().getY() - (square.getSize() / 2) - 20);
				
				handle = new Circle(Color.RED, newCenter, 10.0 / 2.0);
				handle.setShapeType(ShapeType.CIRCLE);
				handle.setIsHandle(true);
				return handle;
				
			case 1:
				// Circle
				Circle circle = (Circle)shape;
				newCenter.setLocation(circle.getCenter().getX(), circle.getCenter().getY() - (circle.getRadius()) - 20);
				
				handle = new Circle(Color.RED, newCenter, 10.0 / 2.0);
				handle.setShapeType(ShapeType.CIRCLE);
				handle.setIsHandle(true);
				return handle;
			case 2:
				// Ellipse
				Ellipse ellipse = (Ellipse)shape;
				newCenter.setLocation(ellipse.getCenter().getX(), ellipse.getCenter().getY() - (ellipse.getHeight() / 2) - 20);
				
				handle = new Circle(Color.RED, newCenter, 10.0 / 2.0);
				handle.setShapeType(ShapeType.CIRCLE);
				handle.setIsHandle(true);
				return handle;
				
			case 3:
				// Line
				return null;
			case 4:
				// Rectangle
				Rectangle rect = (Rectangle)shape;
				newCenter.setLocation(rect.getCenter().getX(), rect.getCenter().getY() - (rect.getHeight() / 2) - 20);
				
				handle = new Circle(Color.RED, newCenter, 10.0 / 2.0);
				handle.setShapeType(ShapeType.CIRCLE);
				handle.setIsHandle(true);
				return handle;
				
			case 5:
				// Triangle // WORK HERE LATER // HOW DO I WANT TO REPRESENT THE BORDER AROUND THE TRIANGLE
				//Triangle triangle = (Triangle)shape;
				//Triangle borderTriangle = new Triangle(triangle.getColor(), triangle.getCenter(), triangle.getA(), triangle.getB(), triangle.getC());
				//borderTriangle.setShapeType(ShapeType.TRIANGLE);
				//borderTriangle.setIsSelectedBorder(true);
				//return borderTriangle;
				
			default:	
				return null;
		}
	}

}
