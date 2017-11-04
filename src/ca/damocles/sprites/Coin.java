package ca.damocles.sprites;

public class Coin extends Sprite{

	private int id;
	private Player owner;
	
	public Coin(int id, int x, int y) {
		super(x, y);
		this.id = id;
		this.owner = null;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public void setOwner(Player player) {
		this.owner = player;
	}
	
	public int getID() {
		return this.id;
	}
	
}
