package ca.damocles.main;

import java.awt.Graphics;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import ca.damocles.level.Level;
import ca.damocles.packet.Packet;
import ca.damocles.packet.Packet.PacketEnum;
import ca.damocles.utils.ImageUtil;
import ca.damocles.utils.SplashText;

public class Client extends JFrame implements Runnable{
	
	/**
	 * SERIALVERSIONUID
	 */
	private static final long serialVersionUID = 3550596793369075558L;
	private final int WIDTH = 928;
	private final int HEIGHT = 736;
	private InputHandler inputHandler;
	public boolean close = false;
	public ServerConnection connection;
	public boolean connected = false;
	public Thread thread;
	public Level level;
	
	public Client(String toolBarMessage) {
		
		setIconImage(ImageUtil.getInstance().getImage("", "Icon"));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	stop();
            }
        });
        setVisible(true);
        
		inputHandler = new InputHandler();

		addKeyListener(inputHandler);
		addFocusListener(inputHandler);
		addMouseListener(inputHandler);
		addMouseMotionListener(inputHandler);
		
		try {
			//50.93.4.185
			Socket clientSocket = new Socket("localhost", 8888);
			if(clientSocket.isConnected()) {
				level = new Level();
				connection = new ServerConnection(clientSocket);
				start();
			}
		}catch (IOException e) { System.out.println(e); }
	}
	
	public Level getLevel() {
		return level;
	}
	
	public synchronized void start() {
		if (connected) return;
		connected = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if(!connected) return;
		connected = false;
		connection.send(new Packet(PacketEnum.CLOSE_PACKET));
		try {
			thread.interrupt();
		}catch(SecurityException e) {
			e.printStackTrace();
		}
		 connection.closeConnection();
		 System.exit(0);
	}

	@Override
	public void run() {
		while(!connected) {

		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(level.sprite.getBG(), 0, 0, null);
	}
	
	public static void main(String[] args) {
		new Client("TopDownShooter: " + new SplashText().get());
	}
	
	
}
