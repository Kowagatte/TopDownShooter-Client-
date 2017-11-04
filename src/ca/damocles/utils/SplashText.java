package ca.damocles.utils;

import java.util.Random;

public class SplashText {
	
	private String text;
	private Random randomNumberGenerator = new Random();
	
	String[] splashTexts = {
			"Java Edition!",
			"Now with portals!"
			};
	
	public SplashText() {
		int randomNumber = randomNumberGenerator.nextInt(splashTexts.length);
		text = splashTexts[randomNumber];
	}
	
	public String get() {
		return text;
	}
	
}
