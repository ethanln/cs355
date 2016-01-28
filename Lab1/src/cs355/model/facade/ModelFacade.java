package cs355.model.facade;

import java.io.File;
import java.util.List;
import java.util.Observer;

import cs355.model.drawing.CS355Drawing;
import cs355.model.drawing.Model;
import cs355.model.drawing.Shape;

public class ModelFacade{
	private static ModelFacade instance;
	private CS355Drawing model;
	
	private ModelFacade(){
		model = new Model();
	}
	
	private static ModelFacade inst(){
		if(instance == null){
			instance = new ModelFacade();
		}
		return instance;
	}
	

	private Shape _getShape(int index) {
		return this.model.getShape(index);
	}
	
	public static Shape getShape(int index){
		return inst()._getShape(index);
	}
	

	private int _addShape(Shape s) {
		if(s != null){
			int result = this.model.addShape(s);
			this.model.notifyObservers();
			return result;
		}
		return -1;
	}
	
	public static int addShape(Shape s){
		return inst()._addShape(s);
	}
	

	private void _deleteShape(int index) {
		this.model.deleteShape(index);
		this.model.notifyObservers();
	}
	
	public static void deleteShape(int index){
		inst()._deleteShape(index);
	}
	

	private int _moveToFront(int index) {
		this.model.moveToFront(index);
		return this.model.getShapes().size() - 1;
	}
	
	public static int moveToFront(int index){
		return inst()._moveToFront(index);
	}
	

	private int _movetoBack(int index) {
		this.model.movetoBack(index);
		return 0;
	}
	
	public static int moveToBack(int index){
		return inst()._movetoBack(index);
	}
	

	private int _moveForward(int index) {
		this.model.moveForward(index);
		return index < (this.model.getShapes().size() - 1) ? (index + 1) : index;
	}
	
	public static int moveForward(int index){
		return inst()._moveForward(index);
	}
	

	private int _moveBackward(int index) {
		this.model.moveBackward(index);
		return index > 0 ? (index - 1) : index;
	}
	
	public static int moveBackward(int index){
		return inst()._moveBackward(index);
	}
	

	private List<Shape> _getShapes() {
		return this.model.getShapes();
	}
	
	public static List<Shape> getShapes(){
		return inst()._getShapes();
	}
	

	private List<Shape> _getShapesReversed() {
		return this.model.getShapesReversed();
	}
	
	public static List<Shape> getShapesReversed(){
		return inst()._getShapesReversed();
	}
	

	private void _setShapes(List<Shape> shapes) {
		this.model.setShapes(shapes);
		this.model.notifyObservers();
	}
	
	public static void setShapes(List<Shape> shapes){
		inst()._setShapes(shapes);
	}
	
	private void _addObserver(Observer observer){
		this.model.addObserver(observer);
	}
	
	public static void addObserver(Observer observer){
		inst()._addObserver(observer);
	}
	
	private void _commitChange(){
		this.model.notifyObservers();
	}
	
	public static void commitChange(){
		inst()._commitChange();
	}
	
	private boolean _open(File f) {
		if(f != null){
			boolean result = this.model.open(f);
			this.model.notifyObservers();
			return result;
		}
		return false;
	}
	
	public static boolean open(File f){
		return inst()._open(f);
	}
	
	private boolean _save(File f) {
		if(f != null){
			return this.model.save(f);
		}
		return false;
	}
	
	public static boolean save(File f){
		return inst()._save(f);
	}

}
