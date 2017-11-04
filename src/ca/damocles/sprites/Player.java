package ca.damocles.sprites;

import ca.damocles.packet.Packet;
import ca.damocles.packet.Packet.PacketEnum;

public class Player extends Sprite{
	
	public Movements direction;

	public Player(int x, int y, Movements direction) {
		super(x, y);
		this.direction = direction;
	}
	
	public Packet move(Movements move) {
		direction = move;
		return new Packet(PacketEnum.PLAYER_MOVE_PACKET, new String[] { move.getInt()+"" });
	}
	
	public Movements getDirection() {
		return direction;
	}
	
	public void fire() {
		//TODO
	}

	public enum Movements{
		UP(0, "Up"),
		UP_LEFT(1, "UpLeft"),
		UP_RIGHT(2, "UpRight"),
		LEFT(3, "Left"),
		RIGHT(4, "Right"),
		DOWN(5, "Down"),
		DOWN_LEFT(6, "DownLeft"),
		DOWN_RIGHT(7, "DownRight");
		private int i;
		private String name;
		Movements(int i, String name){
			this.i = i;
			this.name = name;
		}
		public int getInt() {
			return this.i;
		}
		public String getName() {
			return this.name;
		}
	}
	

}
