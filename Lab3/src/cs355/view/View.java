package cs355.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cs355.GUIFunctions;
import cs355.controller.Controller;
import cs355.definitions.MatrixMode;
import cs355.definitions.ShapeType;
import cs355.drawable.shape.DrawableShape;
import cs355.drawable.shape.factory.DrawableShapeFactory;
import cs355.dto.ConvertObjToScreenDto;
import cs355.dto.ConvertShapeToDrawableShapeDto;
import cs355.matrix.Graphics3D;
import cs355.matrix.Matrix3D;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;
import cs355.model.facade.ModelFacade;
import cs355.model.facade.SceneFacade;
import cs355.model.image.CS355Image;
import cs355.model.image.ImageModel;
import cs355.model.scene.Instance;
import cs355.model.scene.Line3D;
import cs355.model.scene.Point3D;
import cs355.util.CoordinateConverterUtil;
import cs355.util.HandleUtil;

public class View implements ViewRefresher, Observer{

	private Controller controller;
	public View(Controller controller){
		ModelFacade.addObserver(this);
		SceneFacade.addObserver(this);
		this.controller = controller;
	}
	
	@Override
	public void refreshView(Graphics2D g2d) {
		//displayImage
		CS355Image image = this.controller.getImage();
		if(this.controller.isImageView()){
			BufferedImage imgBf = image.getImage();
			float x = 1024.0f - (image.getWidth() / 2.0f);
			float y = 1024.0f - (image.getHeight() / 2.0f);
			AffineTransform op = CoordinateConverterUtil.convertObjToScreen(
					  new ConvertObjToScreenDto(new Point2D.Double(x, y), 
							  					new Point2D.Double(this.controller.getScreenOrigin().x, 
							  										this.controller.getScreenOrigin().y), 
							  										0.0, this.controller.getFactor()));
			g2d.drawImage(imgBf, op, null);
		}
		
		
		ArrayList<Shape> shapes = (ArrayList)ModelFacade.getShapes();
		ArrayList<Shape> overlayShapes = new ArrayList<Shape>();
		
		// fetch selected shape from the controller
		int selectedShape = this.controller.getCurrentSelectedShape();
		
		// if there is a selected shape
		if(selectedShape > -1){

			if(shapes.get(selectedShape).getShapeType() == ShapeType.LINE){ // if the shape is a line
				
				Line line = (Line) shapes.get(selectedShape);
				
				// draw the handle overlays for the line shape
				Circle overlayHandleStart = new Circle(Color.RED, line.getCenter(), 5.0 / this.controller.getFactor());
				overlayHandleStart.setShapeType(ShapeType.CIRCLE);
				overlayHandleStart.setRotation(shapes.get(selectedShape).getRotation());
				
				// draw the handle overlays for the line shape
				Circle overlayHandleEnd = new Circle(Color.RED, line.getEnd(), 5.0 / this.controller.getFactor());
				overlayHandleEnd.setShapeType(ShapeType.CIRCLE);
				overlayHandleEnd.setRotation(shapes.get(selectedShape).getRotation());
				
				overlayShapes.add(overlayHandleStart);
				overlayShapes.add(overlayHandleEnd);
			}
			else{  // if it is any other shape
				
				// fetches the handle center of the shape
				Point2D.Double handleCenter = HandleUtil.getHandleCenter(shapes.get(selectedShape), this.controller.getFactor());
				// draw the handle and border overlay for other shapes
				Circle overlayHandle = new Circle(Color.RED, handleCenter, 5.0 / this.controller.getFactor());
				overlayHandle.setShapeType(ShapeType.CIRCLE);
				overlayHandle.setRotation(shapes.get(selectedShape).getRotation());
				
				overlayShapes.add(overlayHandle);
				overlayShapes.add(shapes.get(selectedShape));
			}
		}
		
		// draw model shapes
		for(int i = 0; i < shapes.size(); i++){
			DrawableShape drawing = DrawableShapeFactory.getDrawableShape(new ConvertShapeToDrawableShapeDto(shapes.get(i), this.controller.getScreenOrigin(), this.controller.getFactor()));
			drawing.draw(g2d, "fill");
		}
		
		// draw overlays
		for(int i = 0; i < overlayShapes.size(); i++){
			DrawableShape drawing = DrawableShapeFactory.getDrawableShape(new ConvertShapeToDrawableShapeDto(overlayShapes.get(i), this.controller.getScreenOrigin(), this.controller.getFactor()));
			drawing.draw(g2d, "border");
		}
		
		// draw 3d, if toggled on.
		if(this.controller.is3D()){
			this.draw3D(g2d);
		}
	}
	
