package framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Game implements Runnable {
	JFrame window;
	JPanel mainMenu;
	Map PlayMenu;
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
		//initial set up stuff
		this.displayMainMenu();
		
		window.setVisible(true);
		//start();
	}
	public void displayMainMenu(){
		//Creates and displays the main menu
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
//listens for play button
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.err.println("play pressed");
			window.getContentPane().remove(mainMenu);
			PlayMenu= new Map();
			window.setVisible(false);
			window.getContentPane().add(PlayMenu);
			window.setVisible(true);
			//the setVisible toggling makes it refresh the window
			start();
		}
	}
	
	class leaderBoardListener implements ActionListener{
//listens for leaderBoard button
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.err.println("LeaderBoard pressed");
			//game.displayLeaderBoard();
			//doesn't do anything yet
		}
	}
	
	private synchronized void start(){
		//start() is the methed called to start run loop
		if (isRunning==true){
			return;
		}
		isRunning = true;
		thread= new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		//stop() stops run loop
		if (isRunning==false){
			return;
		}
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run(){
		long lastTime= System.nanoTime();
		final double ticks = 60.0;//time between doing things 
		double ns = 1000000000 / ticks;
		double delta = 0;//used to find time differences 
		int updates=0;//debug variable 
		int frames=0;//debug variable 
		long timer = System.currentTimeMillis();//debug variable 
		
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
			//debug code so we can see whats going on.  
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
		PlayMenu.repaint();
	}
	private void tick() {
		//for other stuff like movement 
		// and stuff we want to go slower
		PlayMenu.update();
	}
	

	

}
