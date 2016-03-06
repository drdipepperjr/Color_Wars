package framework;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel{
	CustomButton play;
	CustomButton leaderBoard;
	JFrame window;
	ImageIcon img;
	public boolean started = false;
	public MainMenu(JFrame window){
		this.window = window;
		img = new ImageIcon("res/MainMenu.png");
		
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
		//add(Box.createHorizontalGlue());
		//add(Box.createVerticalGlue());

		window.getContentPane().add(this);
		


	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(!started)
			g.drawImage(img.getImage(),0,0,window.getWidth(),window.getHeight(),this);
	}
}
