package cs355.model.facade;

import java.io.File;
import java.util.ArrayList;
import java.util.Observer;

import cs355.model.scene.CS355Scene;
import cs355.model.scene.Instance;
import cs355.model.scene.Point3D;

public class SceneFacade {
	private static SceneFacade instance;
	private CS355Scene scene;
	
	private SceneFacade(){
		this.scene = new CS355Scene();
	}
	
	private static SceneFacade inst(){
		if(instance == null){
			instance = new SceneFacade();
		}
		return instance;
	}
	
	private boolean _open(File f){
		return this.scene.open(f);
	}
	
	public static boolean open(File f){
		return inst()._open(f);
	}
	
	private ArrayList<Instance> _instances(){
		return this.scene.instances();
	}
	
	public static ArrayList<Instance> instances(){
		return inst()._instances();
	}
	
	private Point3D _getCameraPosition() {
		return this.scene.getCameraPosition();
	}
	
	public static Point3D getCameraPosition() {
		return inst()._getCameraPosition();
	}
	
	private void _setCameraPosition(Point3D pos){
		this.scene.setCameraPosition(pos);
	}
	
	public static void setCameraPosition(Point3D pos) {
		inst()._setCameraPosition(pos);
	}
	
	private double _getCameraRotation() {
		return this.scene.getCameraRotation();
	}
		
	public static double getCameraRotation() {
		return inst()._getCameraRotation();
	}
	
	private void _setCameraRotation(double rot){
		this.scene.setCameraRotation(rot);
	}
	
	public static void setCameraRotation(double rot){
		inst()._setCameraRotation(rot);
	}
	
	private void _addObserver(Observer observer){
		this.scene.addObserver(observer);
	}
	
	public static void addObserver(Observer observer){
		inst()._addObserver(observer);
	}
}
