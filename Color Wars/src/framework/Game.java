package framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Game implements Runnable {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
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
		window.setSize(WIDTH,HEIGHT);
		window.setLocationRelativeTo(null); //centers window on middle of screen
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		//initial set up stuff
		this.displayMainMenu();
		
		window.setVisible(true);
		//start();
	}
	
	//Creates and displays the main menu
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
	
	public void playGame(){
		window.getContentPane().remove(mainMenu);
		PlayMenu= new Map();
		window.setVisible(false);
		window.getContentPane().add(PlayMenu);
		window.addMouseListener(new shootListener());
		window.setVisible(true);
		//the setVisible toggling makes it refresh the window
		
		start();
	}
	
	private synchronized void start(){
		//start() is the method called to start run loop
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
	
	@Override
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
	

	//listens for play button
	class playListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			playGame();
			System.err.println("play pressed");
		}
	}
	
	//listens for leaderBoard button
	class leaderBoardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.err.println("LeaderBoard pressed");
			//game.displayLeaderBoard();
			//doesn't do anything yet
		}
	}	
	
	//listens for click
	public class shootListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			double x=e.getX();
			double y=e.getY();
			System.err.println("click coord " +x +", "+y);
			PlayMenu.playerShoot(x,y);
		}
		
		
	}

}
