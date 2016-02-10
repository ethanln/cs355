package cs355.solution;

import java.awt.Color;

import cs355.GUIFunctions;
import cs355.controller.Controller;
import cs355.view.View;

/**
 * This is the main class. The program starts here.
 * Make you add code below to initialize your model,
 * view, and controller and give them to the app.
 */
public class CS355 {

	/**
	 * This is where it starts.
	 * @param args = the command line arguments
	 */
	public static void main(String[] args) {

		// Fill in the parameters below with your controller and view.
		Controller controller = new Controller();
		View view = new View(controller);
		
		GUIFunctions.createCS355Frame(controller, view);
		
		double newBarKnobSize = 512 / controller.getFactor();
		
		controller.setZoomInOrOut(true);
		
		GUIFunctions.setHScrollBarMax(2048);
		GUIFunctions.setHScrollBarMin(0);
		
		GUIFunctions.setVScrollBarMax(2048);
		GUIFunctions.setVScrollBarMin(0);

		GUIFunctions.setHScrollBarKnob((int)newBarKnobSize);
		GUIFunctions.setVScrollBarKnob((int)newBarKnobSize);
		
		GUIFunctions.setHScrollBarPosit((int)controller.getScreenOrigin().getX());
		GUIFunctions.setVScrollBarPosit((int)controller.getScreenOrigin().getY());
		
		controller.setZoomInOrOut(false);
		
		GUIFunctions.setZoomText(controller.getFactor());
		
		GUIFunctions.changeSelectedColor(Color.WHITE);
		
		
		
		GUIFunctions.refresh();
	}
}
