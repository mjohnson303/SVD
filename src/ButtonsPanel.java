import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Jama.Matrix;
import Jama.SingularValueDecomposition;



public class ButtonsPanel extends JPanel{
	JTextField path;
	JTextField rank;
	JLabel initialR, initialG, initialB;
	JLabel red, blue, green;
	JLabel red2, blue2, green2;
	JLabel final1, final2, final3;
	JButton calculate;
	JLabel path1, rank1;
	SVDPanel s;
	
	public ButtonsPanel(SVDPanel s1){
		s=s1;
		path1=new JLabel("Insert the name of picture: ");
		rank1=new JLabel("Insert rank you want to calculate: ");
		path=new JTextField();
		rank=new JTextField();
		initialR=new JLabel("SV1 of Red: ");
		initialG=new JLabel("SV1 of Green: ");
		initialB=new JLabel("SV1 of Blue: ");
		red2=new JLabel("SVK of Red: ");
		green2=new JLabel("SVK of Green: ");
		blue2=new JLabel("SVK of Blue: ");
		red=new JLabel("SVK+1 of Red: ");
		blue=new JLabel("SVK+1 of Green: ");
		green=new JLabel("SVK+1 of Blue: ");
		final1=new JLabel("SV1 of Image: ");
		final2=new JLabel("SVK of Image: ");
		final3=new JLabel("SVK+1 of Image: ");
		calculate = new JButton("Calculate");
		calculate.addActionListener(new CalcListener());
		
		setPreferredSize(new Dimension(200, 500));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//************************ first level
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx=0;
		c.gridy=0;
		add(path1,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 1;
		c.gridy =0;
		add(path, c);
		//*****************************
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx=0;
		c.gridy=1;
		add(rank1,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 1;
		c.gridy =1;
		add(rank, c);
		//************************
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		add(initialR, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 3;
		add(initialG, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 4;
		add(initialB, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 5;
		add(red2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 6;
		add(green2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 7;
		add(blue2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 8;
		add(red, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 9;
		add(green, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 10;
		add(blue, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 11;
		add(final1, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 12;
		add(final2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 13;
		add(final3, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 14;
		add(calculate, c);
		//***********************************
	}
	
	public class CalcListener implements ActionListener{
		public double sv1;
		public double svk1;
		public double svk;
		/**
		 * happens when you click the calculate button and it does all the calculations
		 */
		public void actionPerformed(ActionEvent e){
			BufferedImage img=null;
			String str=path.getText();
			int r=Integer.parseInt(rank.getText());
			try {
	        	img = ImageIO.read(new File(str)); // reads in the file
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Bad Input Path", "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
			 //*******************************************************
			Matrix[] x=readImage(img);//read the image
			Matrix[] k=new Matrix[3];
			k[0]=two(r,x[0]); //perform SVD decomposition on red matrix
			initialR.setText("SV1 of Red: "+sv1);
			red2.setText("SVK of Red: "+svk);
			red.setText("SVK+1 of Red: "+svk1);
			k[1]=two(r,x[1]); //perform SVD decomp on green matrix
			initialG.setText("SV1 of Green: "+sv1);
			green2.setText("SVK of Green: "+svk);
			green.setText("SVK+1 of Green: "+svk1);
			k[2]=two(r,x[2]); //perform SVD decomp on blue matrix
			initialB.setText("SV1 of Blue: "+sv1);
			blue2.setText("SVK of Blue: "+svk);
			blue.setText("SVK+1 of Blue: "+svk1);
			BufferedImage image = createImage(k);//create the image from the RGB matrices
			double[] finalSV = calcTotalSV(img, r);
			final1.setText("SV1 of Image: "+finalSV[0]);
			final2.setText("SVK of Image: "+finalSV[1]);
			final3.setText("SVK+1 of Image: "+finalSV[2]);
			//*************************************************************
			s.setImage(image);
			s.repaint();
			
		}
		/**
	     * reads the pixels from a BufferedImage
	     * @param bi the BufferedImage to be getting the pixels from
	     * @return an array of Matrices that has all of the Matrices with red, green, and blue pixels in them
	     */
		public Matrix[] readImage(BufferedImage bi){
			double[][] red = new double[bi.getHeight()][bi.getWidth()]; // create a 2D array to hold red pixels
			double[][] green = new double[bi.getHeight()][bi.getWidth()]; // create a 2D array to hold green pixels
			double[][] blue = new double[bi.getHeight()][bi.getWidth()]; // create a 2D array to hold blue pixels
			int p=0;
			for(int i=0; i<bi.getHeight(); i++){
				for(int j=0; j<bi.getWidth(); j++){
					p=bi.getRGB(j,i); // gets the ARGB pixel in each spot of the BufferedImage
					red[i][j]=(p >> 16) & 0x000000FF; //gets the red part of pixel
					green[i][j]=(p >>8 ) & 0x000000FF; //gets green part of pixel
					blue[i][j]=(p) & 0x000000FF; //gets blue part of pixel
				}
			}
			Matrix r=new Matrix(red);
			Matrix g=new Matrix(green);
			Matrix b=new Matrix(blue);
			Matrix[] total={r,g,b}; //returns an array of Matrices to hold Matrices with the red, green, and blue pixels
			return total;
		}
		
		/**
		 * creates an image from Matrices with the RGB pixels
		 * @param m array of Matrices with the RGB pixels in them
		 * @return the BufferedImage represented by the matrices
		 */
		public BufferedImage createImage(Matrix[] m){
			double[][] red=m[0].getArray();
			double[][] green=m[1].getArray();
			double[][] blue=m[2].getArray();
			int rows=red.length;
			int cols=red[0].length;
			double temp=0;
			int temp2[]=new int[rows*cols];
			int k=0;
			for(int i=0; i<rows; i++){//goes through all of the matrices and combines them into one integer per pixel
				for(int j=0; j<cols; j++){
					temp =new Color((int)check(red[i][j]), (int)check(green[i][j]), (int)check(blue[i][j])).getRGB();
					temp2[k]=(int)temp;
					k++;
				}

			}		
			BufferedImage img = new BufferedImage(cols,rows, BufferedImage.TYPE_INT_ARGB);
			img.setRGB(0, 0, cols, rows, temp2, 0, cols);
	        return img;
		}
		
		/**
		 * checks to make sure the RGB value is valid. Makes it less than 256 and non-negative
		 * @param temp2 the value of the pixel to be check
		 * @return a pixel that is 0-255
		 */
		public double check(double temp2){
			if(temp2>255)
				temp2=255;
			else if(temp2<0)
				temp2=0;
			return temp2;
		}
		
		/**
		 * takes as input an integer k, and an m× n matrix A, and calculates a^k, and the singular values at 1, and k+1
		 * @param k the rank we are trying to solve
		 * @param x Matrix that we are calculating a^k on
		 */
		public Matrix two(int k, Matrix x){
			SingularValueDecomposition svd=new SingularValueDecomposition(x);
			double[] values = svd.getSingularValues();
			Matrix V = svd.getU();
			Matrix U = svd.getV();
			Matrix Atemp=new Matrix(x.getRowDimension(), x.getColumnDimension()); 
			Matrix tempV=null;
			Matrix tempU=null;
			int vHeight=V.getRowDimension();
			int uHeight=U.getRowDimension();
			for(int j=0; j<k; j++){//calculate A^k
				tempV=V.getMatrix(0,vHeight-1,j,j);
				tempU=U.getMatrix(0, uHeight-1,j,j);
				Atemp.plusEquals((tempV.times(values[j])).times(tempU.transpose()));
			}     
			sv1=values[0];
			svk1=values[k];
			svk=values[k-1];
			return Atemp;
		}
		public double[] calcTotalSV(BufferedImage bi, int k){
			double[][] total = new double[bi.getHeight()][bi.getWidth()];
			int p=0;
			for(int i=0; i<bi.getHeight(); i++){
				for(int j=0; j<bi.getWidth(); j++){
					p=bi.getRGB(j,i); // gets the ARGB pixel in each spot of the BufferedImage
					total[i][j]=p;
				}
			}
			Matrix x = new Matrix(total);
			SingularValueDecomposition svd=new SingularValueDecomposition(x);
			double[] sv = svd.getSingularValues();
			double[] results = new double[3];
			results[0]=sv[0];
			results[1]=sv[k];
			results[2]=sv[k-1];
			return results;
		}
	}
}
