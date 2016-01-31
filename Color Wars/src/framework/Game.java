package framework;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String args[]){
		
		//Window that holds all the JPanels
		JFrame window = new JFrame();
		window.setTitle("Color Wars");	
		window.setSize(1024,768);
		window.setLocationRelativeTo(null); //centers window on middle of screen
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		window.setVisible(true);
	
	}

}
