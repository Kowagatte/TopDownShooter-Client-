package ca.damocles.utils;

public class LocationUtil {
	
	public static String serialize(int x, int y) {
		return "(loc:"+x+":"+y+")";
	}
	
	public static int[] deserialize(String string) {
		String[] strings = string.split(":");
		String x = strings[1];
		String y = strings[2];
		
		int ix = Integer.valueOf(x);
		int iy = Integer.valueOf(y);
		
		return new int[] { ix, iy };
		
	}
	
}