package ca.damocles.level;

import java.util.ArrayList;
import java.util.List;

import ca.damocles.packet.Packet;
import ca.damocles.sprites.Coin;
import ca.damocles.sprites.Player;
import ca.damocles.sprites.Sprite;
import ca.damocles.utils.LocationUtil;

public class Level {

	public List<Coin> coins;
	public Player greenPlayer;
	public Player purplePlayer;
	public Sprite greenSpawn;
	public Sprite purpleSpawn;
	public Sprite walls;

	public Level() {
		walls = new Sprite(32, 160);
		walls.loadImage("", "walls");
	}
	
	public void render(Packet renderPacket) {
		String[] args = renderPacket.getArgs();
		int[] location = LocationUtil.deserialize(args[2]);
		switch(args[0]) {
		case "COIN":
			break;
		case "PURPLESPAWN":
			if(purpleSpawn != null) {
				purpleSpawn.setX(location[0]);
				purpleSpawn.setY(location[1]);
			}else {
				purpleSpawn = new Sprite(location[0], location[1]);
				purpleSpawn.loadImage("", "PurpleSpawn");
			}
			return;
		case "GREENSPAWN":
			if(greenSpawn != null) {
				greenSpawn.setX(location[0]);
				greenSpawn.setY(location[1]);
			}else {
				greenSpawn = new Sprite(location[0], location[1]);
				greenSpawn.loadImage("", "GreenSpawn");
			}
			return;
		}
		return;
	}

	public List<Sprite> getSprites() {
		List<Sprite> objects = new ArrayList<>();
		if(walls != null) 
			objects.add(walls);
		if (greenPlayer != null)
			objects.add(greenPlayer);
		if (purplePlayer != null)
			objects.add(purplePlayer);
		if (greenSpawn != null)
			objects.add(greenSpawn);
		if (purpleSpawn != null)
			objects.add(purpleSpawn);
		if (walls != null)
			objects.add(walls);
		if (coins != null) {
			for (Sprite sprite : coins) {
				objects.add(sprite);
			}
		}
		return objects;
	}

}
