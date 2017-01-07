import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Panel2 extends JPanel {

	Random rand = new Random();
	//x and y represent the coordinates of the tip of the shooter.
	Timer turretTimer;
	Timer catTimer;
	protected int speed;
	public ArrayList<Point> bullets = new ArrayList<Point>();
	
	protected double shooterDiameter = 40;
	protected double shooterLength = 100;
	protected BufferedImage img = null;
	protected int catX=rand.nextInt(900)+50;
	protected int catY=0;
	protected int score=0;
	protected int lives=3;
	protected int catSpeed=35;
	
	
	private int angle=90;
	//public static final int DEFAULT_ANGLE = 90; //angles are represented as degrees to keep consistent with the input.
	
	//represents when the button is pressed, i.e. when to shoot

	
	
	
	public Panel2() {
		
		try {												//takes in image
		    img = ImageIO.read(new File("Octocat.png"));
		} catch (IOException e) {
		}
		
		speed = 200; //200 milliseconds is 1/5 of a second. 
		turretTimer = new Timer(10, new TimerCallback());
		catTimer = new Timer(catSpeed, new TimerCall());
		
		turretTimer.start();
		catTimer.start();
	}
	
	public class TimerCall implements ActionListener {		//the actual timer effects
		public void actionPerformed (ActionEvent e){
			catY+=5;
			repaint();
		}
	}
	
	public class TimerCallback implements ActionListener {		//the actual timer effects
		public void actionPerformed (ActionEvent e){

			repaint();
		}
	}
	
	boolean fire=false;
	boolean legoo=true;
	boolean game=true;
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.BOLD, 15));
		g.drawString("Score: "+score, 0, 25);
		g.drawString("Lives: "+lives, 0, 40);
		
		if(game){
		int width = getWidth();
		int height = getHeight();
		
		try{													//Reads in file
			Scanner scanner = new Scanner(new File("input.txt"));

				
				if (scanner.hasNext()) { //if is used to read just one token. if doesn't work, then use while.
					
					String numInFile = scanner.next();
					
					if (numInFile .contains("999") || numInFile.contains("888")) { //if the number in the txt file is 999 or 888
						fire= true;
					} else if(Integer.parseInt(numInFile)<=180) { //if the number in the txt file is not 999
						angle =Integer.parseInt(numInFile);
					}
				}
				
				scanner.close();

			
		}
		catch(FileNotFoundException e){								// In case file cannot be found
			System.err.println("Couldn't find file");
		}
		double angleRadians=Math.toRadians(angle);
		System.out.println(angle);
		
		int x = (int) ((width / 2) + (Math.cos(angleRadians) * shooterLength));
		int y = (int) (height - (Math.sin(angleRadians) * shooterLength));
		
		if (fire){
			bullets.add(new Point(x,y,slope(width/2,height,x,y), angle));
			fire=false;
		}
		
		try{
				g.fillOval((int)(bullets.get(0)).x, (int)(bullets.get(0)).y, 15,15);
		
				bullets.get(0).increment();
			if(Math.abs(catX-bullets.get(0).x) <49 && Math.abs(catY-bullets.get(0).y) <48){
				legoo=false;
				score++;
				if(score>5){
					catSpeed=20;
				}
				if(score>10){
					catSpeed=10;
				}
			}
			if(catY>600){
				legoo=false;
				lives--;
				score--;
				if(lives<=0){
					game=false;
					turretTimer.stop();
					catTimer.stop();
					repaint();
				}
			}
		}catch(Exception e){}
		
		if(legoo){
			g.drawImage(img, catX, catY,null);		//draws image
		}
		
		if(bullets.isEmpty()==false && legoo)
		if(bullets.get(0).x>width || bullets.get(0).x<0 ||bullets.get(0).y>height ||bullets.get(0).y<0){
			for(int i=0;i<bullets.size();i++){
				bullets.remove(i);
			}
		}
		
		if(legoo==false){
			catX=rand.nextInt(950)+50;
			catY=0;
			legoo=true;
		}

		
		System.out.println(x+" and "+ y);
		setBackground(Color.WHITE);				//sets background to white so image background doesn't show
		g.setColor(Color.BLACK);
		g.fillOval(getWidth()/2-20, getHeight()-10, 40, 20);
		g.drawLine(width/2, height, x, y);
		

		}
		else{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Verdana", Font.BOLD, 45));
			g.drawString("YOU LOSE... SCORE "+score, getWidth()/2-250, getHeight()/2);
		}
	}
	
	public static double slope(double x1, double y1, double x2, double y2){
		double slope = (y2-y1)/(x2-x1);
		return slope;
	}
}
