package cs355.controller.state;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.definitions.ToolType;
import cs355.model.drawing.Shape;

public abstract class ControllerState {
	
	protected Color selectedColor;
	protected int selectedShape;
	protected Point2D.Double origin;
	
	private ToolType selectedTool;
	private boolean isRotation;
	
	public abstract void editShape(Shape shape, Point2D.Double p);
	
	public abstract Shape makeShape(Point2D.Double p);
	
	public abstract void moveShape(Shape shape, Shape overlayBorder, Shape overlayHandle, Point2D.Double p);
	
	public abstract Shape makeShapeBorder(Shape shape);
	
	public abstract Shape makeHandle(Shape shape);

	public Color getSelectedColor() {
		return this.selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}
	
	public Point2D.Double getOrigin(){
		return this.origin;
	}
	
	public void setOrigin(Point2D.Double pt){
		this.origin = pt;
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

	public boolean isRotation() {
		return isRotation;
	}

	public void setIsRotation(boolean isRotation) {
		this.isRotation = isRotation;
	}
}
