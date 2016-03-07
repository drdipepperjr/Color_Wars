package framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



/*
 * A class that holds the high scores of past players and allows us to add new ones
 */
@SuppressWarnings("serial")
public class LeaderBoard extends JPanel{
	CustomButton mainMenu;
	JFrame window;
	HighScores highScores;
	ImageIcon img = new ImageIcon("res/leaderboard.jpg");
	public LeaderBoard(JFrame window, HighScores highScores){
		this.window=window;
		this.setLayout(null);
		mainMenu = new CustomButton("Main Menu",317,34);
		mainMenu.setBounds(340,625,317,34);
		this.add(mainMenu);
		this.highScores=highScores;
		window.getContentPane().add(this);
		}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img.getImage(),0,0,window.getWidth(),window.getHeight(),this);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman",Font.ITALIC,28));
		g.drawString("Name", 365-5, 261-2);
		g.drawString("Score", 580-5, 261-2);
		g.setFont(new Font("TimesRoman",Font.PLAIN,20));
		for(int i=0;i<10;i++){
			g.drawString((i+1)+". "+highScores.getName(i), 350-5, 261+29*(i+1));
			g.drawString(""+ highScores.getScore(i), 580-5, 261+29*(i+1));
		}
		
	}
		
}
