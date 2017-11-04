package ca.damocles.utils;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	public static ImageUtil instance = new ImageUtil();
	
	public static ImageUtil getInstance() {
		return instance;
	}
	
	public ImageUtil() { }
	
	public Image getImage(String path, String name) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(path+"/"+name+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