	private void draw3D(Graphics2D g2d){
		// get graphics object
		Graphics3D graphics = this.controller.getGraphicsSetup();
		
		// get projection and model view matrices.
		Matrix3D projection = graphics.getProjectionMatrix();
		Matrix3D modelView = graphics.getModelViewMatrix();
		
		// get instances
		ArrayList<Instance> instances = SceneFacade.instances();
		for(int i = 0; i < instances.size(); i++){
			// get instance
			Instance instance = instances.get(i);
			
			// get instance lines
			ArrayList<Line3D> lines = (ArrayList)instance.getModel().getLines();
			
			double instanceAngle = instance.getRotAngle();
			Point3D instancePos = instance.getPosition();
			
			// transform instances for camera view
			graphics.mode(MatrixMode.MODELVIEW);
			graphics.loadIdentityMatrix();
			graphics.rotate(Math.toRadians(this.controller.getCamRot()), 0.0, 1.0, 0.0);
			graphics.translate(-this.controller.getCamPos().x, -this.controller.getCamPos().y, -this.controller.getCamPos().z);
			
			// transform instances in relation to camera position
			graphics.translate(instancePos.x, instancePos.y, instancePos.z);
			graphics.rotate(Math.toRadians(instanceAngle), 0.0, 1.0, 0.0);
			
			// for each line
			for(int j = 0; j < lines.size(); j++){
				Line3D line = lines.get(j);
				
				// get camera coordinates, convert 3d points in homogenous vectors
				double[] camera3Dend = Matrix3D.multiplyMatrixWithVector(modelView.getMatrix(), new double[]{line.end.x, line.end.y, line.end.z, 1.0});
				double[] camera3Dstart = Matrix3D.multiplyMatrixWithVector(modelView.getMatrix(), new double[]{line.start.x, line.start.y, line.start.z, 1.0});
				
				// get clip coordiantes
				double[] clip3Dend = Matrix3D.multiplyMatrixWithVector(projection.getMatrix(), camera3Dend);
				double[] clip3Dstart = Matrix3D.multiplyMatrixWithVector(projection.getMatrix(), camera3Dstart);
				
				// clip test
				boolean endPointTest = this.clipTest(clip3Dend);
				boolean startPointTest = this.clipTest(clip3Dstart);
				
				// if clip matrix passes
				if(endPointTest && startPointTest){
					// convert points into screen coor, normalize?
					Point2D end = this.convertToScreenCoor(clip3Dend);
					Point2D start = this.convertToScreenCoor(clip3Dstart);
					
					AffineTransform objToScreen = CoordinateConverterUtil.convertObjToScreen(
												  new ConvertObjToScreenDto(new Point2D.Double(0.0, 0.0), 
														  					new Point2D.Double(this.controller.getScreenOrigin().x, this.controller.getScreenOrigin().y), 0.0, this.controller.getFactor()));
					g2d.setTransform(objToScreen);
					g2d.setPaint(instance.getColor());
					Line2D.Double shapeLine = new Line2D.Double(end.getX(), end.getY(), start.getX(), start.getY());
					g2d.draw(shapeLine);
					g2d.fill(shapeLine);
				}
			}
		}
	}
	
	private boolean clipTest(double[] vector3D){
		
		double x = vector3D[0];
		double y = vector3D[1];
		double z = vector3D[2];
		double w = vector3D[3];
		
		if(x < -w
		   && x > w
		   && y < -w
		   && y > w
		   && z < -w + 5
		   && z > w - 5){
			return true;
		}
		return false;
	}
	
	private Point2D convertToScreenCoor(double[] vector3D){
		Point2D.Double point = new Point2D.Double();
		double[][] screenMatrix = new double[][]{{2048.0 / 2.0, 0.0,          2048.0 / 2.0},
												 {0.0,         2048.0 / 2.0,  2048.0 / 2.0},
												 {0.0,		   0.0,		     1.0}};
		double w = vector3D[3];
		vector3D[0] /= w;
		vector3D[1] /= w;
		vector3D[2] = 1.0;
		
		double[] newVector = new double[3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				newVector[i] += vector3D[j] * screenMatrix[i][j];
			}
		}
		
		point.x = newVector[0];
		point.y = newVector[1];
		
		return point;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		GUIFunctions.refresh();
	}
}
