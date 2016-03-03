package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

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
	public LeaderBoard(JFrame window){
		this.window=window;
		mainMenu = new JButton("Main Menu");
		this.add(mainMenu);
		try {
			FileInputStream fileStream = new FileInputStream("res/HighScores.ser");
			ObjectInputStream os = new ObjectInputStream(fileStream);
			Object one = os.readObject();
			highScores=(HighScores)one;
			os.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			highScores= new HighScores();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window.getContentPane().add(this);
		}
		
}
