package cs355.controller.state;
import java.awt.geom.Point2D.Double;

import cs355.model.drawing.Shape;
import cs355.model.scene.Point3D;

public class Controller3DState extends ControllerState{

	private Point3D camPos;
	private double camRot;
	
	private double width;
	private double height;
	
	private boolean isActive;
	
	public Controller3DState(Point3D _camPos, double _camRot, double _width, double _height){
		this.camPos = _camPos;
		this.camRot = _camRot;	
		
		this.width = _width;
		this.height = _height;
		
		this.setActive(false);
	}
	
	@Override
	public void editShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape makeShape(Double p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateShape(Shape shape, Double p) {
		// TODO Auto-generated method stub
		
	}

	// Camera Settings
	
	public Point3D getCamPos() {
		return camPos;
	}
	
	public void setCamPos(Point3D camPos) {
		this.camPos = camPos;
	}
	
	public double getCamRot() {
		return camRot;
	}
	
	public void setCamRot(double camRot) {
		this.camRot = this.camRot + camRot;
	}
	
	public void setCamPosX(double x){
		this.camPos.x = this.camPos.x + x;
	}
	
	public void setCamPosY(double y){
		this.camPos.y = this.camPos.y + y;
	}
	
	public void setCamPosZ(double z){
		this.camPos.z = this.camPos.z + z;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
