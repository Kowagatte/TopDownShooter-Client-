package ca.damocles.level;

import java.awt.Graphics;
import java.util.List;

import ca.damocles.graphics.Window;
import ca.damocles.sprites.Coin;
import ca.damocles.sprites.Player;
import ca.damocles.sprites.Player.Movements;
import ca.damocles.sprites.Sprite;
import ca.damocles.sprites.SpriteHandler;
import ca.damocles.utils.ImageUtil;

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
	
	
}
