package map;

import java.awt.Graphics;

import camera.Camera;

import sprite.Sprite;

import files.InputFile;
import files.OutputFile;

public class TileMap {
	private String path;
	private TileObject tiles[];
	private TileManager tilemanager;
	private int width;
	private int height;
	
	public TileMap(int width, int height, TileManager tilemanager) {
		this.width = width;
		this.height = height;
		this.tilemanager = tilemanager;
		
		tiles = new TileObject[width*height];
		for(int i=0; i<tiles.length; i++) {
			tiles[i] = new TileObject();
			tiles[i].setId(-1);
			tiles[i].w = tilemanager.getTileW();
			tiles[i].h = tilemanager.getTileH();
			tiles[i].x = i%width*tilemanager.getTileW();
			tiles[i].y = i/width*tilemanager.getTileH();
		}
	}
	
	/**
	 * Constructor which loads the map using
	 * the parameter for the path
	 * @param path to map file
	 */
	public TileMap(String path) {
		load(path);
	}
	
	/**
	 * Loads a map
	 * @param path to map file
	 */
	public void load(String path) {
		tilemanager = null;
		
		tiles = new TileObject[width*height];
		for(int i=0; i<tiles.length; i++) {
			tiles[i] = new TileObject();
			tiles[i].setId(-1);
		}
		
		this.path = path;
		InputFile file = new InputFile(path);
		
		String input[] = null;
		
		while(file.ready()) {
			input = file.readTokens(" ");
			
			if(input != null) {
				if(input[0].equals("0")) {
					if(input.length > 2) {
						width = Integer.parseInt(input[1]);
						height = Integer.parseInt(input[2]);
					}
					else {
						System.out.println("Could not load map - width/height not defined.");
					}
				}
				
				if(input[0].equals("1")) {
					if(input.length > 2) {
						tilemanager = new TileManager(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
					}
					else {
						System.out.println("Could not load map - tile width/height not defined.");
					}
				}
				
				if(input[0].equals("2")) {
					if(input.length > 1) {
						if(tilemanager != null) {
							tilemanager.addSheet(new Sprite(input[1]));
						}
					}
					else {
						System.out.println("Could not load tiles.");
					}
				}
				
				if(input[0].equals("3")) {
					if(input.length > 2) {
						if(tilemanager != null) {
							int tileid = Integer.parseInt(input[3]);
							int x = Integer.parseInt(input[1]);
							int y = Integer.parseInt(input[2]);
							tiles[x+y*width].x = x*tilemanager.getTileW();
							tiles[x+y*width].y = y*tilemanager.getTileH();
							tiles[x+y*width].setId(tileid);
							tiles[x+y*width].w = tilemanager.getTileW();
							tiles[x+y*width].h = tilemanager.getTileH();
						}
					}
					else {
						System.out.println("Could not load tiles.");
					}
				}
			}
		}
		
		file.close();
	}
	
	public boolean save() {
		if(path!=null) {
			save(path);
			return true;
		}
		return false;
	}
	
	public void save(String path) {
		this.path = path;
		OutputFile file = new OutputFile(path);
		
		if(file != null) {
			file.writeln(0x00 + " " + width + " " + height);
			file.writeln(0x01 + " " + tilemanager.getTileW() + " " + tilemanager.getTileH());
			
			String imagepath = "";
			String previousimagepath = "";
			
			if(tilemanager!=null) {
				for(int i=0; i<tilemanager.paths(); i++) {
					imagepath = tilemanager.getPath(i);
					if(!imagepath.equals(previousimagepath)) {
						previousimagepath = imagepath;
						file.writeln(0x02 + " " + imagepath);
					}
				}
			}
			
			for(int i=0; i<tiles.length; i++) {
				if(tiles[i].getId()!=-1) {
					file.write(0x03);
					file.write(" ");
					int x = (tiles[i].x/tilemanager.getTileW());
					int y = (tiles[i].y/tilemanager.getTileH());
					file.write(x + " " + y);
					file.write(" ");
					file.writeln(tiles[i].getId());
				}
			}
			
			file.close();
		}
	}
	
	/**
	 * Set tile
	 * @param x-position in array
	 * @param y-position in array
	 * @param tile
	 */
	public boolean set(int x, int y, TileObject tile) {
		if(x+y*width<tiles.length && x+y*width>=0) {
			if(x<width && y<height && x>=0 && y>=0) {
				tiles[x+y*width] = tile;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Set tile on screen
	 * @param x-position on screen
	 * @param y-position on screen
	 * @param tile
	 */
	public boolean setTile(TileObject tile) {
		for(TileObject t: tiles) {
			if(t.x==tile.x && t.y==tile.y) {
				t = tile;
				return true;
			}
		}
		return false;
	}
	
	public TileObject get(int x, int y) {
		if(x+y*width<tiles.length && x+y*width>0) {
			return tiles[x+y*width];
		}
		return null;
	}
	
	public TileObject getTileObject(int x, int y) {
		for(TileObject t: tiles) {
			if(t.x==x && t.y==y) {
				return tiles[x+y*width];
			}
		}
		return null;
	}
	
	public void draw(Graphics g) {
		for(TileObject t: tiles) {
			if(t.getId()!=-1) {
				tilemanager.getTile(t.getId()).x = t.x;
				tilemanager.getTile(t.getId()).y = t.y;
				tilemanager.getTile(t.getId()).show(g);
			}
		}
	}
	
	public void draw(Graphics g, Camera c) {
		for(TileObject t: tiles) {
			if(t.getId()!=-1) {
				tilemanager.getTile(t.getId()).x = t.x + c.getX();
				tilemanager.getTile(t.getId()).y = t.y + c.getY();
				tilemanager.getTile(t.getId()).show(g);
			}
		}
	}
}
