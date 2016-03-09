package cs355.matrix;

public interface IMatrix {
	
	void pushMatrix(IMatrix matrix);
	
	void loadIdentityMatrix();
	
	void rotate(double orientation, double x, double y, double z);
	
	void translate(double x, double y, double z);
	
	void gluPerspective(double fov, double aspect, double near, double far);
	
	int getSize();
	
	double[][] getMatrix();
	
	void setMatrix(double[][] matrix);
}
