package cs355.controller.state;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.definitions.ShapeType;
import cs355.definitions.ToolType;
import cs355.model.drawing.Shape;

public abstract class ControllerState {
	
	protected Color selectedColor;
	protected int selectedShape;
	
	private ToolType selectedTool;
	
	public abstract void editShape(Shape shape, Point2D.Double p);
	
	public abstract Shape makeShape(Point2D.Double p);
	
	public abstract void moveShape(Shape shape, Point2D.Double p);
	
	protected void selectShape(Shape shape){
		int type = ShapeType.toInt(shape.getShapeType());
		switch(type){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:		
		}
	}

	public Color getSelectedColor() {
		return this.selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}

	public ToolType getSelectedTool() {
		return this.selectedTool;
	}

	public void setSelectedTool(ToolType tool) {
		this.selectedTool = tool;
	}

	public int getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(int selectedShape) {
		this.selectedShape = selectedShape;
	}
}
