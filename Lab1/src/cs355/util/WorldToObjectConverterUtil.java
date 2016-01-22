package cs355.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.dto.ConvertWorldToObjDto;

public class WorldToObjectConverterUtil implements IUtil{

	@Override
	public Object doUtil(Object o) {
		try{
			ConvertWorldToObjDto dto = (ConvertWorldToObjDto) o;
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

}
