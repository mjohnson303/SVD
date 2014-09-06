import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import Jama.Matrix;


public class ProjectMain {
	public static void main(String[] args) throws IOException {
		BufferedImage img=null;
		try {
        	img = ImageIO.read(new File("Hello.jpg")); // reads in the file
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Cannot read Hello.jpg", "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		SVDPanel svd=new SVDPanel(img);
		ButtonsPanel bp=new ButtonsPanel(svd);
		JFrame f = new JFrame("SVD Decomposition on Images");//frame with the image in it
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(0,2);
        f.setLayout(layout);
        f.add(svd);
        f.add(bp);
        f.setSize(1000,700);
        f.setLocation(0,0);
        f.setVisible(true);
    }
}
