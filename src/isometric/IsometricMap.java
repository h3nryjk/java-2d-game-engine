package isometric;

import java.awt.Color;
import java.awt.Graphics;


public class IsometricMap {
	IsometricTile tiles[][];
	private int width;
	private int height;
	
	public IsometricMap(int width, int height) {
		tiles = new IsometricTile[width][height];
		this.width = width;
		this.height = height;
		
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				tiles[x][y] = new IsometricTile();
				tiles[x][y].setMode(IsometricTile.MODE_COLOR);
				tiles[x][y].setColor(Color.WHITE);
				if(y%2==0) {
					tiles[x][y].setPosition(x*64, y*16, 0);
				}
				else { 
					tiles[x][y].setPosition(x*64+32, y*16, 0);
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				tiles[x][y].draw(g);
			}
		}
	}
	
	public void setMode(int mode) {
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				tiles[x][y].setMode(mode);
			}
		}
	}
	
	public void setGrid(boolean grid) {
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				tiles[x][y].setGrid(grid);
			}
		}
	}
	
	public void setColor(Color color) {
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				tiles[x][y].setColor(color);
			}
		}
	}
	
	public void setGridColor(Color color) {
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				tiles[x][y].setGridColor(color);
			}
		}
	}
	
	public IsometricTile get(int x, int y) {
		try {
			return tiles[x][y];
		} catch(NullPointerException npe) {
			return null;
		}
	}
	
	public IsometricTile getTile(int xpos, int ypos) {
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				if(tiles[x][y].onTile(xpos, ypos)) {
					return tiles[x][y];
				}
			}
		}
		return null;
	}
}
