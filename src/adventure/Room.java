package adventure;

import java.awt.Graphics;
import java.util.ArrayList;

import sprite.Sprite;

/**
 * 
 * @author Henry
 *
 * Simple room class for point 'n' click adventure
 * games
 */
public class Room {
	private Sprite background;
	private ArrayList<Hotspot> hotspots;
	
	public Room() {
		
	}

	public Room(Sprite background) {
		this.background = background;
	}
	
	public void addHotspot(Hotspot hotspot) {
		hotspots.add(hotspot);
	}
	
	public Hotspot getHotspot(int id) {
		return hotspots.get(id);
	}
	
	public ArrayList<Hotspot> getHotspots() {
		return hotspots;
	}
	
	public void draw(Graphics g) {
		background.show(g);
	}
}
