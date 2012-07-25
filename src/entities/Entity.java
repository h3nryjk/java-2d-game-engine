package entities;

import java.awt.Graphics;

import camera.Camera;

public abstract class Entity {
	public int x;
	public int y;
	protected static int id;
	
	public Entity() { 
		id++;
		x = 0;
		y = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public abstract void draw(Graphics g, Camera camera);
	public abstract void handle();
}
