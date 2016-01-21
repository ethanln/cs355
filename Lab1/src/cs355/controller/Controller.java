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
import cs355.model.drawing.Shape;
import cs355.model.facade.ModelFacade;

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
				// check if the selection point is within the shape and the shape is not a Select shape border
				if(shapes.get(i).pointInShape(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()), 4.0) 
						&& !shapes.get(i).isSelectedBorder()){
					// set origin of the click.
					this.state.setOrigin(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
					
					if(!shapes.get(i).isHandle()){
						
						this.clearOverlays();
						
						// store the shape as selected in the state
						this.state.setSelectedShape(i);
						
						// set the color of new selected shape
						Color selectedShapeColor = ModelFacade.getShape(i).getColor();
						this.state.setSelectedColor(selectedShapeColor);
						GUIFunctions.changeSelectedColor(selectedShapeColor);
						GUIFunctions.refresh();

						// add new border selection overlay to the model
						Shape shapeBorder = this.state.makeShapeBorder(shapes.get(i));
						Shape handle = this.state.makeHandle(shapes.get(i));
						
						// if either the border or handle are null, do not include any overlay
						if(shapeBorder != null && handle != null){
							ModelFacade.addShape(this.state.makeShapeBorder(shapes.get(i)));
							ModelFacade.addShape(this.state.makeHandle(shapes.get(i)));
						}
					}
					else{
						// if the shape type is a handle bar, set the state to rotation
						this.state.setIsRotation(true);
					}
					break;
				}
				state.setSelectedShape(-1);
			}
			
			// if no shapes are selected, clear all the overlays
			if(state.getSelectedShape() == -1){
				this.clearOverlays();
			}
		}
	}
	
	private void clearOverlays(){
		if(ModelFacade.getShapes().size() > 1){
			if(ModelFacade.getShapes().get(ModelFacade.getShapes().size() - 2).isSelectedBorder()){
				ModelFacade.deleteShape(ModelFacade.getShapes().size() - 2);
			}
			if (ModelFacade.getShapes().get(ModelFacade.getShapes().size() - 1).isHandle()){
				ModelFacade.deleteShape(ModelFacade.getShapes().size() - 1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// if the selection tool is not being used, be sure to deselect any shape
		if(this.state.getSelectedTool() != ToolType.SELECT){
			this.state.setSelectedShape(-1);
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
					this.state.moveShape(shape, 
								ModelFacade.getShape(ModelFacade.getShapes().size() - 2), 
								ModelFacade.getShape(ModelFacade.getShapes().size() - 1), 
								new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
				}
				else{
					this.state.moveShape(shape, 
								null, null, 
								new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
				}
				ModelFacade.commitChange();
			}
			else{
				Shape overlayBorder = ModelFacade.getShape(ModelFacade.getShapes().size() - 2);
				Shape overlayHandle = ModelFacade.getShape(ModelFacade.getShapes().size() - 1);
				// IMPLEMENT ROTATION
			}
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
		this.state = new ControllerLineState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.clearOverlays();
		GUIFunctions.refresh();
	}

	@Override
	public void squareButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerSquareState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.clearOverlays();
		GUIFunctions.refresh();
		
	}

	@Override
	public void rectangleButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerRectangleState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.clearOverlays();
		GUIFunctions.refresh();
		
	}

	@Override
	public void circleButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerCircleState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.clearOverlays();
		GUIFunctions.refresh();
		
	}

	@Override
	public void ellipseButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerEllipseState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.clearOverlays();
		GUIFunctions.refresh();
		
	}

	@Override
	public void triangleButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerTriangleState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		this.clearOverlays();
		GUIFunctions.refresh();
		
	}

	@Override
	public void selectButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerSelectState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SELECT);
		this.clearOverlays();
		GUIFunctions.refresh();
	}

	@Override
	public void zoomInButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerZoomInState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.ZOOM_IN);
		this.clearOverlays();
		GUIFunctions.refresh();
	}

	@Override
	public void zoomOutButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerZoomOutState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.ZOOM_OUT);
		this.clearOverlays();
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
		this.clearOverlays();
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
			this.state.setSelectedShape(-1);
			this.clearOverlays();
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
