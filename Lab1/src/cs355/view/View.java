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
import cs355.util.UtilFactory;

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
		
		int selectedShape = this.controller.getCurrentSelectedShape();
		
		if(selectedShape > -1){

			if(shapes.get(selectedShape).getShapeType() == ShapeType.LINE){ // if the shape is a line
				// draw the handle overlays for the line shape
				Line line = (Line) shapes.get(selectedShape);
				Circle overlayHandleStart = new Circle(Color.RED, line.getCenter(), 10.0 / 2.0);
				overlayHandleStart.setShapeType(ShapeType.CIRCLE);
				overlayHandleStart.setRotation(shapes.get(selectedShape).getRotation());
				
				Circle overlayHandleEnd = new Circle(Color.RED, line.getEnd(), 10.0 / 2.0);
				overlayHandleEnd.setShapeType(ShapeType.CIRCLE);
				overlayHandleEnd.setRotation(shapes.get(selectedShape).getRotation());
				
				overlayShapes.add(overlayHandleStart);
				overlayShapes.add(overlayHandleEnd);
			}
			else{  // if it is any other shape
				// draw the handle and border overlay for other shapes
				Point2D.Double handleCenter = (Point2D.Double)UtilFactory.makeUtil("handle-center").doUtil(shapes.get(selectedShape));
				
				Circle overlayHandle = new Circle(Color.RED, handleCenter, 10.0 / 2.0);
				overlayHandle.setShapeType(ShapeType.CIRCLE);
				overlayHandle.setRotation(shapes.get(selectedShape).getRotation());
				
				overlayShapes.add(overlayHandle);
				overlayShapes.add(shapes.get(selectedShape));
			}
		}
		
		for(int i = 0; i < shapes.size(); i++){
			DrawableShape drawing = DrawableShapeFactory.getDrawableShape(shapes.get(i));
			drawing.draw(g2d, "fill");
		}
		
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
