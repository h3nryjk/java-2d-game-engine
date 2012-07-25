package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputFile {
	private BufferedReader reader;
	private String path;
	
	public InputFile() {
		
	}
	
	public InputFile(File file) {
		open(file);
	}
	
	public InputFile(String path) {
		open(path);
	}
	
	public void open(File file) {
		open(file.getAbsolutePath());
	}
	
	public void open(String path) {
		try {
			this.path = path;
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + path + ".");
		}
	}
	
	public int read() {
		if(reader!=null) {
			try {
				return reader.read();
			} catch (IOException e) {
				System.out.println("Could not read from file: " + path + ".");
			}
		}
		return -1;
	}
	
	public String readln() {
		if(reader!=null) {
			try {
				return reader.readLine();
			} catch (IOException e) {
				System.out.println("Could not read from file: " + path + ".");
			}
		}
		return null;
	}
	
	public String[] readTokens(String regex) {
		if(reader!=null) {
			try {
				String s = reader.readLine();
				return s.split(regex);
			} catch (IOException e) {
				System.out.println("Could not read from file: " + path + ".");
			}
		}
		return null;
	}
	
	public boolean ready() {
		try {
			return reader.ready();
		} catch (IOException e) {
			System.out.println("Could not get file status: " + path + ".");
		}
		return false;
	}
	
	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("Could not close file: " + path + ".");
		}
	}
}
