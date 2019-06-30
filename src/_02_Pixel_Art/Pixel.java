package _02_Pixel_Art;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Pixel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public Color color;
	
	public Pixel(int x, int y) {
		this.x = x;
		this.y = y;
		color = Color.WHITE;
	}
	
	private static void save(Pixel data) {
		try (FileOutputStream fos = new FileOutputStream(new File("src/_02_Pixel_Art/save.dat")); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Pixel load() {
		try (FileInputStream fis = new FileInputStream(new File("src/_02_Pixel_Art/save.dat")); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (Pixel) ois.readObject();
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
