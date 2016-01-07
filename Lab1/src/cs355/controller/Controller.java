package cs355.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.definitions.ToolType;
import cs355.model.drawing.Shape;
import cs355.model.drawing.factory.ShapeFactory;
import cs355.model.facade.ModelFacade;
import cs355.state.InterfaceState;

public class Controller implements CS355Controller{

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(InterfaceState.isShapeToolSelected()){
			Shape shape = ShapeFactory.makeShape(InterfaceState.getCurrentToolSelected(), 
							new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()), 
							InterfaceState.getCurrentColorSelected());
			
			if(shape != null){
				ModelFacade.addShape(shape);	
			}
		}
		else if(InterfaceState.getCurrentToolSelected() == ToolType.SELECT){
			ArrayList<Shape> shapes = (ArrayList<Shape>) ModelFacade.getShapes();
			for(int i = shapes.size() - 1; i >= 0; i--){
				if(shapes.get(i).isShapeSelected(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()))){
					InterfaceState.setCurrentShapeSelected(i);
					break;
				}
				InterfaceState.setCurrentShapeSelected(-1);
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
		if(InterfaceState.isShapeToolSelected()){
			ModelFacade.editShape(new Point2D.Double(e.getPoint().getX(), e.getPoint().getY()));
		}
		else if(InterfaceState.getCurrentToolSelected() == ToolType.SELECT){
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
		InterfaceState.setCurrentColorSelected(c);	
		GUIFunctions.refresh();
	}

	@Override
	public void lineButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.LINE);
		GUIFunctions.refresh();
	}

	@Override
	public void squareButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.SQUARE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void rectangleButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.RECTANGLE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void circleButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.CIRCLE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void ellipseButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.ELLIPSE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void triangleButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.TRIANGLE);
		GUIFunctions.refresh();
		
	}

	@Override
	public void selectButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.SELECT);
		GUIFunctions.refresh();
	}

	@Override
	public void zoomInButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.ZOOM_IN);
		GUIFunctions.refresh();
	}

	@Override
	public void zoomOutButtonHit() {
		InterfaceState.setCurrentToolSelected(ToolType.ZOOM_OUT);
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
		if(InterfaceState.isShapeSelected()){
			ModelFacade.deleteShape(InterfaceState.getCurrentShapeSelected());
			InterfaceState.setCurrentShapeSelected(-1);
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
		if(InterfaceState.isShapeSelected()){
			int resultIndex = ModelFacade.moveForward(InterfaceState.getCurrentShapeSelected());
			InterfaceState.setCurrentShapeSelected(resultIndex);
		}
	}

	@Override
	public void doMoveBackward() {
		if(InterfaceState.isShapeSelected()){
			int resultIndex = ModelFacade.moveBackward(InterfaceState.getCurrentShapeSelected());
			InterfaceState.setCurrentShapeSelected(resultIndex);
		}	
	}

	@Override
	public void doSendToFront() {
		if(InterfaceState.isShapeSelected()){
			int resultIndex = ModelFacade.moveToFront(InterfaceState.getCurrentShapeSelected());
			InterfaceState.setCurrentShapeSelected(resultIndex);
		}
	}

	@Override
	public void doSendtoBack() {
		if(InterfaceState.isShapeSelected()){
			int resultIndex = ModelFacade.moveToBack(InterfaceState.getCurrentShapeSelected());
			InterfaceState.setCurrentShapeSelected(resultIndex);
		}
	}
	
}
