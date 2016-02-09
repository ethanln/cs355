package cs355.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.controller.state.*;
import cs355.definitions.HandleType;
import cs355.definitions.ToolType;
import cs355.dto.*;
import cs355.model.drawing.Shape;
import cs355.model.facade.ModelFacade;
import cs355.util.HandleUtil;

public class Controller implements CS355Controller{

	private ControllerState state;
	
	public Controller(){
		this.state = new ControllerDefaultState();
		this.state.setSelectedColor(Color.WHITE);
		this.state.setSelectedTool(ToolType.DEFAULT);
		this.state.setSelectedShape(-1);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(this.state.getSelectedTool() == ToolType.SHAPE){
			// draw shape
			this.doShapeTool(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
		}
		else if(this.state.getSelectedTool() == ToolType.SELECT){ // if current tool is Select
			
			// check if shape is selected and the click falls on the handle
			if(this.checkIfHandleIsSelected(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()))){
				return;
			}
			
			// check of the click is within any of the shapes from front to back
			if(this.checkIfShapeIsSelected(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()))){
				return;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// if the selection tool is not being used, be sure to deselect any shape
		if(this.state.getSelectedTool() != ToolType.SELECT){
			this.resetSelection();
		}
		// cease rotation 
		if(this.state.isRotation()){
			this.state.setIsRotation(false);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.state.getSelectedTool() == ToolType.SHAPE && this.state.getSelectedShape() != -1){
			// draw and edit shape from initial click
			this.state.editShape(ModelFacade.getShape(this.state.getSelectedShape()), new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
			ModelFacade.commitChange();
		}
		else if(this.state.getSelectedTool() == ToolType.SELECT && this.state.getSelectedShape() > -1){
			Shape shape = ModelFacade.getShape(this.state.getSelectedShape());
			if(!this.state.isRotation()){ 
				// move the shape
				this.state.moveShape(shape, new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
			}
			else{ 
				// rotate shape
				this.state.rotateShape(shape, new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
			}
			ModelFacade.commitChange();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void colorButtonHit(Color c) {
		GUIFunctions.changeSelectedColor(c);
		this.state.setSelectedColor(c);
		
		// change color of currently selected shape, if there is one selected
		if(this.state.getSelectedShape() != -1){
			this.state.editShape(ModelFacade.getShape(this.state.getSelectedShape()), null);
		}
		
		GUIFunctions.refresh();
	}

	@Override
	public void lineButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		double factor = this.state.getFactor();
		Point2D.Double screenOrigin = this.state.getScreenOrigin();
		int vScroll = this.state.getVScrollPos();
		int hScroll = this.state.getHScrollPos();
		
		this.state = new ControllerLineState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.state.setFactor(factor);
		this.state.setScreenOrigin(screenOrigin);
		
		this.state.setVScrollPos(vScroll);
		this.state.setHScrollPos(hScroll);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
	}

	@Override
	public void squareButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		double factor = this.state.getFactor();
		Point2D.Double screenOrigin = this.state.getScreenOrigin();
		int vScroll = this.state.getVScrollPos();
		int hScroll = this.state.getHScrollPos();
		
		this.state = new ControllerSquareState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.state.setFactor(factor);
		this.state.setScreenOrigin(screenOrigin);
		
		this.state.setVScrollPos(vScroll);
		this.state.setHScrollPos(hScroll);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
		
	}

	@Override
	public void rectangleButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		double factor = this.state.getFactor();
		Point2D.Double screenOrigin = this.state.getScreenOrigin();
		int vScroll = this.state.getVScrollPos();
		int hScroll = this.state.getHScrollPos();
		
		this.state = new ControllerRectangleState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.state.setFactor(factor);
		this.state.setScreenOrigin(screenOrigin);
		
		this.state.setVScrollPos(vScroll);
		this.state.setHScrollPos(hScroll);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
		
	}

	@Override
	public void circleButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		double factor = this.state.getFactor();
		Point2D.Double screenOrigin = this.state.getScreenOrigin();
		int vScroll = this.state.getVScrollPos();
		int hScroll = this.state.getHScrollPos();
		
		this.state = new ControllerCircleState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.state.setFactor(factor);
		this.state.setScreenOrigin(screenOrigin);
		
		this.state.setVScrollPos(vScroll);
		this.state.setHScrollPos(hScroll);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
		
	}

	@Override
	public void ellipseButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		double factor = this.state.getFactor();
		Point2D.Double screenOrigin = this.state.getScreenOrigin();
		int vScroll = this.state.getVScrollPos();
		int hScroll = this.state.getHScrollPos();
		
		this.state = new ControllerEllipseState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.state.setFactor(factor);
		this.state.setScreenOrigin(screenOrigin);
		
		this.state.setVScrollPos(vScroll);
		this.state.setHScrollPos(hScroll);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
		
	}

	@Override
	public void triangleButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		double factor = this.state.getFactor();
		Point2D.Double screenOrigin = this.state.getScreenOrigin();
		int vScroll = this.state.getVScrollPos();
		int hScroll = this.state.getHScrollPos();
		
		this.state = new ControllerTriangleState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.state.setFactor(factor);
		this.state.setScreenOrigin(screenOrigin);
		
		this.state.setVScrollPos(vScroll);
		this.state.setHScrollPos(hScroll);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
	}

