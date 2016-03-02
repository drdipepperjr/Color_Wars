package framework;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * A class that holds the high scores of past players and allows us to add new ones
 */
@SuppressWarnings("serial")
public class LeaderBoard extends JPanel{
	JButton mainMenu;
	JButton leaderBoard;
	JFrame window;
	public LeaderBoard(JFrame window){
		this.window=window;
		mainMenu = new JButton("Main Menu");
		this.add(mainMenu);
		
		window.getContentPane().add(this);
		}
		
}
