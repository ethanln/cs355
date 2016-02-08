package cs355.controller.state;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.definitions.HandleType;
import cs355.definitions.ToolType;
import cs355.model.drawing.Shape;

public abstract class ControllerState {
	
	protected Color selectedColor;
	protected int selectedShape;
	protected Point2D.Double origin;
	
	protected double factor = 1.0;
	protected Point2D.Double screenOrigin = new Point2D.Double(0.0, 0.0);
	protected int HScrollPos = 0;
	protected int VScrollPos = 0;
	
	private ToolType selectedTool;
	private boolean isRotation;
	private boolean isDrawing;
	private boolean isZoomInOrOut = false;
	private double screenSize = 512.0;
	private double scrollScreenSize = 0.0;
	
	private HandleType rotationHandle = HandleType.NONE;
	
	public abstract void editShape(Shape shape, Point2D.Double p);
	
	public abstract Shape makeShape(Point2D.Double p);
	
	public abstract void moveShape(Shape shape, Point2D.Double p);
	
	public abstract void rotateShape(Shape shape, Point2D.Double p);

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
	
	public double getFactor(){
		return this.factor;
	}
	
	public void setFactor(double newFactor){
		this.factor = newFactor;
	}
	
	public double getScreenSize(){
		return this.screenSize;
	}
	
	public void setScreenSize(double newScreenSize){
		this.screenSize = newScreenSize;
	}
	
	public int getHScrollPos(){
		return this.HScrollPos;
	}
	
	public void setHScrollPos(int newHScrollPos){
		this.HScrollPos = newHScrollPos;
	}
	
	public int getVScrollPos(){
		return this.VScrollPos;
	}
	
	public void setVScrollPos(int newVScrollPos){
		this.VScrollPos = newVScrollPos;
	}
	
	public Point2D.Double getScreenOrigin(){
		return this.screenOrigin;
	}
	
	public void setScreenOrigin(Point2D.Double newScreenOrigin){
		this.screenOrigin = newScreenOrigin;
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

	public boolean isDrawing() {
		return isDrawing;
	}

	public void setIsDrawing(boolean isDrawing) {
		this.isDrawing = isDrawing;
	}

	public HandleType getRotationHandle() {
		return rotationHandle;
	}

	public void setRotationHandle(HandleType rotationHandle) {
		this.rotationHandle = rotationHandle;
	}

	public boolean isZoomInOrOut() {
		return isZoomInOrOut;
	}

	public void setZoomInOrOut(boolean isZoomInOrOut) {
		this.isZoomInOrOut = isZoomInOrOut;
	}

	public double getScrollScreenSize() {
		return scrollScreenSize;
	}

	public void setScrollScreenSize(double scrollScreenSize) {
		this.scrollScreenSize = scrollScreenSize;
	}
}
