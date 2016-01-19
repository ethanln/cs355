package cs355.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.controller.state.*;
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
				int index = ModelFacade.addShape(shape);	
				this.state.setSelectedShape(index);
			}
		}
		else if(this.state.getSelectedTool() == ToolType.SELECT){
			ArrayList<Shape> shapes = (ArrayList<Shape>) ModelFacade.getShapes();
			for(int i = shapes.size() - 1; i >= 0; i--){
				if(shapes.get(i).isInShape(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()))){
					state.setSelectedShape(i);
					break;
				}
				state.setSelectedShape(-1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		else if(this.state.getSelectedTool() == ToolType.SELECT){
			// TODO
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
		GUIFunctions.refresh();
	}

	@Override
	public void lineButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerLineState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		GUIFunctions.refresh();
	}

	@Override
	public void squareButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerSquareState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void rectangleButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerRectangleState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void circleButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerCircleState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void ellipseButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerEllipseState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void triangleButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerTriangleState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SHAPE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void selectButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerSelectState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.SELECT);
		GUIFunctions.refresh();
	}

	@Override
	public void zoomInButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerZoomInState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.ZOOM_IN);
		GUIFunctions.refresh();
	}

	@Override
	public void zoomOutButtonHit() {
		Color c = this.state.getSelectedColor();
		this.state = new ControllerZoomOutState();
		this.state.setSelectedColor(c);
		this.state.setSelectedShape(-1);
		this.state.setSelectedTool(ToolType.ZOOM_OUT);
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
