package ca.damocles.level;

import java.util.ArrayList;
import java.util.List;
import ca.damocles.sprites.Coin;
import ca.damocles.sprites.Player;
import ca.damocles.sprites.Sprite;

public class Level{
	
	public List<Coin> coins;
	public Player greenPlayer;
	public Player purplePlayer;
	public Sprite greenSpawn;
	public Sprite purpleSpawn;
	public Sprite walls;
	
	public Level() {
		walls = new Sprite(32, 128);
	}

	public List<Sprite> getSprites() {
		List<Sprite> objects = new ArrayList<>();
		if(greenPlayer != null) 
			objects.add(greenPlayer);
		if(purplePlayer != null)
			objects.add(purplePlayer);
		if(greenSpawn != null) 
			objects.add(greenSpawn);
		if(purpleSpawn != null)
			objects.add(purpleSpawn);
		if(walls != null)
			objects.add(walls);
		if(coins != null) {
			for(Sprite sprite : coins) {
				objects.add(sprite);
			}
		}
		return objects;
	}
	
	
}
