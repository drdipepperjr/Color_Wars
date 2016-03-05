package framework;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOver extends JPanel{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CustomButton replay;
	CustomButton mainmenu;
	CustomButton leaderboard;
	JFrame window;
	ImageIcon img;
	boolean started = false;
	public GameOver(JFrame window)
	{
		started = true;
		img = new ImageIcon("res/GameOver.png");
		
		this.setLayout(null);
		replay = new CustomButton("yolo",230,40);
		replay.setBounds(365, 540, 230, 40);
		add(replay);
		
		mainmenu = new CustomButton("yolo",320,40);
		mainmenu.setBounds(320, 613, 320, 40);
		add(mainmenu);
		
		leaderboard = new CustomButton("yolo",380,40);
		leaderboard.setBounds(294,687,380,40);
		add(leaderboard);
		
		this.window = window;
		window.getContentPane().add(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(started)
			g.drawImage(img.getImage(),0,0,window.getWidth(),window.getHeight(),this);
	}
}
