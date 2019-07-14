package _02_Pixel_Art;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class PixelArtMaker implements MouseListener, MouseMotionListener, ActionListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	JButton save = new JButton("Save");
	static boolean red = false;
	GridPanel loadedData = null;
	

	public void start() {
	
		try {
			Scanner scanner = new Scanner(new FileReader("src/_02_Pixel_Art/save.dat"));
			if(scanner.hasNext()) {
			red = true;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		if(red == true) {
		loadedData = load();
		submitGridData(loadedData.getWindowWidth(), loadedData.getWindowHeight(), loadedData.getRows(), loadedData.getCols());
		
		}else {
		gip = new GridInputPanel(this);
		window.add(gip);
	   }
		window.pack();
	    
		
	}

	public void submitGridData(int w, int h, int r, int c) {
		
		gp = new GridPanel(w, h, r, c);
		
		csp = new ColorSelectionPanel();
		if(red == false) {
		window.remove(gip);
		}
		window.add(gp);
		window.add(csp);
		window.add(save);
		save.addActionListener(this);
		if(red == true) {
		loadedData = load();
		gp.pixels = loadedData.pixels;
		}
		
		gp.repaint();
		gp.addMouseListener(this);
		gp.addMouseMotionListener(this);
		window.pack();
		save(new GridPanel(w, h, r, c));
		
		
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(save)) {
			gp.saveData();
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
			Object gd = null;
			try{
			gd = ois.readObject();
			red = true;
			}catch(EOFException e) {
			red = false;
			}
			return (GridPanel) gd;
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
