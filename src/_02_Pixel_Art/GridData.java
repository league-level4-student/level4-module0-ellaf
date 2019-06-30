package _02_Pixel_Art;

public class GridData {
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


}
