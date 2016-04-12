package cs355.model.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import cs355.dto.HSBDto;
import cs355.dto.RGBDto;

public class ImageModel extends CS355Image{
	
	@Override
	public BufferedImage getImage() {
		
		if(!(super.getWidth() > 0 && super.getHeight() > 0)){
			return null;
		}
		
		BufferedImage image = new BufferedImage(super.getWidth(), super.getHeight(), BufferedImage.TYPE_INT_RGB);
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
		BufferedImage image = new BufferedImage(super.getWidth(), super.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				Color color = getEdge(j, i);
				image.setRGB(j, i, color.getRGB());
			}
		}
		super.setPixels(image);
	}
	
	private Color getEdge(int x, int y){
		float[] sobelX = new float[]{-1.0f, 0.0f,  1.0f,
									 -2.0f, 0.0f,  2.0f,
									 -1.0f, 0.0f,  1.0f};
		
		float[] sobelY = new float[]{-1.0f, -2.0f, -1.0f,
									  0.0f,  0.0f, 	0.0f,
									  1.0f,  2.0f,  1.0f};
		


		ArrayList<HSBDto> hsbValues = new ArrayList<HSBDto>();

		for(int i = y - 1; i < y + 2; i++){
			for(int j = x - 1; j < x + 2; j++){
				float[] rgb = getValue(j, i);

				float[] hsb = null;
				hsb = Color.RGBtoHSB((int)rgb[0], (int)rgb[1], (int)rgb[2], hsb);

				HSBDto hsbdto = new HSBDto(hsb[0], hsb[1], hsb[2]);
				hsbValues.add(hsbdto);
			}
		}
		
		//float Hx = 0.0f;
		//float Sx = 0.0f;
		float Bx = 0.0f;
		
		for(int i = 0; i < hsbValues.size(); i++){
			//Hx += (hsbValues.get(i).H * sobelX[i]) / 8.0f;
			//Sx += (hsbValues.get(i).S * sobelX[i]) / 8.0f;
			Bx += (hsbValues.get(i).B * sobelX[i]) / 8.0f;
		}
		
		//float Hy = 0.0f;
		//float Sy = 0.0f;
		float By = 0.0f;
		
		for(int i = 0; i < hsbValues.size(); i++){
			//Hy += (hsbValues.get(i).H * sobelY[i]) / 8.0f;
			//Sy += (hsbValues.get(i).S * sobelY[i]) / 8.0f;
			By += (hsbValues.get(i).B * sobelY[i]) / 8.0f;
		}
		
		//float H = (float)Math.sqrt((float)Math.pow(Hx, 2.0f) + (float)Math.pow(Hy, 2.0f));
		//float S = (float)Math.sqrt((float)Math.pow(Sx, 2.0f) + (float)Math.pow(Sy, 2.0f));
		float B = (float)Math.sqrt((float)Math.pow(Bx, 2.0f) + (float)Math.pow(By, 2.0f));
		
		//H = Math.min(H, 1.0f);
		//H = Math.max(H, 0.0f);
		
		//S = Math.min(S, 1.0f);
		//S = Math.max(S, 0.0f);
		
		B = Math.min(B, 1.0f);
		B = Math.max(B, 0.0f);
		return Color.getHSBColor(0.0f, 0.0f, B);
		//Color color = new Color((int)B, (int)B, (int)B);
		//return color;
	}

	@Override
	public void sharpen() {
		BufferedImage image = new BufferedImage(super.getWidth(), super.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				Color color = getSharpen(j, i);
				image.setRGB(j, i, color.getRGB());
			}
		}
		super.setPixels(image);
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
		BufferedImage image = new BufferedImage(super.getWidth(), super.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				Color color = getMedian(j, i);
				image.setRGB(j, i, color.getRGB());
			}
		}
		super.setPixels(image);
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
		BufferedImage image = new BufferedImage(super.getWidth(), super.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < super.getHeight(); i++){
			for(int j = 0; j < super.getWidth(); j++){
				Color color = getAverage(j, i);
				image.setRGB(j, i, color.getRGB());
			}
		}
		super.setPixels(image);
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
