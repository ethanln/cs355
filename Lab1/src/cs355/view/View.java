package cs355.view;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cs355.GUIFunctions;
import cs355.drawable.shape.DrawableShape;
import cs355.drawable.shape.factory.DrawableShapeFactory;
import cs355.model.drawing.Shape;
import cs355.model.facade.ModelFacade;

public class View implements ViewRefresher, Observer{

	public View(){
		ModelFacade.addObserver(this);
	}
	
	@Override
	public void refreshView(Graphics2D g2d) {
		ArrayList<Shape> shapes = (ArrayList)ModelFacade.getShapes();
		
		for(int i = 0; i < shapes.size(); i++){
			DrawableShape drawing = DrawableShapeFactory.getDrawableShape(shapes.get(i));
			drawing.draw(g2d);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		GUIFunctions.refresh();
	}
}
