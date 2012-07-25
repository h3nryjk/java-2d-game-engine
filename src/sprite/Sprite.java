package sprite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Sprite class used for displaying and clipping
 * images
 * 
 * @author Henry
 *
 */
public class Sprite {
	private BufferedImage image;
	
	public int x;
	public int y;
	private boolean visible;
	private int Xclip;
	private int Yclip;
	private int clipW;
	private int clipH;
	private String path;
	
	/**
	 * Constructor that does not take any
	 * arguments
	 */
	public Sprite() {
		x = 0;
		y = 0;
		image = null;
		setVisible(true);
		Xclip = 0;
		Yclip = 0;
		clipW = 0;
		clipH = 0;
	}
	
	/**
	 * Constructor that takes a String which contains the (relative
	 * or absolute) path to an image
	 * 
	 * @param Path to the image that should be loaded
	 */
	public Sprite(String path) {
		load(path);
		setVisible(true);
	}
	
	public Sprite(BufferedImage image) {
		setImage(image);
		setVisible(true);
	}
	
	/**
	 * Loads an image by a given path
	 * 
	 * @param Path to the image that should be loaded
	 */
	public void load(String path) {
		this.path = path;
		image = null;
		try {
		    image = ImageIO.read(new File(path));
		    clipW = image.getWidth();
		    clipH = image.getHeight();
		} catch (IOException e) {
			
		}
		if(image == null) {
			System.out.println("File not found.");
		}
	}
	
	/**
	 * Clips the sprite
	 * 
	 * @param Width of every clip
	 * @param Height of every clip
	 */
	public void clip(int clipW, int clipH) {
		this.clipW = clipW;
		this.clipH = clipH;
	}
	
	/**
	 * Set the clip that should be displayed
	 * If you have an image that is 64 x 64 pixels
	 * big and each clip is 16 x 16 pixels, the
	 * first clip is Xclip=0 and Yclip=0, the
	 * last clip is Xclip=1 and Yclip=1
	 * 
	 * @param X Position of the clip
	 * @param Y Position of the clip
	 */
	public void setClip(int Xclip, int Yclip) {
		if(Xclip<image.getWidth()/clipW) {
			this.Xclip = Xclip;
		}
		if(Yclip<image.getHeight()/clipH) {
			this.Yclip = Yclip;
		}
	}
	
	/**
	 * 
	 * @param Graphics object that is used to draw stuff
	 * @param clip X position
	 * @param clip Y position
	 * @param width of the clips
	 * @param height of the clips
	 */
	public void show(Graphics g, int Xclip, int Yclip, int clipW, int clipH) {
		if(image!=null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(image.getSubimage(Xclip*clipW, Yclip*clipH, clipW, clipH), null, x, y);
		}
	}
	
	/**
	 * 
	 * @param Graphics object
	 */
	public void show(Graphics g) {
		if(image!=null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(image.getSubimage(Xclip*clipW, Yclip*clipH, clipW, clipH), null, x, y);
		}
	}

	/**
	 * 
	 * @return image width
	 */
	public int getW() {
		if(image!=null) {
			return image.getWidth();
		}
		return 0;
	}
	
	/**
	 * 
	 * @return image height
	 */
	public int getH() {
		if(image!=null) {
			return image.getHeight();
		}
		return 0;
	}
	
	public int getXClips() {
		return image.getWidth()/clipW;
	}
	
	public int getYClips() {
		return image.getHeight()/clipH;
	}
	
	/**
	 * 
	 * @param second Sprite
	 * @return true if both sprites collide, false if not
	 */
	public boolean collides(Sprite s) {
		if(this.x>s.x && this.x<s.x+s.getW()) {
			if(this.y>s.y && this.y<s.y+s.getH()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return Rectangle which is as big as the current clip
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, clipW, clipH);
	}
	
	/**
	 * 
	 * @return visibility
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * 
	 * @param visibility
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * 
	 * @return image path
	 */
	public String getImgPath() {
		return path;
	}
	
	/**
	 * 
	 * @return current image clip as sprite
	 */
	public Sprite getSubSprite() {
		Sprite s = new Sprite(this.getImage());
		return s;
	}
	
	/**
	 * 
	 * @return current image clip
	 */
	public BufferedImage getImage() {
		return image.getSubimage(Xclip*clipW, Yclip*clipH, clipW, clipH);
	}
	
	/**
	 * 
	 * @return full image
	 */
	public BufferedImage getFullImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		clipW = image.getWidth();
	    clipH = image.getHeight();
	}
}
