package cs355.matrix;

public interface IMatrix {
	
	void pushMatrix(IMatrix matrix);
	
	void loadIdentityMatrix();
	
	void rotate(double orientation, double x, double y, double z);
	
	void translate(double x, double y, double z);
	
	int getSize();
	
	double[][] getMatrix();
}
