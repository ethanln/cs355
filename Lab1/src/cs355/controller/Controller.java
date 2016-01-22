package cs355.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.controller.state.*;
import cs355.definitions.ShapeType;
import cs355.definitions.ToolType;
import cs355.dto.ConvertWorldToObjDto;
import cs355.model.drawing.Shape;
import cs355.model.facade.ModelFacade;
import cs355.util.UtilFactory;
import cs355.util.WorldToObjectConverterUtil;

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
			Shape shape = this.state.makeShape(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
			if(shape != null){
				// if shape successfully creates, store the origin of the shape in the state, then add it to the model
				this.state.setOrigin(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
				int index = ModelFacade.addShape(shape);	
				
				// store the shape index as the currently selected shape in the state
				this.state.setSelectedShape(index);
			}
		}
		else if(this.state.getSelectedTool() == ToolType.SELECT){
			// get all shapes in the model
			ArrayList<Shape> shapes = (ArrayList<Shape>) ModelFacade.getShapes();
			
			for(int i = shapes.size() - 1; i >= 0; i--){
				// check if shape is selected and the click falls on the handle
				if(shapes.get(i).isSelected() && shapes.get(i).isInHandle(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()))){
					this.state.setIsRotation(true);
					return;
				}
			}
			
			for(int i = shapes.size() - 1; i >= 0; i--){
				if(shapes.get(i).pointInShape(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()), 4.0)){ // check if the selection point is within the shape and the shape is not a Select shape border
					
					// reset selected shapes
					this.resetSelection();
					
					// set origin of the click.
					this.state.setOrigin(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));

					// store the shape as selected in the state
					this.state.setSelectedShape(i);
					ModelFacade.getShape(i).setIsSelected(true);
					ModelFacade.commitChange();
					
					// set the color of new selected shape
					Color selectedShapeColor = ModelFacade.getShape(i).getColor();
					this.state.setSelectedColor(selectedShapeColor);
					GUIFunctions.changeSelectedColor(selectedShapeColor);
					GUIFunctions.refresh();

					break;
				}
				this.resetSelection();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// if the selection tool is not being used, be sure to deselect any shape
		if(this.state.getSelectedTool() != ToolType.SELECT){
			this.resetSelection();
		}
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
			this.state.editShape(ModelFacade.getShape(this.state.getSelectedShape()), new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
			ModelFacade.commitChange();
		}
		else if(this.state.getSelectedTool() == ToolType.SELECT && this.state.getSelectedShape() > -1){
			Shape shape = ModelFacade.getShape(this.state.getSelectedShape());
			if(!this.state.isRotation()){
				if(shape.getShapeType() != ShapeType.LINE){
					this.state.moveShape(shape, new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
				}
				else{
					this.state.moveShape(shape, new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
				}
			}
			else{
				WorldToObjectConverterUtil converter = (WorldToObjectConverterUtil)UtilFactory.makeUtil("world_to_object_converter");
				// instantiate dto to be passed into the converter
				ConvertWorldToObjDto dto = new ConvertWorldToObjDto(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()), 
																	shape.getCenter(), shape.getRotation());
				// convert the point of interst to object coordinates
				Point2D.Double objCoor = (Point2D.Double)converter.doUtil(dto);
				
				double newRotation = shape.getRotation() + Math.atan(-(objCoor.getX() / objCoor.getY()));;
				if(objCoor.getY() < 0){
					newRotation = shape.getRotation() + Math.atan((objCoor.getX() / -objCoor.getY()));
				}
				else if(objCoor.getX() < 0){
					newRotation = shape.getRotation() + Math.atan((-objCoor.getX() / objCoor.getY()));
				}

				shape.setRotation(newRotation);
				// IMPLEMENT ROTATION
			}
			ModelFacade.commitChange();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void resetSelection(){
		if(this.state.getSelectedShape() > -1){
			ModelFacade.getShape(this.state.getSelectedShape()).setIsSelected(false);
			ModelFacade.commitChange();
			this.state.setSelectedShape(-1);
		}
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
		
		this.state = new ControllerLineState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
	}

	@Override
	public void squareButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		
		this.state = new ControllerSquareState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
		
	}

	@Override
	public void rectangleButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		
		this.state = new ControllerRectangleState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
		
	}

	@Override
	public void circleButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		
		this.state = new ControllerCircleState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
		
	}

	@Override
	public void ellipseButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		
		this.state = new ControllerEllipseState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
		
	}

	@Override
	public void triangleButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		
		this.state = new ControllerTriangleState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SHAPE);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
	}

	@Override
	public void selectButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		
		this.state = new ControllerSelectState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.SELECT);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
	}

	@Override
	public void zoomInButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		
		this.state = new ControllerZoomInState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.ZOOM_IN);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
	}

	@Override
	public void zoomOutButtonHit() {
		Color c = this.state.getSelectedColor();
		int selectedShapeIndex = this.state.getSelectedShape();
		
		this.state = new ControllerZoomOutState();
		this.state.setSelectedShape(selectedShapeIndex);
		this.state.setSelectedColor(c);
		this.state.setSelectedTool(ToolType.ZOOM_OUT);
		
		this.resetSelection();
		
		GUIFunctions.refresh();
	}

	@Override
	public void hScrollbarChanged(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vScrollbarChanged(int value) {
		// TODO Auto-generated method stub
		
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
		this.resetSelection();
		ModelFacade.save(file);
	}

	@Override
	public void openDrawing(File file) {
		ModelFacade.open(file);
	}

	@Override
	public void doDeleteShape() {
		if(this.state.getSelectedShape() > -1){
			ModelFacade.deleteShape(this.state.getSelectedShape());
		}
		this.resetSelection();
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
		}
	}

	@Override
	public void doMoveBackward() {
		if(this.state.getSelectedShape() > -1){
			int resultIndex = ModelFacade.moveBackward(this.state.getSelectedShape());
			this.state.setSelectedShape(resultIndex);
		}	
	}

	@Override
	public void doSendToFront() {
		if(this.state.getSelectedShape() > -1){
			int resultIndex = ModelFacade.moveToFront(this.state.getSelectedShape());
			this.state.setSelectedShape(resultIndex);
		}
	}

	@Override
	public void doSendtoBack() {
		if(this.state.getSelectedShape() > -1){
			int resultIndex = ModelFacade.moveToBack(this.state.getSelectedShape());
			this.state.setSelectedShape(resultIndex);
		}
	}
	
}
