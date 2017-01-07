import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Panel extends JPanel{

	public Timer t;
	BufferedImage img = null;
	public Panel(){
		
		t=new Timer(10, new TimerCallback());
		t.start();
		
	
		try {												//takes in image
		    img = ImageIO.read(new File("Octocat.png"));
		} catch (IOException e) {
		}
		
		
		
	}
	public class TimerCallback implements ActionListener {		//the actual timer effects
		public void actionPerformed (ActionEvent e){
			sdf+=1;
			repaint();				//calls paintComponent
		}
	}
	
	
	
	
	
	
	
	
	
	public int sdf=0;
	public void paintComponent(Graphics g){
		setBackground(Color.WHITE);				//sets background to white so image background doesn't show
		g.drawOval(getWidth()/2-20, getHeight()-10, 40, 20);
		g.drawImage(img, 10, sdf,null);		//draws image
	}
	
	
	
	
	
}
