package cs355.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cs355.GUIFunctions;
import cs355.definitions.ShapeType;
import cs355.drawable.shape.DrawableShape;
import cs355.drawable.shape.factory.DrawableShapeFactory;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;
import cs355.model.facade.ModelFacade;

public class View implements ViewRefresher, Observer{

	public View(){
		ModelFacade.addObserver(this);
	}
	
	@Override
	public void refreshView(Graphics2D g2d) {
		ArrayList<Shape> shapes = (ArrayList)ModelFacade.getShapes();
		ArrayList<Shape> overlayShapes = new ArrayList<Shape>();
		
		for(int i = 0; i < shapes.size(); i++){
			if(shapes.get(i).isSelected()){
				if(shapes.get(i).getShapeType() == ShapeType.LINE){
					
				}
				else{
					Circle overlayHandle = new Circle(Color.RED, shapes.get(i).getHandleCenter(), 10.0 / 2.0);
					overlayHandle.setShapeType(ShapeType.CIRCLE);
					overlayShapes.add(overlayHandle);
					overlayShapes.add(shapes.get(i));
				}
			}
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
