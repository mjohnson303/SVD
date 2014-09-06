import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
 /**
  * Matt Johnson
  */
public class SVDPanel extends JPanel {
    BufferedImage image;
    Dimension size = new Dimension();
    static double sv1=0;
	static double svk1=0;
	static double svk=0;
    static Matrix ak= null;
 
    /**
     * creates a SVD2 Object
     * @param image the image that we will be doing operations on
     */
    public SVDPanel(BufferedImage image) {
    	setBackground(Color.white);
        this.image = image;
        size.setSize(200,400);
    }
 
    /**
     * paints the image into a JPanel
     * @param g a Graphics object
     */
    protected void paintComponent(Graphics g) {
        // Center image in this component.
        int x = (getWidth() - size.width)/2;
        int y = (getHeight() - size.height)/2;
        g.setColor(Color.white);
        g.fillRect(0,0,600,800);
        g.drawImage(image, x, y, this);
    }
 
    /**
     * gets the size of the JPanel
     */
    public Dimension getPreferredSize() { return size; }
 
    public void setImage(BufferedImage img){
    	image=img;
    }
    
	//TESTER METHODS//--------------------------------------------------------------------------------------------
	/**
	 * reads and image into a single 2D array value
	 * @param bi a BufferedImage to translate into pixels
	 * @return a 2D array of a single ARGB pixel value
	 */
	public static double[][] readImageOrig(BufferedImage bi){
		int[] pixels = new int[bi.getWidth()*bi.getHeight()];
		bi.getRGB(0, 0, bi.getWidth(), bi.getHeight(), pixels, 0, bi.getHeight()); //Get all pixels
		double[][] x = new double[bi.getHeight()][bi.getWidth()];
		for(int i=0; i<bi.getHeight(); i++){
			for(int j=0; j<bi.getWidth(); j++)
				x[i][j]=pixels[bi.getHeight()*i + j];
		}
		return x;
	}
	
	/**
	 * creates an image from a 2D array
	 * @param x a 2D array of ARGB pixel values
	 * @return a BufferedImage
	 */
	public static BufferedImage createImage(double[][] x){
		int rows=x.length;
		int cols=x[0].length;	
		BufferedImage img = new BufferedImage(cols,rows, BufferedImage.TYPE_INT_ARGB);
		int[] temp2=new int[rows*cols];
		int i=0;
		for(int k=0; k<rows; k++){
			for(int m=0; m<cols; m++){
				temp2[i]=(int)x[k][m];
				i++;
			}
		}
		img.setRGB(0, 0, cols, rows, temp2, 0, cols);
        return img;
	}
}