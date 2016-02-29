package Util;

import java.awt.Color;

public class ColorGenerator {
	public static Color generateColor(int i){

		
		switch(i){
			case 0:
				return Color.BLUE;
			case 20:
				return Color.RED;
			case 40:
				return Color.CYAN;
			case 60:
				return Color.ORANGE;
			case 80:
				return Color.GREEN;
			case 100:
				return Color.YELLOW;
			default:
					return Color.WHITE;
		}
	}
}
