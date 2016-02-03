package cs355.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.dto.ConvertViewToWorldDto;
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
	
	private Point2D.Double _convertWorldToObject(ConvertWorldToObjDto dto) {
		try{
			Point2D.Double objCoor = new Point2D.Double();
			AffineTransform worldToObj = new AffineTransform();
			
			if(dto.rotation != 0.0){
				worldToObj.rotate(-dto.rotation);
			}
			
			worldToObj.translate(-dto.center.getX(), -dto.center.getY());
			worldToObj.transform(dto.pointToConvert, objCoor);
			
			return objCoor;
		}
		catch(Exception e){
			
		}

		return null;
	}
	
	public static Point2D.Double convertWorldToObject(ConvertWorldToObjDto dto){
		return inst()._convertWorldToObject(dto);
	}
	
	private Point2D.Double _convertViewToWorld(ConvertViewToWorldDto dto){
		//AffineTransform viewToWorld = new AffineTransform();
		return null;
	}
	
	public static Point2D.Double convertViewToWorld(ConvertViewToWorldDto dto){
		return inst()._convertViewToWorld(dto);
	}
}
