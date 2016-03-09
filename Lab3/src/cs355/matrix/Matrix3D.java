package cs355.matrix;

public class Matrix3D implements IMatrix{
	private double[][] matrix;
	private static final int size = 4;
	
	public Matrix3D(){
		this.matrix = new double[][]{{1.0, 0.0, 0.0, 0.0},
									 {0.0, 1.0, 0.0, 0.0},
									 {0.0, 0.0, 1.0, 0.0},
									 {0.0, 0.0, 0.0, 1.0}};
	}
	
	@Override
	public void pushMatrix(IMatrix matrix) {
		this.matrix = multiplyMatrix(this.matrix, matrix.getMatrix());
	}

	@Override
	public void loadIdentityMatrix() {
		this.matrix = new double[][]{{1.0, 0.0, 0.0, 0.0},
									 {0.0, 1.0, 0.0, 0.0},
									 {0.0, 0.0, 1.0, 0.0},
									 {0.0, 0.0, 0.0, 1.0}};
	}

	@Override
	public void rotate(double orientation, double x, double y, double z) {
		/*double[][] rotationMatrix = new double[][]{{1.0, 0.0, 0.0, 0.0},
													 {0.0, 1.0, 0.0, 0.0},
													 {0.0, 0.0, 1.0, 0.0},
													 {0.0, 0.0, 0.0, 1.0}};*/
		
		double[][] rotationMatrixY = new double[][]{{Math.cos(orientation),  0.0, Math.sin(orientation), 0.0},
												   {0.0, 				    1.0, 0.0, 				    0.0},
												   {-Math.sin(orientation), 0.0, Math.cos(orientation), 0.0},
												   {0.0, 					0.0, 0.0, 				    1.0}};
		
		/*double[][] rotationMatrixX = new double[][]{{1.0,  					0.0, 				   0.0, 				    0.0},
												   {0.0, 				    Math.cos(orientation), -Math.sin(orientation),  0.0},
												   {0.0, 					Math.sin(orientation), Math.cos(orientation),   0.0},
												   {0.0, 					0.0, 				   0.0, 				    1.0}};
		
		double[][] rotationMatrixZ = new double[][]{{Math.cos(orientation),  -Math.sin(orientation), 0.0, 0.0},
												    {Math.sin(orientation),  Math.cos(orientation),	 0.0, 0.0},
												    {0.0, 					 0.0, 					 1.0, 0.0},
												    {0.0, 					 0.0, 					 0.0, 1.0}};*/
		//rotationMatrix = multiplyMatrix(rotationMatrix, rotationMatrixX);
		//rotationMatrix = multiplyMatrix(rotationMatrix, rotationMatrixY);
		//rotationMatrix = multiplyMatrix(rotationMatrix, rotationMatrixZ);
		
		this.matrix = multiplyMatrix(this.matrix, rotationMatrixY);
	}

	@Override
	public void translate(double x, double y, double z) {
		double[][] translateMatrix = new double[][]{{1.0, 0.0, 0.0, x},
													{0.0, 1.0, 0.0, y},
													{0.0, 0.0, 1.0, z},
													{0.0, 0.0, 0.0, 1.0}};
		
		this.matrix = multiplyMatrix(this.matrix, translateMatrix);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public double[][] getMatrix() {
		return this.matrix;
	}
	
	@Override
	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	public void gluPerspective(double fov, double aspect, double near, double far) {
		double zoomx = 1 / Math.tan(fov / 2);
		double zoomy = 1 / Math.tan(fov / 2);
		
		double clip1 = ((far + near) / (far - near));
		double clip2 = ((-2 * near * far) / (far - near));
		
		double[][] clipMatrix = new double[][]{{zoomx, 0.0,   0.0,   0.0},
											   {0.0,   zoomy, 0.0,   0.0},
											   {0.0,   0.0,   clip1, clip2},
											   {0.0,   0.0,   1.0,   0.0}};
		this.matrix = multiplyMatrix(this.matrix, clipMatrix);
	}

	public static double[][] multiplyMatrix(double[][] matrixA, double[][] matrixB){
		double[][] newMatrix = new double[size][size];
		
		int n = size;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				for (int k = 0; k < n; k++){
					newMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
				}
			}
		}

		return newMatrix;
	}
	
	public static double[] multiplyMatrixWithVector(double[][] transformation, double[] vector){
		double[] newCoor = new double[size];
		int n = size;
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				newCoor[i] += vector[j] * transformation[i][j];
			}
		}

		return newCoor;
	}

	
	
}
