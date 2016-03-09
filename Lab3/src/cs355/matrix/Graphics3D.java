package cs355.matrix;

import cs355.definitions.MatrixMode;

public class Graphics3D {
	
	private IMatrix modelView;
	private IMatrix projection;
	private IMatrix currentMatrix;
	
	public Graphics3D(){
		this.modelView = new Matrix3D();
		this.projection = new Matrix3D();
		this.currentMatrix = null;
	}
	
	public void mode(MatrixMode mode){
		// change matrix mode
		switch(MatrixMode.toInt(mode)){
		case 0:
			this.currentMatrix = null;
			break;
		case 1:
			this.currentMatrix = modelView;
			break;
		case 2:
			this.currentMatrix = projection;
			break;
		}
	}
	
	public void translate(double x, double y, double z){
		this.currentMatrix.translate(x, y, z);
	}
	
	public void rotate(double orientation, double x, double y, double z){
		this.currentMatrix.rotate(orientation, x, y, z);
	}
	
	public void loadIdentityMatrix(){
		this.currentMatrix.loadIdentityMatrix();
	}
	
	public void gluPerspective(double fov, double aspect, double near, double far){
		this.currentMatrix.gluPerspective(fov, aspect, near, far);
	}
	
	public Matrix3D getPipeline(){
		Matrix3D pipeline = new Matrix3D();
		pipeline.setMatrix(Matrix3D.multiplyMatrix(this.projection.getMatrix(), this.modelView.getMatrix()));
		return pipeline;
	}
	
	public Matrix3D getModelViewMatrix(){
		return (Matrix3D)this.modelView;
	}
	
	public Matrix3D getProjectionMatrix(){
		return (Matrix3D)this.projection;
	}
}
