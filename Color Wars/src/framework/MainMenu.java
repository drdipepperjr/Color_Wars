package framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel{
	CustomButton play;
	CustomButton leaderBoard;
	CustomButton instructions;
	JFrame window;
	ImageIcon img;
	public boolean started = false;
	public MainMenu(JFrame window){
		this.window = window;
		img = new ImageIcon("res/MainMenu.jpg");
		
		this.setLayout(null);
		
		
		//add(Box.createVerticalStrut(350));
		//add(Box.createHorizontalStrut(10));
		//add(new Box.Filler(200,200,200));
		
		play = new CustomButton("Play",178,42);
		play.setBounds(510,480,178,42);
		add(play);
		
		leaderBoard = new CustomButton("LeaderBoard",395,42);
		leaderBoard.setBounds(510,576,395,42);
		add(leaderBoard);
		
		instructions = new CustomButton("f",389,56);
		instructions.setBounds(510,673,389,56);
		add(instructions);
		//add(Box.createHorizontalGlue());
		//add(Box.createVerticalGlue());

		window.getContentPane().add(this);
		


	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(!started)
			g2d.drawImage(img.getImage(),0,0,window.getWidth(),window.getHeight(),this);
	}
}
