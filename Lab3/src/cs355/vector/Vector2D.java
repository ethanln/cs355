package cs355.vector;

public class Vector2D {
	private double vectorX;
	private double vectorY;
	
	public Vector2D(double _vectorX, double _vectorY){
		this.vectorX = _vectorX;
		this.vectorY = _vectorY;
	}
	
	public double getVectorX() {
		return vectorX;
	}
	
	public void setVectorX(double vectorX) {
		this.vectorX = vectorX;
	}
	
	public double getVectorY() {
		return vectorY;
	}
	
	public void setVectorY(double vectorY) {
		this.vectorY = vectorY;
	}
	
	public double getMagnitude(){
		return Math.sqrt(Math.pow(this.vectorX, 2) + Math.pow(this.vectorY, 2));
	}
	
	public static Vector2D subtractVectors(Vector2D v1, Vector2D v2){
		double newVectorX = v2.getVectorX() - v1.getVectorX();
		double newVectorY = v2.getVectorY() - v1.getVectorY();
		return new Vector2D(newVectorX, newVectorY);
	}
	
	public static Vector2D multiplyVectors(Vector2D v1, Vector2D v2){
		double newVectorX = v2.getVectorX() * v1.getVectorX();
		double newVectorY = v2.getVectorY() * v1.getVectorY();
		return new Vector2D(newVectorX, newVectorY);
	}
	
	public static Vector2D addVectors(Vector2D v1, Vector2D v2){
		double newVectorX = v2.getVectorX() + v1.getVectorX();
		double newVectorY = v2.getVectorY() + v1.getVectorY();
		return new Vector2D(newVectorX, newVectorY);
	}
	
	public static double getDotProduct(Vector2D v1, Vector2D v2){
		double dX = v1.getVectorX() * v2.getVectorX();
		double dY = v1.getVectorY() * v2.getVectorY();
		return dX + dY;
	}
}
