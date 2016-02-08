package cs355.model.drawing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model extends CS355Drawing{
	private List<Shape> shapes;
	
	public Model(){
		this.shapes = new ArrayList<Shape>();
	}
	
	@Override
	public Shape getShape(int index) {
		setChanged();
		return this.shapes.get(index);
	}

	@Override
	public int addShape(Shape s) {
		this.shapes.add(s);
		setChanged();
		return this.shapes.size() - 1;
	}

	@Override
	public void deleteShape(int index) {
		this.shapes.remove(index);	
		setChanged();
	}

	@Override
	public void moveToFront(int index) {
		Shape shape = this.shapes.get(index);
		this.shapes.add(shape);
		this.shapes.remove(index);
		setChanged();
	}

	@Override
	public void movetoBack(int index) {
		Shape shape = this.shapes.get(index);
		this.shapes.remove(index);
		this.shapes.add(0, shape);
		setChanged();
	}

	@Override
	public void moveForward(int index) {
		if(index < this.shapes.size() - 1){
			Shape currentShape = this.shapes.get(index);
			Shape nextShape = this.shapes.get(index + 1);
			this.shapes.set(index + 1, currentShape);
			this.shapes.set(index, nextShape);
			setChanged();
		}
	}

	@Override
	public void moveBackward(int index) {
		if(index > 0){
			Shape currentShape = this.shapes.get(index);
			Shape nextShape = this.shapes.get(index - 1);
			this.shapes.set(index - 1, currentShape);
			this.shapes.set(index, nextShape);
			setChanged();
		}
	}

	@Override
	public List<Shape> getShapes() {
		return this.shapes;
	}

	@Override
	public List<Shape> getShapesReversed() {
		ArrayList<Shape> reversedList = new ArrayList<Shape>();
		for(int i = this.shapes.size() - 1; i >= 0; i--){
			reversedList.add(this.shapes.get(i));
		}
		return reversedList;
	}

	@Override
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
		setChanged();
	}
	
	@Override
	public boolean open(File f) {
		boolean result = super.open(f);
		setChanged();
		return result;
	}

}
