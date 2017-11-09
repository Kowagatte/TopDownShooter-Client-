package ca.damocles.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import ca.damocles.level.Level;
import ca.damocles.packet.Packet;
import ca.damocles.packet.Packet.PacketEnum;

public class ServerConnection extends Thread{

	protected Socket socket;
	private InputStream inputStream;
	private BufferedReader buffReader;
	private PrintStream outputStream;
	private Level level;
	
	public void setLevel(Level level) {
		this.level = level;
	}

	public ServerConnection(Socket clientSocket) {
        this.socket = clientSocket;
        
        try {
            inputStream = socket.getInputStream();
            buffReader = new BufferedReader(new InputStreamReader(inputStream));
            outputStream = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        
        start();
        
    }
    
    public void closeConnection() {
    	try {
    		socket.close();
    		inputStream.close();
    		outputStream.close();
    		buffReader.close();
    		interrupt();
    	}catch(IOException e) {
    		interrupt();
    	}
    }
    
    public void send(Packet packet) {
    	outputStream.println(packet.sendPacket());
    }
    
    public void run() {
    	Packet packet = null;
    	while(socket.isConnected()) {
    		try {
    			if(buffReader.ready()) {
            			String line = buffReader.readLine();
            			System.out.println(line);
                			packet = new Packet(line).formPacket();
                			
                			if(packet.getEnum() == PacketEnum.RENDER_PACKET) {
                				//ClientHandler.getInstance().getClient().window.repaint();
                			}
                			
                			if( (packet.getEnum() == PacketEnum.DENIED_PACKET) || (packet.getEnum() == PacketEnum.CLOSE_PACKET) ) {
                				closeConnection();
                				System.exit(0);
                			}
    				
    			}
    		}catch(IOException e) { interrupt(); }
    	}
    }
	
}

