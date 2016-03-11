package framework;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * The Game-Over screen
 */
@SuppressWarnings("serial")
public class GameOver extends JPanel{

	protected CustomButton replay;
	protected CustomButton mainmenu;
	protected CustomButton leaderboard;
	private JFrame window;
	private ImageIcon img;
	private boolean started = false;
	
	/*
	 * Constructor for the GameOver Screen
	 * 
	 * @param window the game window
	 */
	public GameOver(JFrame window)
	{
		started = true;
		img = new ImageIcon("res/GameOver.png");
		
		this.setLayout(null);
		replay = new CustomButton();
		replay.setBounds(365, 540, 230, 40);
		add(replay);
		
		mainmenu = new CustomButton();
		mainmenu.setBounds(320, 613, 320, 40);
		add(mainmenu);
		
		leaderboard = new CustomButton();
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
