package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import camera.Camera;

/**
 * 
 * Game class
 * When extended, it features a panel that includes it's own
 * double buffering and a SpriteList object that can be used
 * to handle many sprites by switching their visibility 
 * 
 * @author Henry
 *
 */

public abstract class Game extends Panel implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;

	protected ArrayList<Integer> keys = new ArrayList<Integer>();
	
	private Image buffer = null;
	private Graphics bufferG = null;
	
	protected Camera camera;
	
	/**
	 * Constructor, creates a new Panel including a
	 * SpriteList and certain Event Listeners
	 * 
	 * @param panel width
	 * @param panel height
	 */
	public Game(int width, int height) {
		setSize(width, height);
		camera = new Camera(0, 0, width, height);
		setLayout(null);
		
		addKeyListener(this);
    	addMouseListener(this);
    	addMouseMotionListener(this);
    	addMouseWheelListener(this);
	}
	
	/**
	 * This method should be used to draw
	 * on the game panel
	 * 
	 * @param graphics object
	 */
	public abstract void draw(Graphics g);
	
	public void start() {
    	Thread t = new Thread(this);
    	t.start();
    }

	public void paint(Graphics g) {
		draw(g);
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	/**
	 * Used for double buffering
	 */
	public void update(Graphics g) {    	
		if(buffer == null) {
        	buffer = this.createImage(this.getSize().width, this.getSize().height);
        	bufferG = buffer.getGraphics();
        }
        
        bufferG.setColor(this.getBackground());
        bufferG.fillRect(0, 0, super.getSize().width, super.getSize().height);
        
        bufferG.setColor(this.getForeground());   
        paint(bufferG);
        
        g.drawImage(buffer, 0, 0, this);
    }
	
	/**
	 * run method, used to run the main thread and call
	 * this loop method
	 */
	public void run() {
		while(true) {
			loop();
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		boolean exists = false;
		if(!keys.contains(arg0.getKeyCode())) {
			keys.add(arg0.getKeyCode());
		}
		
		for(int i: keys) {
			keyDown(i);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keyUp(arg0.getKeyCode());
		
		for(int i=0; i<keys.size(); i++) {
			if(keys.get(i) == arg0.getKeyCode()) {
				keys.remove(i);
			}
		}
		
	}
	
	/**
	 * Main game logic goes here
	 */
	public abstract void loop();
	
	/**
	 * Use this method to get key press input
	 * @param id of the pressed keys
	 */
	public abstract void keyDown(int keyID);
	
	/**
	 * Use this method to get the released keys
	 * @param id of the released keys
	 */
	public abstract void keyUp(int keyID);
	
	@Override
	public abstract void mouseWheelMoved(MouseWheelEvent arg0);

	@Override
	public abstract void mouseDragged(MouseEvent arg0);

	@Override
	public abstract void mouseMoved(MouseEvent arg0);

	@Override
	public abstract void mouseClicked(MouseEvent arg0);

	@Override
	public abstract void mouseEntered(MouseEvent arg0);

	@Override
	public abstract void mouseExited(MouseEvent arg0);

	@Override
	public abstract void mousePressed(MouseEvent arg0);

	@Override
	public abstract void mouseReleased(MouseEvent arg0);

	@Override
	public abstract void keyTyped(KeyEvent arg0);
}
