package adventure;

import java.util.ArrayList;

/**
 * 
 * @author Henry
 *
 * Simple inventory class
 */
public class Inventory {
	private ArrayList<Item> items;
	
	public Inventory() {
		items = new ArrayList<Item>();
	}
	
	public boolean addItem(int id) {
		for(Item i: items) {
			if(i.getID() == id) {
				return false;
			}
		}
		items.add(new Item(id));
		return true;
	}
	
	public boolean addItem(Item item) {
		for(Item i: items) {
			if(i.getID() == item.getID()) {
				return false;
			}
		}
		items.add(item);
		return true;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public boolean contains(int id) {
		for(Item i: items) {
			if(i.getID() == id) {
				return true;
			}
		}
		return false;
	}
}
