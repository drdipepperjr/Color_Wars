package framework;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainMenu extends JPanel{
	JButton play;
	JButton leaderBoard;
	JFrame window;
	public MainMenu(JFrame window){
		this.window=window;
		play = new JButton("Play Color Wars");
		this.add(play);

	
		leaderBoard = new JButton("LeaderBoard");
		this.add(leaderBoard);
	
		window.getContentPane().add(this);
	}
	
}
