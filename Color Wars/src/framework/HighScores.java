package framework;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HighScores implements Serializable{
	String[] names = new String[10];
	int[] scores= new int[10];
	
	public HighScores(){
	}
	
	public void add(String name,int score){
		String curName=name;
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
