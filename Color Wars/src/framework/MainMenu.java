package framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * The main menu screen
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel{
	CustomButton play;
	CustomButton leaderBoard;
	CustomButton instructions;
	JFrame window;
	ImageIcon img;
	public boolean started = false;
	
	/*
	 * Constructor for mainMenu
	 * 
	 * @param window the game window
	 */
	public MainMenu(JFrame window){
		this.window = window;
		img = new ImageIcon("res/MainMenu.jpg");
		
		this.setLayout(null);
		
		play = new CustomButton();
		play.setBounds(510,480,178,42);
		add(play);
		
		leaderBoard = new CustomButton();
		leaderBoard.setBounds(510,576,395,42);
		add(leaderBoard);
		
		instructions = new CustomButton();
		instructions.setBounds(510,673,389,56);
		add(instructions);

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
