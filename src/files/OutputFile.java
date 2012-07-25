package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputFile {
	private PrintWriter printwriter;
	
	public OutputFile() {
		
	}
	
	public OutputFile(String path) {
		open(path);
	}
	
	public OutputFile(File file) {
		open(file);
	}
	
	public void open(String path) {
		try {
			printwriter = new PrintWriter(new FileWriter(path));
		} catch (IOException e) {
			System.out.println("File not found: " + path + ".");
		}
	}
	
	public void open(File file) {
		open(file.getAbsolutePath());
	}
	
	public void write(Object data) {
		if(printwriter!=null) {
			printwriter.print(data);
			printwriter.flush();
		}
	}
	
	public void write(byte data) {
		if(printwriter!=null) {
			printwriter.print(data);
			printwriter.flush();
		}
	}
	
	public void write(int data) {
		if(printwriter!=null) {
			printwriter.print(data);
			printwriter.flush();
		}
	}
	
	public void write(String data) {
		if(printwriter!=null) {
			printwriter.print(data);
			printwriter.flush();
		}
	}
	
	public void writeln(Object data) {
		if(printwriter!=null) {
			printwriter.println(data);
			printwriter.flush();
		}
	}
	
	public void writeln(byte data) {
		if(printwriter!=null) {
			printwriter.println(data);
			printwriter.flush();
		}
	}
	
	public void writeln(int data) {
		if(printwriter!=null) {
			printwriter.println(data);
			printwriter.flush();
		}
	}
	
	public void writeln(String data) {
		if(printwriter!=null) {
			printwriter.println(data);
			printwriter.flush();
		}
	}
	
	public void close() {
		printwriter.close();
	}
}
