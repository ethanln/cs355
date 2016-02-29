package cs355.controller;

import cs355.scene.Point3D;

public class ControllerState {
	private Point3D camPos;
	private double camRot;
	
	private float width;
	private float height;
	
	public ControllerState(Point3D _camPos, double _camRot, float _width, float _height){
		this.camPos = _camPos;
		this.camRot = _camRot;
		this.width = _width;
		this.height = _height;
	}
	
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
