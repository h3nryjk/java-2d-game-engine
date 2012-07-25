package map;

import java.util.ArrayList;

import sprite.Sprite;

/**
 * class that is also a tile manager.
 * Good way to access all tile types without
 * passing parameters around
 * 
 * @author Henry
 */
public class TileManager {
	private ArrayList<Sprite> tiles;
	private ArrayList<String> paths;
	private int tileW = 32;
	private int tileH = 32;
	
	public TileManager(int tileW, int tileH) {
		tiles = new ArrayList<Sprite>();
		paths = new ArrayList<String>();
		this.tileW = tileW;
		this.tileH = tileH;
	}
	
	public String getPath(int index) {
		return paths.get(index);
	}
	
	public int paths() {
		return paths.size();
	}
	
	public void addSheet(Sprite sheet) {
		sheet.clip(tileW, tileH);
		paths.add(sheet.getImgPath());
		
		for(int x=0; x<sheet.getXClips(); x++) {
			for(int y=0; y<sheet.getYClips(); y++) {
				sheet.setClip(x, y);
				tiles.add(new Sprite(sheet.getImage()));
			}
		}
	}
	
	/**
	 * Add all clips of a sprite to the list
	 * @param clipped sprite, tilesheet
	 */
	public void addClippedSheet(Sprite sheet) {
		paths.add(sheet.getImgPath());
		
		for(int x=0; x<sheet.getXClips(); x++) {
			for(int y=0; y<sheet.getYClips(); y++) {
				sheet.setClip(x, y);
				tiles.add(new Sprite(sheet.getImage()));
			}
		}
	}
	
	/**
	 * Gets a tile image by instance
	 * @param tile index
	 * @return tile image
	 */
	public Sprite getTile(int index) {
		return tiles.get(index);
	}
	
	public int size() {
		return tiles.size();
	}
	
	public int getTileW() {
		return tileW;
	}
	
	public int getTileH() {
		return tileH;
	}
}
