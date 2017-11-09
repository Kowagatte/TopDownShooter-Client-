package ca.damocles.utils;

import java.util.Random;

public class SplashText {
	
	private String text;
	private Random randomNumberGenerator = new Random();
	
	String[] splashTexts = {
			"Java Edition!",
			"Now with portals!",
			"I know, you know",
			"Tom you know what you did wrong",
			"I'm sorry Dave, I'm afraid I cant do that.",
			"Use the force"
	};
	
	public SplashText() {
		int randomNumber = randomNumberGenerator.nextInt(splashTexts.length);
		text = splashTexts[randomNumber];
	}
	
	public String get() {
		return text;
	}
	
}
