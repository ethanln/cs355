package cs355.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cs355.GUIFunctions;
import cs355.controller.Controller;
import cs355.definitions.ShapeType;
import cs355.drawable.shape.DrawableShape;
import cs355.drawable.shape.factory.DrawableShapeFactory;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;
import cs355.model.facade.ModelFacade;
import cs355.util.HandleUtil;

public class View implements ViewRefresher, Observer{

	private Controller controller;
	public View(Controller controller){
		ModelFacade.addObserver(this);
		this.controller = controller;
	}
	
	@Override
	public void refreshView(Graphics2D g2d) {
		ArrayList<Shape> shapes = (ArrayList)ModelFacade.getShapes();
		ArrayList<Shape> overlayShapes = new ArrayList<Shape>();
		
		// fetch selected shape from the controller
		int selectedShape = this.controller.getCurrentSelectedShape();
		
		// if there is a selected shape
		if(selectedShape > -1){

			if(shapes.get(selectedShape).getShapeType() == ShapeType.LINE){ // if the shape is a line
				
				Line line = (Line) shapes.get(selectedShape);
				
				// draw the handle overlays for the line shape
				Circle overlayHandleStart = new Circle(Color.RED, line.getCenter(), 10.0 / 2.0);
				overlayHandleStart.setShapeType(ShapeType.CIRCLE);
				overlayHandleStart.setRotation(shapes.get(selectedShape).getRotation());
				
				// draw the handle overlays for the line shape
				Circle overlayHandleEnd = new Circle(Color.RED, line.getEnd(), 10.0 / 2.0);
				overlayHandleEnd.setShapeType(ShapeType.CIRCLE);
				overlayHandleEnd.setRotation(shapes.get(selectedShape).getRotation());
				
				overlayShapes.add(overlayHandleStart);
				overlayShapes.add(overlayHandleEnd);
			}
			else{  // if it is any other shape
				
				// fetches the handle center of the shape
				Point2D.Double handleCenter = HandleUtil.getHandleCenter(shapes.get(selectedShape));
				// draw the handle and border overlay for other shapes
				Circle overlayHandle = new Circle(Color.RED, handleCenter, 10.0 / 2.0);
				overlayHandle.setShapeType(ShapeType.CIRCLE);
				overlayHandle.setRotation(shapes.get(selectedShape).getRotation());
				
				overlayShapes.add(overlayHandle);
				overlayShapes.add(shapes.get(selectedShape));
			}
		}
		
		// draw model shapes
		for(int i = 0; i < shapes.size(); i++){
			DrawableShape drawing = DrawableShapeFactory.getDrawableShape(shapes.get(i));
			drawing.draw(g2d, "fill");
		}
		
		// draw overlays
		for(int i = 0; i < overlayShapes.size(); i++){
			DrawableShape drawing = DrawableShapeFactory.getDrawableShape(overlayShapes.get(i));
			drawing.draw(g2d, "border");
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		GUIFunctions.refresh();
	}
}
