package cs355.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.dto.ConvertObjToScreenDto;
import cs355.dto.ConvertObjToWorldDto;
import cs355.dto.ConvertScreenToObjDto;
import cs355.dto.ConvertScreenToWorldDto;
import cs355.dto.ConvertWorldToObjDto;

public class CoordinateConverterUtil {
	private static CoordinateConverterUtil instance;
	
	private CoordinateConverterUtil(){}
	
	private static CoordinateConverterUtil inst(){
		if(instance == null){
			instance = new CoordinateConverterUtil();
		}
		return instance;
	}
	
	/*
	 *  world to object
	 */
	private Point2D.Double _convertWorldToObject(ConvertWorldToObjDto dto) {
		try{
			// translation matrix
			AffineTransform translate = new AffineTransform(1.0, 0.0, 0.0, 1.0, -dto.center.getX(), -dto.center.getY());
			
			// rotation matrix
			AffineTransform rotate = new AffineTransform(Math.cos(-dto.rotation), Math.sin(-dto.rotation), 
														-Math.sin(-dto.rotation), Math.cos(-dto.rotation), 
														 0.0, 0.0);
			
			// multiply matrices
			rotate.concatenate(translate);
			
			// get current matrix
			double[] matrix = new double[6];
			rotate.getMatrix(matrix);
			
			// transform
			double objX = (matrix[0] * dto.pointToConvert.getX()) + (matrix[2] * dto.pointToConvert.getY()) + (matrix[4] * 1.0);
			double objY = (matrix[1] * dto.pointToConvert.getX()) + (matrix[3] * dto.pointToConvert.getY()) + (matrix[5] * 1.0);
			
			return new Point2D.Double(objX, objY);
		}
		catch(Exception e){
			
		}

		return null;
	}
	
	public static Point2D.Double convertWorldToObject(ConvertWorldToObjDto dto){
		return inst()._convertWorldToObject(dto);
	}
	
	/*
	 *  object to world
	 */
	private AffineTransform _convertObjectToWorld(ConvertObjToWorldDto dto){
		// translation matrix
		AffineTransform translate = new AffineTransform(1.0, 0.0, 0.0, 1.0, dto.center.getX(), dto.center.getY());
		
		// get rotation matrix
		AffineTransform rotate = new AffineTransform(Math.cos(dto.rotation), Math.sin(dto.rotation), 
													-Math.sin(dto.rotation), Math.cos(dto.rotation), 
													 0.0, 0.0);
		translate.concatenate(rotate);
		return translate;
	}
	
	public static AffineTransform convertObjectToWorld(ConvertObjToWorldDto dto){
		return inst()._convertObjectToWorld(dto);
	}
	
	/*
	 *  screen to object
	 */
	private Point2D.Double _convertScreenToObj(ConvertScreenToObjDto dto){
		// rotation matrix
		AffineTransform translate = new AffineTransform(1.0, 0.0, 0.0, 1.0, -dto.center.getX(), -dto.center.getY());
		AffineTransform obj = new AffineTransform(Math.cos(-dto.rotation), Math.sin(-dto.rotation), 
													-Math.sin(-dto.rotation), Math.cos(-dto.rotation), 
													 0.0, 0.0);
		obj.concatenate(translate);
		
		AffineTransform view = new AffineTransform(1.0, 0.0, 0.0, 1.0, dto.screenOrigin.getX(), dto.screenOrigin.getY());
		AffineTransform scale = new AffineTransform((1.0 / dto.factor), 0.0, 0.0, (1.0 / dto.factor), 0.0, 0.0);
		view.concatenate(scale);
		
		obj.concatenate(view);
		Point2D.Double objCoor = new Point2D.Double();
		obj.transform(dto.pt, objCoor);
		
		return objCoor;
	}
	
	public static Point2D.Double convertScreenToObj(ConvertScreenToObjDto dto){
		return inst()._convertScreenToObj(dto);
	}
	
	/*
	 *  object to screen
	 */
	private AffineTransform _convertObjToScreen(ConvertObjToScreenDto dto){
		AffineTransform objToWorld = this._convertObjectToWorld(new ConvertObjToWorldDto(dto.center, dto.rotation));
		AffineTransform objToScreen = new AffineTransform(dto.factor, 0.0, 0.0, dto.factor, 0.0, 0.0);
		AffineTransform translate = new AffineTransform(1.0, 0.0, 0.0, 1.0, -dto.screenOrigin.getX(), -dto.screenOrigin.getY());
		
		objToScreen.concatenate(translate);
		objToScreen.concatenate(objToWorld);
		return objToScreen;
	}
	
	public static AffineTransform convertObjToScreen(ConvertObjToScreenDto dto){
		return inst()._convertObjToScreen(dto);
	}
	
	private Point2D.Double _convertScreenToWorld(ConvertScreenToWorldDto dto){
		AffineTransform scale = new AffineTransform((1 / dto.factor), 0.0, 0.0, (1/dto.factor), 0.0, 0.0);
		AffineTransform translate = new AffineTransform(1.0, 0.0, 0.0, 1.0, dto.screenOrigin.getX(), dto.screenOrigin.getY());
		
		translate.concatenate(scale);

		Point2D.Double screenCoor = new Point2D.Double();
		translate.transform(dto.pt, screenCoor);
		return screenCoor;
	}
	
	public static Point2D.Double convertScreenToWorld(ConvertScreenToWorldDto dto){
		return inst()._convertScreenToWorld(dto);
	}
}
