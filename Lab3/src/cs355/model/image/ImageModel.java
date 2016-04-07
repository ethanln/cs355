package cs355.model.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import cs355.dto.RGBDto;

public class ImageModel extends CS355Image{
	
	@Override
	public BufferedImage getImage() {
		BufferedImage image = new BufferedImage(super.getWidth(), super.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				int[] data = null;
				data = super.getPixel(j, i, data);
				Color color = new Color(data[0], data[1], data[2]);
				image.setRGB(j, i, color.getRGB());
			}
		}
		return image;
	}

	@Override
	public void edgeDetection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sharpen() {
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				Color color = getSharpen(j, i);
				super.setPixel(j, i, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
			}
		}
	}
	
	private Color getSharpen(int x, int y){
		float[] kernel = new float[]{0.0f, -1.0f,  0.0f,
									-1.0f,  6.0f, -1.0f,
									 0.0f, -1.0f,  0.0f};
		
		ArrayList<RGBDto> rgbValues = new ArrayList<RGBDto>();

		for(int i = y - 1; i < y + 2; i++){
			for(int j = x - 1; j < x + 2; j++){
				float[] values = getValue(j, i);
				RGBDto rgb = new RGBDto(values[0], values[1], values[2]);
				rgbValues.add(rgb);
			}
		}
		
		float R = 0.0f;
		float G = 0.0f;
		float B = 0.0f;
		
		for(int i = 0; i < rgbValues.size(); i++){
			R += (rgbValues.get(i).R * kernel[i]) / 2.0f;
			G += (rgbValues.get(i).G * kernel[i]) / 2.0f;
			B += (rgbValues.get(i).B * kernel[i]) / 2.0f;
		}
		
		R = Math.min(R, 255.0f);
		R = Math.max(R, 0.0f);
		
		G = Math.min(G, 255.0f);
		G = Math.max(G, 0.0f);
		
		B = Math.min(B, 255.0f);
		B = Math.max(B, 0.0f);
		
		return new Color((int)R, (int)G, (int)B);
	}

	@Override
	public void medianBlur() {
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				Color color = getMedian(j, i);
				super.setPixel(j, i, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
			}
		}
	}
	
	private Color getMedian(int x, int y){

		ArrayList<Float> mediansR = new ArrayList<Float>();
		ArrayList<Float> mediansG = new ArrayList<Float>();
		ArrayList<Float> mediansB = new ArrayList<Float>();

		for(int i = y - 1; i < y + 2; i++){
			for(int j = x - 1; j < x + 2; j++){
				float[] values = getValue(j, i);
				mediansR.add(values[0]);
				mediansG.add(values[1]);
				mediansB.add(values[2]);
			}
		}
		Float[] R = new Float[mediansR.size()];
		R =	mediansR.toArray(R);
		Float[] G = new Float[mediansG.size()];
		G =	mediansG.toArray(G);
		Float[] B = new Float[mediansB.size()];
		B =	mediansB.toArray(B);
		
		Arrays.sort(R);
		Arrays.sort(G);
		Arrays.sort(B);
		
		return new Color((int)this.calculateMedian(R), (int)this.calculateMedian(G), (int)this.calculateMedian(B));
	}
	
	private float calculateMedian(Float[] values){
		float returnValue = 0.0f;
		if(values.length % 2 == 0){
			returnValue = (values[values.length / 2] + values[(values.length / 2) - 1]) / 2.0f;
		}
		else{
			returnValue = values[(values.length / 2)];
		}
		return returnValue;
	}

	@Override
	public void uniformBlur() {
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				Color color = getAverage(j, i);
				super.setPixel(j, i, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
			}
		}	
	}
	
	private Color getAverage(int x, int y){
		float averageValueR = 0.0f;
		float averageValueG = 0.0f;
		float averageValueB = 0.0f;
		for(int i = y - 1; i < y + 2; i++){
			for(int j = x - 1; j < x + 2; j++){
				float[] values = getValue(j, i);
				averageValueR += values[0];
				averageValueG += values[1];
				averageValueB += values[2];
			}
		}

		averageValueR = Math.min(averageValueR / 9, 255);
		averageValueG = Math.min(averageValueG / 9, 255);
		averageValueB = Math.min(averageValueB / 9, 255);
	
		return new Color((int)averageValueR, (int)averageValueG, (int)averageValueB);
	}
	private float[] getValue(int x, int y){
		if(!this.validateCoordinates(x, y)){
			return new float[]{0.0f, 0.0f, 0.0f};
		}
		
		try{
			int[] rgb = null;
			rgb = super.getPixel(x, y, rgb);
			return new float[]{rgb[0], rgb[1], rgb[2]};
		}
		catch(Exception e){
			return new float[]{0.0f, 0.0f, 0.0f};
		}
	}
	
	private boolean validateCoordinates(int x, int y){
		return x >= 0 && y >= 0;
	}

	@Override
	public void grayscale() {
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){

				float[] hsb = this.getHSB(j, i);
				
				float h = hsb[0];
				float s = 0.0f;
				float b = hsb[2];
				
				Color color = Color.getHSBColor(h, s, b);
				
				super.setPixel(j, i, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
				
			}
		}
	}

	@Override
	public void contrast(int amount) {
		if(amount > 100 || amount < -100){
			return;
		}
		
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){

				float[] hsb = this.getHSB(j, i);
				
				float adjustment = (amount + 100.0f) / 100.0f;
				adjustment = (float)Math.pow(adjustment, 4);
				adjustment *= (hsb[2] - 0.5f);
				adjustment += 0.5f;
				
				float h = hsb[0];
				float s = hsb[1];
				float b = Math.min(adjustment, 1.0f);
				b = Math.max(b, 0.0f);
				
				Color color = Color.getHSBColor(h, s, b);
				
				super.setPixel(j, i, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
				
			}
		}
	}

	@Override
	public void brightness(int amount) {
		if(amount > 100 || amount < -100){
			return;
		}
		
		float normalizedAmount = 0.0f;
		normalizedAmount += amount / 100.0f;
		
	
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				float[] hsb = getHSB(j,i);
				
				float h = hsb[0];
				float s = hsb[1];
				float b = Math.min(hsb[2] + normalizedAmount, 1.0f);
				b = Math.max(b, 0.0f);
				
				Color color = Color.getHSBColor(h, s, b);
				
				super.setPixel(j, i, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
				
			}
		}
	}
	
	private float[] getHSB(int x, int y){
		int[] rgb = null;
		rgb = super.getPixel(x, y, rgb);
		
		float[] hsb = null;
		return Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsb);
	}

}
