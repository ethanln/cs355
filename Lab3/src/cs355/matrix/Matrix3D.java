package cs355.matrix;

public class Matrix3D implements IMatrix{
	private double[][] matrix;
	private final int size = 4;
	
	public Matrix3D(){
		this.matrix = new double[][]{{1.0, 0.0, 0.0, 0.0},
									 {0.0, 1.0, 0.0, 0.0},
									 {0.0, 0.0, 1.0, 0.0},
									 {0.0, 0.0, 0.0, 1.0}};
	}
	
	@Override
	public void pushMatrix(IMatrix matrix) {
		this.multiplyMatrix(this.matrix, matrix.getMatrix());
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
		double[][] rotationMatrix = new double[][]{{1.0, 0.0, 0.0, x},
												   {0.0, 1.0, 0.0, y},
												   {0.0, 0.0, 1.0, z},
												   {0.0, 0.0, 0.0, 1.0}};
		this.multiplyMatrix(this.matrix, rotationMatrix);
	}

	@Override
	public void translate(double x, double y, double z) {
		double[][] translateMatrix = new double[][]{{1.0, 0.0, 0.0, x},
													{0.0, 1.0, 0.0, y},
													{0.0, 0.0, 1.0, z},
													{0.0, 0.0, 0.0, 1.0}};
		
		this.multiplyMatrix(this.matrix, translateMatrix);
	}

	@Override
	public int getSize() {
		return this.size;
	}
	
	private void multiplyMatrix(double[][] matrixA, double[][] matrixB){
		double[][] newMatrix = new double[this.size][this.size];
		
		int n = this.size;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				for (int k = 0; k < n; k++){
					newMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
				}
			}
		}

		this.matrix = newMatrix;
	}

	@Override
	public double[][] getMatrix() {
		return this.matrix;
	}

}
