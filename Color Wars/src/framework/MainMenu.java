package framework;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel{
	JButton play;
	JButton leaderBoard;
	JFrame window;
	ImageIcon img;
	public boolean started = false;
	public MainMenu(JFrame window){
		this.window = window;
		img = new ImageIcon("res/MainMenu.png");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		add(Box.createVerticalStrut(350));
		add(Box.createHorizontalStrut(10));
		//add(new Box.Filler(200,200,200));
		
		play = new JButton("Play");
		add(play);
		
		add(Box.createVerticalStrut(50));
		leaderBoard = new JButton("LeaderBoard");
		leaderBoard.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(leaderBoard);
		add(Box.createHorizontalGlue());
		add(Box.createVerticalGlue());
		
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
