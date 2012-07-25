package adventure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/**
 * 
 * @author Henry
 *
 * Hotspot class, can be used for point 'n' click
 * adventure games; nearly the same as Polygon
 */
public class Hotspot {
	private Polygon area;
	
	public Hotspot() {
		area = new Polygon();
	}
	
	public Hotspot(Polygon area) {
		this.area = area;
	}
	
	public Hotspot(int xpoints[], int ypoints[], int num) {
		area = new Polygon(xpoints, ypoints, num);
	}
	
	public boolean contains(int x, int y) {
		return area.contains(x, y);
	}
	
	public boolean contains(Point p) {
		return area.contains(p);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(255, 0, 0, 125));
		g.drawPolygon(area);
	}
}
