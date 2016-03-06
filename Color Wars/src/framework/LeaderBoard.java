package framework;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * A class that holds the high scores of past players and allows us to add new ones
 */
@SuppressWarnings("serial")
public class LeaderBoard extends JPanel{
	JButton mainMenu;
	JFrame window;
	HighScores highScores;
	public LeaderBoard(JFrame window, HighScores highScores){
		this.window=window;
		mainMenu = new JButton("Main Menu");
		this.add(mainMenu);
		this.highScores=highScores;
		window.getContentPane().add(this);
		}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0;i<10;i++){
			g.setColor(Color.black);
			g.drawString("name " + highScores.getName(i), 0, 25*(i+1));
			g.drawString("Score "+ highScores.getScore(i), 0, 25*(i+1)+10);
		}
		
	}
		
}
