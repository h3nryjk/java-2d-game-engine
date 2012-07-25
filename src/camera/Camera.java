package camera;

import java.awt.Point;
import java.awt.Rectangle;

public class Camera {
	private Rectangle camera;
	
	public Camera(int width, int height) {
		camera = new Rectangle(0, 0, width, height);
	}
	
	public Camera(int x, int y, int width, int height) {
		camera = new Rectangle(x, y, width, height);
	}
	
	public void setPosition(int x, int y) {
		camera.x = x;
		camera.y = y;
	}
	
	public void move(int x, int y) { 
		camera.x += x;
		camera.y += y;
	}
	
	public int getX() {
		return camera.x;
	}
	
	public int getY() {
		return camera.y;
	}
	
	public boolean contains(int x, int y) {
		return camera.contains(new Point(x, y));
	}
	
	public boolean contains(int x, int y, int width, int height) {
		return camera.contains(new Rectangle(x, y, width, height));
	}
	
	public boolean contains(Rectangle r) {
		return camera.contains(r);
	}
	
	public boolean intersects(int x, int y, int width, int height) {
		return camera.intersects(new Rectangle(x, y, width, height));
	}
	
	public boolean intersects(Rectangle r) {
		return camera.intersects(r);
	}
}
