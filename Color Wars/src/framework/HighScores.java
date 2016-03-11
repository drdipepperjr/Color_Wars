package framework;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * The High-Score list
 */
@SuppressWarnings("serial")
public class HighScores implements Serializable{
	String[] names; 
	int[] scores;
	
	/*
	 * Constructor for class HighScores
	 */
	public HighScores(){
		this.names=new String[10];
		this.scores = new int[10];
	}
	
	/*
	 * Add a score to the list, only if it is in the top 10 scores
	 * 
	 * @param score the score to be entered
	 */
	public void add(int score){
		// a jframe here isn't strictly necessary, but it makes the example a little more real
		JFrame frame = new JFrame("InputDialog Example #1");
		
		// prompt the user to enter their name
		String curName = JOptionPane.showInputDialog(frame, "You got a High score!  What's your name?");
		
		// get the user's input. note that if they press Cancel, 'name' will be null
		System.out.printf("The user's name is '%s'.\n", curName);
		int curScore=score;
		for (int i=0;i<10;i++){
			if (scores[i]<curScore){
				int tmpS=curScore;
				String tmpN=curName;
				curScore=scores[i];
				curName=names[i];
				scores[i]=tmpS;
				names[i]=tmpN;
			}
		}
		save();
	}
	
	/*
	 * gets the name at the specified index
	 * 
	 * @param place the index of the name
	 */
	public String getName(int place){
		return names[place];
	}
	
	/*
	 * gets the score at the specified index
	 * 
	 * @param place the index of the score
	 */
	public int getScore(int place){
		return scores[place];
	}
	
	/*
	 * Saves the high score list by writing it to a file
	 */
	public void save(){
		try{
			FileOutputStream fileStream=new FileOutputStream("res/HighScores.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(this);
			os.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
