package cs355.state;

import java.awt.Color;

import cs355.definitions.ToolType;

public class InterfaceState {
	private static InterfaceState instance;
	
	private ToolType currentToolSelected;
	private Color currentColorSelected;
	private int currentShapeSelected;
	
	private InterfaceState(){
		this.currentToolSelected = ToolType.NONE;
		this.currentColorSelected = Color.WHITE;
		this.currentShapeSelected = -1;
	}
	
	private static InterfaceState inst(){
		if(instance == null){
			instance = new InterfaceState();
		}
		return instance;
	}
	
	public static ToolType getCurrentToolSelected(){
		return inst().currentToolSelected;
	}
	
	public static Color getCurrentColorSelected(){
		return inst().currentColorSelected;
	}
	
	public static void setCurrentToolSelected(ToolType tool){
		if(tool != ToolType.SELECT){
			inst().currentShapeSelected = -1;
		}
		inst().currentToolSelected = tool;
	}
	
	public static void setCurrentColorSelected(Color c){
		inst().currentColorSelected = c;
	}

	public static int getCurrentShapeSelected() {
		return inst().currentShapeSelected;
	}

	public static void setCurrentShapeSelected(int currentShapeSelected) {
		inst().currentShapeSelected = currentShapeSelected;
	}
	
	public static boolean isShapeSelected(){
		return inst().currentShapeSelected != -1;
	}
	
	public static boolean isShapeToolSelected(){
		switch(ToolType.toInt(inst().currentToolSelected)){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				return true;
			default:
				return false;
		}
	}
	
}
