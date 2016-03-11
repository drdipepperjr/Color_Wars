package framework;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class HighScores implements Serializable{
	String[] names; 
	int[] scores;
	
	public HighScores(){
		this.names=new String[10];
		this.scores = new int[10];
	}
	
	public void add(int score){
		// a jframe here isn't strictly necessary, but it makes the example a little more real
		JFrame frame = new JFrame("InputDialog Example #1");
		
		// prompt the user to enter their name
		String name = JOptionPane.showInputDialog(frame, "You got a High score!  What's your name?");
		
		// get the user's input. note that if they press Cancel, 'name' will be null
		System.out.printf("The user's name is '%s'.\n", name);
		add(score,name);
		save();
	}
	public void add(int score, String name){
		int curScore=score;
		String curName=name;
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
	}
	public String getName(int place){
		return names[place];
	}
	public int getScore(int place){
		return scores[place];
	}
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
