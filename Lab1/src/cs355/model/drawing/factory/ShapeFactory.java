package cs355.model.drawing.factory;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.definitions.ShapeType;
import cs355.definitions.ToolType;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Line;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;
import cs355.model.drawing.Triangle;

public class ShapeFactory {
	private static ShapeFactory instance;
	private Triangle constructTriangle;
	
	private ShapeFactory(){}
	
	private static ShapeFactory inst(){
		if(instance == null){
			instance = new ShapeFactory();
		}
		return instance;
	}
	
	private Shape _makeShape(ToolType type, Point2D.Double p, Color color){
		int typeVal = ToolType.toInt(type);
		
		if(this.constructTriangle != null && type != ToolType.TRIANGLE){
			this.constructTriangle = null;
		}
		
		switch(typeVal){
			case 0:
				// if square
				Square square = new Square(color, p, p, 0);
				square.setShapeType(ShapeType.SQUARE);
				return square;
				
			case 1:
				// if circle
				Circle circle = new Circle(color, p, p, 0.0);
				circle.setShapeType(ShapeType.CIRCLE);
				return circle;
				
			case 2:
				// if ellipse
				Ellipse ellipse = new Ellipse(color, p, p, 0.0, 0.0);
				ellipse.setShapeType(ShapeType.ELLIPSE);
				return ellipse;
				
			case 3:
				// if line
				Line line = new Line(color, p, p);
				line.setShapeType(ShapeType.LINE);
				return line;
				
			case 4: 
				// if rectangle
				Rectangle rect = new Rectangle(color, p, p, 0.0, 0.0);
				rect.setShapeType(ShapeType.RECTANGLE);
				return rect;
				
			case 5:
				// if triangle
				if(this.constructTriangle == null){
					this.constructTriangle = new Triangle(color, p, null, null);
					this.constructTriangle.setShapeType(ShapeType.TRIANGLE);
				}
				else if(this.constructTriangle.getB() == null){
					this.constructTriangle.setB(p);
				}
				else if(this.constructTriangle.getC() == null){
					this.constructTriangle.setC(p);
					Triangle completeTriangle = this.constructTriangle;
					this.constructTriangle = null;
					return completeTriangle;
				}
			default:
				return null;
		
		}
	}
	
	public static Shape makeShape(ToolType type, Point2D.Double p, Color color){
		return inst()._makeShape(type, p, color);
	}
}
