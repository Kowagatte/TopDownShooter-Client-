package ca.damocles.packet;

public class Packet {
	
	private PacketEnum packet = null;
	private String[] args = null;
	private String rawPacket = null;

	public Packet(PacketEnum packet, String[] args) {
		this.packet = packet;
		this.args = args;
	}
	
	public Packet(PacketEnum packet) {
		this.packet = packet;
	}
	
	public Packet(String rawPacket) {
		this.rawPacket = rawPacket;
	}
	
	public PacketEnum getEnum() {
		return packet;
	}
	
	public String[] getArgs() {
		return args;
	}
	
	public Packet formPacket() {
		String[] fragments = rawPacket.split(";");
		
		PacketEnum currentPacket = null;
		
		for(PacketEnum pack : PacketEnum.values()) {
			if(fragments[0].equals(pack.getPacketName())){
				currentPacket = pack;
				break;
			}
		}
		
		String[] newArray = new String[fragments.length-1];
		
		int i = 0;
		for(String s : fragments) {
			if(i > 0) {
				newArray[i-1] = s;
			}
			i++;
		}
		
		return new Packet(currentPacket, newArray);
	}
	
	public String sendPacket() {
		switch(packet) {
		case RENDER_PACKET:
			return packet.getPacketName()+";"+args[0]+";"+args[1]+";"+args[2];
		case PLAYER_MOVE_PACKET:
			return packet.getPacketName()+";"+args[0]+";"+args[1];
		case SCORE_CHANGE_PACKET:
			return packet.getPacketName()+";"+args[0]+";"+args[1];
		case TIME_CHANGE_PACKET:
			return packet.getPacketName()+";"+args[0];
		case CLOSE_PACKET:
			return packet.getPacketName()+";";
		case DENIED_PACKET:
			return packet.getPacketName()+";";
		case TICK_PACKET:
			return packet.getPacketName()+";";
		case COIN_VISABLE_PACKET:
			return packet.getPacketName()+";"+args[0]+";"+args[1];
		}
		return "";
	}
	
	public enum PacketEnum{
		RENDER_PACKET("RENDER", "RENDER;SPRITE;ID;LOCATION"),
		COIN_VISABLE_PACKET("COINVISABLE", "COINVISABLE;ID;BOOLEAN"),
		PLAYER_MOVE_PACKET("PLAYERMOVE", "PLAYERMOVE;ID;DIRECTION"),
		SCORE_CHANGE_PACKET("SCORECHANGE", "SCORECHANGE;ID;AMOUNT"),
		TIME_CHANGE_PACKET("TIMECHANGE", "TIMECHANGE;TIME"),
		CLOSE_PACKET("CLOSE", "CLOSE;"),
		DENIED_PACKET("DENIED", "DENIED;"),
		TICK_PACKET("TICK", "TICK;");
		String packetname;
		String structure;
		PacketEnum(String packetname, String structure){
			this.packetname = packetname;
			this.structure = structure;
		}
		public String getPacketName() { return this.packetname; }
		public String getPacketSctructure() { return this.structure; }
	}
	
}
