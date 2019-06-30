package _02_Pixel_Art;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GridData implements Serializable{
Pixel[][] pix;

private int windowWidth;
private int windowHeight;
private int pixelWidth;
private int pixelHeight;
private int rows;
private int cols;

void GridData(int windowWidth, int windowHeight, int pixelWidth, int pixelHeight, int rows, int cols, Pixel[][] pix) {

this.windowWidth = windowWidth;
this.windowHeight = windowHeight;
this.pixelWidth = pixelWidth;
this.pixelHeight = pixelHeight;
this.rows = rows;
this.cols = cols;
this.pix = pix;	

}

private static void save(GridData data) {
	try (FileOutputStream fos = new FileOutputStream(new File("src/_02_Pixel_Art/save.dat"), true); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(data);	
	} catch (IOException e) {
		e.printStackTrace();
	}
}

private static GridData load() {
	try (FileInputStream fis = new FileInputStream(new File("src/_02_Pixel_Art/save.dat")); ObjectInputStream ois = new ObjectInputStream(fis)) {
		return (GridData) ois.readObject();
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	} catch (ClassNotFoundException e) {
		// This can occur if the object we read from the file is not
		// an instance of any recognized class
		e.printStackTrace();
		return null;
	}
}

}
