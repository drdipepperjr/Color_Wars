package framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class Game implements Runnable {
	JFrame window;
	JPanel mainMenu;
	JPanel PlayMenu;
	JPanel LeaderBoard;
	private boolean isRunning= false;
	private Thread thread;
	
	private synchronized void start(){
		if (isRunning==true){
			return;
		}
		isRunning = true;
		thread= new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if (isRunning==false){
			return;
		}
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run(){
		while (isRunning){
			// game loop
			System.err.println("playing");
		}
		stop();
	}
	
	public static void main(String args[]){
		Game game=new Game();
		game.go();

	}
	private void go() {
		this.window = new JFrame();
		window.setTitle("Color Wars");	
		window.setSize(1024,768);
		window.setLocationRelativeTo(null); //centers window on middle of screen
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		this.displayMainMenu();
		
		window.setVisible(true);
		//start();
	}
	public void displayMainMenu(){
		mainMenu = new JPanel(); 
		JButton play = new JButton("Play Color Wars");
		mainMenu.add(play);
		play.addActionListener(new playListener());
	
		JButton leaderBoard = new JButton("LeaderBoard");
		mainMenu.add(leaderBoard);
		leaderBoard.addActionListener(new leaderBoardListener());
	
		this.window.getContentPane().add(mainMenu);
	}	
	
	
	class playListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.err.println("play pressed");
			window.getContentPane().remove(mainMenu);
			start();
		}
	}
	
	class leaderBoardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.err.println("LeaderBoard pressed");
			//game.displayLeaderBoard();
		}
	}
}
