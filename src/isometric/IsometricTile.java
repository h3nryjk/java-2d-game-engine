package isometric;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import sprite.Sprite;

public class IsometricTile {
	public static final int MODE_COLOR = 0;
	public static final int MODE_IMAGE = 1;
	public static final int MODE_TEXTURE = 2;
	
	private Polygon p;
	
	private Color color;
	private Color gridColor;
	
	private int mode;
	
	private boolean grid;
	
	private Sprite image;
	private Sprite texture;
	
	private TexturePaint texturepaint;
	
	private int x;
	private int y;
	private int z;
	
	public IsometricTile() {
		grid = false;
		mode = MODE_COLOR;
		color = new Color(0, 0, 0);
		gridColor = new Color(255, 255, 255);
		p = new Polygon(new int[]{x+0, x+32, x+64, x+32}, new int[]{y+16-z, y+0-z, y+16-z, y+32-z}, 4);
	}
	
	public void setPosition(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		p.xpoints = new int[]{x+0, x+32, x+64, x+32};
		p.ypoints = new int[]{y+16-z, y+0-z, y+16-z, y+32-z};
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}
	
	public void setGrid(boolean enable) {
		grid = enable;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public void setImage(Sprite image) {
		this.image = image;
	}
	
	public void setTexture(Sprite texture) {
		this.texture = texture;
		Rectangle r = new Rectangle(0, 0, 64, 32);
		texturepaint = new TexturePaint(texture.getImage(), r);
	}
	
	public void draw(Graphics g) {
		switch(mode) {
			case MODE_COLOR: 
				g.setColor(color);
				g.fillPolygon(p);
				break;
			case MODE_IMAGE: 
				image.x = x;
				image.y = y;
				image.show(g);
				break;
			case MODE_TEXTURE:
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(texturepaint);
				g2d.fillPolygon(p);
				break;
		}
		if(grid) {
			g.setColor(gridColor);
			g.drawPolygon(p);
		}
	}
	
	public boolean onTile(int x, int y) {
		return p.contains(x, y);
	}
}
