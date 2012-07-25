package map;

public class TileObject {
	public int x;
	public int y;
	public int w;
	public int h;
	private int id;
	
	public TileObject() {
		x = 0;
		y = 0;
		id = 0;
	}
	
	public TileObject(int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
