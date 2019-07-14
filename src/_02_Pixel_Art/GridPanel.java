package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridPanel extends JPanel implements Serializable, ActionListener{

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;


	// 1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] pixels;

	private Color color;
	
	
	
	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;

		
		color = Color.BLACK;

		setPreferredSize(new Dimension(windowWidth, windowHeight));

		// 2. Initialize the pixel array using the rows and cols variables.
		pixels = new Pixel[rows][cols];

		// 3. Iterate through the array and initialize each element to a new pixel
		//Pixel savedPixels = load();
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				pixels[i][j] = new Pixel(i * pixelWidth, j * pixelHeight);
		//		pixels[i][j] = new Pixel(savedPixels.x, savedPixels.y);
			}
		}


	}
	
	public int getWindowHeight() {
		return windowHeight;
	}
	public int getWindowWidth() {
		return windowWidth;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	
	
	void saveData() {
	save(this);
	}

	public void setColor(Color c) {
		color = c;
	}

	public void clickPixel(int mouseX, int mouseY) {
		// 5. Use the mouseX and mouseY variables to change the color
		// of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		pixels[mouseX / pixelWidth][mouseY / pixelHeight].color = color;
	}

	@Override
	public void paintComponent(Graphics g) {
		// 4. Iterate through the array.
		// For every pixel in the list, fill in a rectangle using the pixel's color.
		// Then, use drawRect to add a grid pattern to your display.
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				g.setColor(pixels[i][j].color);
				g.fillRect(i * pixelWidth, j * pixelHeight, pixelWidth, pixelHeight);
				g.setColor(Color.BLACK);
				g.drawRect(i * pixelWidth, j * pixelHeight, pixelWidth, pixelHeight);
			}
		}

	}
	
	private static void save(GridPanel data) {
		try (FileOutputStream fos = new FileOutputStream(new File("src/_02_Pixel_Art/save.dat")); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static GridPanel load() {
		try (FileInputStream fis = new FileInputStream(new File("src/_02_Pixel_Art/save.dat")); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (GridPanel) ois.readObject();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
