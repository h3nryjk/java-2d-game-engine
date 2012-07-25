package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import camera.Camera;

public class EntityManager {
	private ArrayList<Entity> entities;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public Entity get(int index) {
		return entities.get(index);
	}
	
	public void draw(Graphics g, Camera camera) {
		for(Entity e: entities) {
			e.draw(g, camera);
		}
	}
	
	public void handle() {
		for(Entity e: entities) {
			e.handle();
		}
	}
	
	public int size() {
		return entities.size();
	}
}