	@Override
	public void selectButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		double factor = this.state.getFactor();
		Point2D.Double screenOrigin = this.state.getScreenOrigin();
		int vScroll = this.state.getVScrollPos();
		int hScroll = this.state.getHScrollPos();
		
		this.state = new ControllerSelectState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SELECT);
		this.state.setFactor(factor);
		this.state.setScreenOrigin(screenOrigin);
		
		this.state.setVScrollPos(vScroll);
		this.state.setHScrollPos(hScroll);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
	}

	/**
	 * zoom in
	 */
	@Override
	public void zoomInButtonHit() {
		
		double newFactor = this.state.getFactor() * 2.0;
		
		// check if newFactor is beyond its limit
		if(newFactor > 4.0){
			return;
		}
		
		this.state.setFactor(newFactor);
		
		double val = 0;
		double newX = 0.0;
		double newY = 0.0;
		
		if(newFactor < 1.0){
			val = 512.0;
			newX = this.state.getScreenOrigin().getX() + val;
			newY = this.state.getScreenOrigin().getY() + val;
		}
		else if(newFactor > 1.0){
			if(newFactor == 2.0){
				val = 128.0;
				newX = this.state.getScreenOrigin().getX() + val;
				newY = this.state.getScreenOrigin().getY() + val;
			}
			else if(newFactor == 4.0){
				val = 64.0;
				newX = this.state.getScreenOrigin().getX() + val;
				newY = this.state.getScreenOrigin().getY() + val;
			}

		}
		else{
			val = 256.0;
			newX = this.state.getScreenOrigin().getX() + val;
			newY = this.state.getScreenOrigin().getY() + val;
		}
		
		this.state.setScreenOrigin(new Point2D.Double(newX, newY));

		GUIFunctions.setZoomText(newFactor);
		
		this.state.setVScrollPos((int)this.state.getScreenOrigin().getY());
		this.state.setHScrollPos((int)this.state.getScreenOrigin().getX());
		
		this.state.setZoomInOrOut(true);

		GUIFunctions.setVScrollBarMax(2048);
		GUIFunctions.setHScrollBarMax(2048);

		double newBarKnobSize = 512 / newFactor;
		GUIFunctions.setHScrollBarKnob((int)newBarKnobSize);
		GUIFunctions.setVScrollBarKnob((int)newBarKnobSize);
		
		this.state.setZoomInOrOut(false);
		
		GUIFunctions.setVScrollBarPosit(this.state.getVScrollPos());
		GUIFunctions.setHScrollBarPosit(this.state.getHScrollPos());
		
		GUIFunctions.refresh();
		
	}

	/**
	 * zoom out
	 */
	@Override
	public void zoomOutButtonHit() {
		
		double newFactor = this.state.getFactor() / 2.0;
		
		// check if newFactor is beyond its limit
		if(newFactor < 0.25){
			return;
		}
		
		double val = 0;
		double newX = 0.0;
		double newY = 0.0;
		
		if(newFactor < 1.0){
			if(newFactor == 0.5){
				val = 256.0;
				newX = this.state.getScreenOrigin().getX() - val;
				newY = this.state.getScreenOrigin().getY() - val;
			}
		}
		else if(newFactor > 1.0){
			val = 64.0;
			newX = this.state.getScreenOrigin().getX() - val;
			newY = this.state.getScreenOrigin().getY() - val;
		}
		else{
			val = 128.0;
			newX = this.state.getScreenOrigin().getX() - val;
			newY = this.state.getScreenOrigin().getY() - val;
		}
		
		if(newX + (512 / newFactor) > 2048.0
				&& newY + (512 / newFactor) > 2048.0){
			this.state.setScreenOrigin(new Point2D.Double(2048.0 - (512 / newFactor), 2048.0 - (512 / newFactor)));
		}
		else if(newX + (512 / newFactor) > 2048.0){
			this.state.setScreenOrigin(new Point2D.Double(2048.0 - (512 / newFactor), Math.max(newY, 0.0)));
		}
		else if(newY + (512 / newFactor) > 2048.0){
			this.state.setScreenOrigin(new Point2D.Double(Math.max(newX, 0.0), 2048.0 - (512 / newFactor)));
		}
		else{
			this.state.setScreenOrigin(new Point2D.Double(Math.max(newX, 0.0), Math.max(newY, 0.0)));
		}
		
		this.state.setFactor(newFactor);

		GUIFunctions.setZoomText(newFactor);
			
		this.state.setVScrollPos((int)this.state.getScreenOrigin().getY());
		this.state.setHScrollPos((int)this.state.getScreenOrigin().getX());
		
		this.state.setZoomInOrOut(true);
		
		if(newFactor == .25){
			GUIFunctions.setVScrollBarMax(0);
			GUIFunctions.setHScrollBarMax(0);
		}
		else{
			GUIFunctions.setVScrollBarMax(2048);
			GUIFunctions.setHScrollBarMax(2048);
		}

		
		this.state.setZoomInOrOut(false);
		
		if(newFactor == 0.25){
			GUIFunctions.setVScrollBarPosit(0);
			GUIFunctions.setHScrollBarPosit(0);
		}
		else{
			GUIFunctions.setVScrollBarPosit(this.state.getVScrollPos());
			GUIFunctions.setHScrollBarPosit(this.state.getHScrollPos());
		}
	
		double newBarKnobSize = 512 / newFactor;
		GUIFunctions.setHScrollBarKnob((int)newBarKnobSize);
		GUIFunctions.setVScrollBarKnob((int)newBarKnobSize);

		GUIFunctions.refresh();
	}

	/**
	 * horizontal scroll bar change
	 * @param value
	 */
	@Override
	public void hScrollbarChanged(int value) {
		if(this.state.isZoomInOrOut()){
			return;
		}
		
		int changeAmount = value - this.state.getHScrollPos();
		
		this.state.setHScrollPos(value);
		this.state.setScreenOrigin(new Point2D.Double(this.state.getScreenOrigin().getX() + changeAmount, this.state.getScreenOrigin().getY()));
		GUIFunctions.refresh();
	}

	/**
	 * vertical scroll bar change
	 * @param value
	 */
	@Override
	public void vScrollbarChanged(int value) {
		if(this.state.isZoomInOrOut()){
			return;
		}
		
		int changeAmount = value - this.state.getVScrollPos();
		
		this.state.setVScrollPos(value);
		this.state.setScreenOrigin(new Point2D.Double(this.state.getScreenOrigin().getX(), this.state.getScreenOrigin().getY() + changeAmount));
		GUIFunctions.refresh();
	}

	@Override
	public void openScene(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggle3DModelDisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openImage(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveImage(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleBackgroundDisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDrawing(File file) {
		ModelFacade.save(file);
	}

	@Override
	public void openDrawing(File file) {
		this.resetSelection();
		ModelFacade.open(file);
	}

	@Override
	public void doDeleteShape() {
		if(this.state.getSelectedShape() > -1){
			int selectedShape = this.state.getSelectedShape();
			this.state.setSelectedShape(-1);
			ModelFacade.deleteShape(selectedShape);
		}
	}

	@Override
	public void doEdgeDetection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSharpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doMedianBlur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUniformBlur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doGrayscale() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doChangeContrast(int contrastAmountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doChangeBrightness(int brightnessAmountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doMoveForward() {
		if(this.state.getSelectedShape() > -1){
			int resultIndex = ModelFacade.moveForward(this.state.getSelectedShape());
			this.state.setSelectedShape(resultIndex);
			ModelFacade.commitChange();
		}
	}

	@Override
	public void doMoveBackward() {
		if(this.state.getSelectedShape() > -1){
			int resultIndex = ModelFacade.moveBackward(this.state.getSelectedShape());
			this.state.setSelectedShape(resultIndex);
			ModelFacade.commitChange();
		}	
	}

	@Override
	public void doSendToFront() {
		if(this.state.getSelectedShape() > -1){
			int resultIndex = ModelFacade.moveToFront(this.state.getSelectedShape());
			this.state.setSelectedShape(resultIndex);
			ModelFacade.commitChange();
		}
	}

	@Override
	public void doSendtoBack() {
		if(this.state.getSelectedShape() > -1){
			int resultIndex = ModelFacade.moveToBack(this.state.getSelectedShape());
			this.state.setSelectedShape(resultIndex);
			ModelFacade.commitChange();
		}
	}
	
	// Additional Methods:
	public int getCurrentSelectedShape(){
		if(!this.state.isDrawing()){
			return this.state.getSelectedShape();
		}
		else{
			return -1;
		}
	}
	
	public double getFactor(){
		return this.state.getFactor();
	}
	
	public Point2D.Double getScreenOrigin(){
		return this.state.getScreenOrigin();
	}
	
	private void resetSelection(){
		// reset any shapes that are marked as selected
		if(this.state.getSelectedShape() > -1){
			this.state.setSelectedShape(-1);
			ModelFacade.commitChange();
		}
	}
	

	/**
	 * draws shape
	 * @param pt
	 */
	private void doShapeTool(Point2D.Double pt){
		Shape shape = this.state.makeShape(new Point2D.Double(pt.getX(), pt.getY()));
		if(shape != null){
			// if shape successfully creates, store the origin of the shape in the state, then add it to the model
			this.state.setOrigin(new Point2D.Double(pt.getX(), pt.getY()));
			int index = ModelFacade.addShape(shape);	
			
			// store the shape index as the currently selected shape in the state
			this.state.setSelectedShape(index);
			this.state.setIsDrawing(true);
		}
	}
	
	/**
	 * check if shape is selected and the click falls on the handle
	 * @param pt
	 * @return
	 */
	private boolean checkIfHandleIsSelected(Point2D.Double pt){
		if(this.state.getSelectedShape() > -1){
			PointInHandleDto dto = new PointInHandleDto(ModelFacade.getShape(this.state.getSelectedShape()), 
								   new Point2D.Double(pt.getX(), pt.getY()),
								   this.state.getFactor(),
								   this.state.getScreenOrigin());

			HandleType handleType = HandleUtil.isPointInHandle(dto);
			if(handleType != HandleType.NONE){
				this.state.setIsRotation(true);
				this.state.setRotationHandle(handleType);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check of the click is within any of the shapes from front to back
	 * @param pt
	 * @return
	 */
	private boolean checkIfShapeIsSelected(Point2D.Double pt){
		// get all shapes from the model
		ArrayList<Shape> shapes = (ArrayList<Shape>) ModelFacade.getShapes();
		for(int i = shapes.size() - 1; i >= 0; i--){
			// if click falls within a shape
			if(shapes.get(i).pointInShape(new Point2D.Double(pt.getX(), pt.getY()), this.state.getScreenOrigin(), 4.0, this.state.getFactor())){ // check if the selection point is within the shape and the shape is not a Select shape border
				
				// reset selected shapes
				this.resetSelection();
				
				// set origin of the click.
				this.state.setOrigin(new Point2D.Double(pt.getX(), pt.getY()));

				// store the shape as selected in the state
				this.state.setSelectedShape(i);
				
				// set the color of new selected shape
				Color selectedShapeColor = ModelFacade.getShape(i).getColor();
				this.state.setSelectedColor(selectedShapeColor);
				GUIFunctions.changeSelectedColor(selectedShapeColor);
				GUIFunctions.refresh();

				return true;
			}
			this.resetSelection();
		}
		return false;
	}
	
}
