import java.awt.BorderLayout;

import javax.swing.JFrame;

public class OctocatInvasion extends JFrame{

	public static Panel2 pan;
	public OctocatInvasion(){
		
		pan = new Panel2();
		add(pan);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Makes red X work (close window)
		setTitle("Octocat Invasion");
		setSize(1000, 600);					//Size of window

	}
	public static void main(String[] args) {
		new OctocatInvasion().setVisible(true);					//Makes it visible

	}

}
