package cs355.util;

public class UtilFactory{

	private static UtilFactory instance;

	private UtilFactory(){}
	
	private static UtilFactory inst(){
		if(instance == null){
			instance = new UtilFactory();
		}
		return instance;
	}
	
	private IUtil _makeUtil(String type){
		switch(type){
			case "world_to_object_converter":
				return new WorldToObjectConverterUtil();
			case "handle-center":
				return new HandleCenterUtil();
			case "point_in_handle":
				return new PointInHandleUtil();
			default:
				return null;
		}
	}
	
	public static IUtil makeUtil(String type){
		return inst()._makeUtil(type);
	}
}
