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
			PlayMenu= new Map();
			window.setVisible(false);
			window.getContentPane().add(PlayMenu);
			window.setVisible(true);
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
		long lastTime= System.nanoTime();
		final double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		int updates=0;
		int frames=0;
		long timer = System.currentTimeMillis();
		
		while (isRunning){
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime=now;
			if(delta>=1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis()-timer > 1000){
				timer += 1000;
				System.err.println(updates + " ticks, fps " +frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	private void render() {
		//for graphic rendering 
	}
	private void tick() {
		//for other stuff like movement 
		// and stuff we want to go slower
	}
	

	

}
