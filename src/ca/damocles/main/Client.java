package ca.damocles.main;

import java.awt.Graphics;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.damocles.level.Level;
import ca.damocles.packet.Packet;
import ca.damocles.packet.Packet.PacketEnum;
import ca.damocles.sprites.Sprite;
import ca.damocles.utils.ImageUtil;
import ca.damocles.utils.SplashText;

public class Client extends JPanel implements Runnable{
	
	/* SERIAL VERSION UID */
	private static final long serialVersionUID = 3550596793369075558L;
	private final int WIDTH = 928, HEIGHT = 800;
	private InputHandler inputHandler;
	public ServerConnection connection;
	public boolean connected = false;
	public Thread thread;
	public Level level;
	
	public Client(String toolBarMessage) {
		
		JFrame frame = new JFrame(toolBarMessage);
		frame.setIconImage(ImageUtil.getInstance().getImage("", "Icon"));
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	stop();
            }
        });
		frame.getContentPane().add(this);
		frame.setVisible(true);
        
		inputHandler = new InputHandler();

		addKeyListener(inputHandler);
		addFocusListener(inputHandler);
		addMouseListener(inputHandler);
		addMouseMotionListener(inputHandler);
		
		try {
			Socket clientSocket = new Socket( /* "50.93.4.185" */ "localhost", 8888);
			if(clientSocket.isConnected()) {
				level = new Level();
				connection = new ServerConnection(clientSocket);
				start();
			}
		}catch (IOException e) { e.printStackTrace(); }
	}
	
	public Level getLevel() {
		return level;
	}
	
	public synchronized void start() {
		if (connected) return;
		connected = true;
		connection.setLevel(level);
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if(!connected) return;
		connected = false;
		connection.send(new Packet(PacketEnum.CLOSE_PACKET));
		try {
			thread.interrupt();
		}catch(SecurityException e) {e.printStackTrace();}
		 connection.closeConnection();
		 System.exit(0);
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double secondsPerTicks = 1000000000 / 60;
		double delta = 0;
		while(connected) {
			long now = System.nanoTime();
			delta += (now - lastTime) / secondsPerTicks;
			lastTime = now;
			if(delta >= 1) {
				repaint();
				delta--;
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageUtil.getInstance().getImage("", "BG"), 0, 0, null);
		for(Sprite sprite : level.getSprites()) {
			g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), null);
		}
	}
	
	public static void main(String[] args) {
		new Client("TopDownShooter: " + new SplashText().get());
	}
	
	
}
