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
		GUIFunctions.setHScrollBarMax(512);
		GUIFunctions.setHScrollBarMin(0);
		
		GUIFunctions.setVScrollBarMax(512);
		GUIFunctions.setVScrollBarMin(0);
		
		GUIFunctions.setHScrollBarKnob(512);
		GUIFunctions.setVScrollBarKnob(512);
		GUIFunctions.setZoomText(0.25);
		
		GUIFunctions.changeSelectedColor(Color.WHITE);
		GUIFunctions.refresh();
	}
}
