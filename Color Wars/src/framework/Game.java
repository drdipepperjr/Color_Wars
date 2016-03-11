package framework;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;

/*
 * A class that initializes the game window and creates the game loop
 * upon which the whole game is based. 
 */
public class Game implements Runnable {
	
	/*
	 * The width of the screen
	 */
	public static final int WIDTH = 1024;
	
	/*
	 * The height of the screen
	 */
	public static final int HEIGHT = 768;
	
	/*
	 * the x-coordinate of the mouse
	 */
	public static double mouseX;
	
	/*
	 * The y-coordinate of the mouse
	 */
	public static double mouseY;

	private Sound soundPlayer;
	private JFrame window;
	private MainMenu mainMenu;
	private Map PlayMenu;
	private GameOver gameover;
	private HighScores highScores;
	private LeaderBoard leaderBoard;
	private Instructions instr;

	/*
	 * variable that turns the debug Environment on and off
	 */
	private static boolean DebugEnvironment =false;

	private boolean isRunning= false;
	private Thread thread;
	

	/*
	 * Creates the Game object on which everything depends on and calls it's go method
	 */
	public static void main(String args[]){
		if(args.length!=0 && args[0]== "Debug")
			DebugEnvironment=true;
		Game game=new Game();
		game.go();
	}
	
	/*
	 * Creates the window and displays the main menu on the window
	 */
	private void go() {
		this.window = new JFrame();
		
		
		window.setTitle("Color Wars");	
		window.setSize(WIDTH,HEIGHT);
		window.setLocationRelativeTo(null); //centers window on middle of screen
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		//initial set up stuff
		this.displayMainMenu();
		
		//plays horribile music
		 soundPlayer = new Sound();
		 soundPlayer.setClip("res/UpbeatFunk.wav");
		 soundPlayer.loop();
		
		 //loads highscores
		 try {
				FileInputStream fileStream = new FileInputStream("res/HighScores.ser");
				ObjectInputStream os = new ObjectInputStream(fileStream);
				Object one = os.readObject();
				highScores=(HighScores)one;
				os.close();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				highScores= new HighScores();
			} catch (IOException e) {
				e.printStackTrace();
			}
		window.setVisible(true);
	}
	
	/*
	 * Displays the main menu and creates buttons to access various parts of the game.
	 */
	public void displayMainMenu(){
		if (Game.DebugEnvironment)
			System.err.println("displaying main menu");
		window.getContentPane().removeAll();
		mainMenu = new MainMenu(window); 
		mainMenu.play.addActionListener(new playListener());
		mainMenu.leaderBoard.addActionListener(new LeaderBoardListener());
		mainMenu.instructions.addActionListener(new instructionsListener());
		window.setVisible(true);
	}	
	
	/*
	 * Displays the leader board 
	 */
	public void displayLeaderBoard(){
		if (Game.DebugEnvironment)
			System.err.println("displaying leader Board");
		window.getContentPane().removeAll();
		leaderBoard = new LeaderBoard(window,highScores); 
		leaderBoard.mainMenu.addActionListener(new MainMenuListener());
		window.setVisible(true);
	}
	
	/*
	 * Displays the instructions screen
	 */
	public void displayInstructions(){
		if (Game.DebugEnvironment)
			System.err.println("displaying instructions");
		window.getContentPane().removeAll();
		instr = new Instructions(window); 
		instr.mainMenu.addActionListener(new MainMenuListener());
		window.setVisible(true);
	}
	
	/*
	 * Displays the Game Over screen
	 * Occurs when the player dies
	 */
	public void displayGameOver(){
		if (Game.DebugEnvironment)
			System.err.println("displaying gameOver");
		highScores.add(Map.score);
		window.getContentPane().removeAll();
		gameover = new GameOver(window);
		gameover.mainmenu.addActionListener(new MainMenuListener());
		gameover.leaderboard.addActionListener(new LeaderBoardListener());
		gameover.replay.addActionListener(new playListener());
		window.setVisible(true);
		
	}
	
	/*
	 * Self-explanatory. Starts the gameplay aspect of the game.
	 */
	public void playGame(){
		if (Game.DebugEnvironment)
			System.err.println("playing Game");
		soundPlayer.stop();
		soundPlayer.setClip("res/MoodyLoop.wav");
		soundPlayer.loop();
		 
		window.getContentPane().removeAll();
		PlayMenu= new Map(window);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("res/cursor.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(16,16), "img");
		
		window.addMouseMotionListener(new MouseMotionListener() {
		    @Override
		    public void mouseMoved(MouseEvent e) {
		        final int x = e.getX();
		        final int y = e.getY();
		    //displays the cursor if inside the window
		        if (x>0 && y >0 && y<Game.HEIGHT && x < Game.WIDTH) {
		            window.setCursor(c);
		        } else {
		            window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		        }
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {
		    }
		});
		window.getContentPane().add(PlayMenu);
		window.addMouseMotionListener(new shootListener());
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
	}
	
	/*
	 * The game loop.
	 * Creates "ticks" that occur 60 times a second. Each tick calls the master update method in Map
	 * Also calls the master render method.
	 * Creates debug information that gives us the number of ticks per second and frames per second
	 */
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
			if(DebugEnvironment==true){
				if(System.currentTimeMillis()-timer > 1000){
					timer += 1000;
					System.err.println(updates + " ticks, fps " +frames);
					updates = 0;
					frames = 0;
				}
			}
		}
		
		displayGameOver();
	}
	
	/*
	 * The master render method.
	 * Calls the repaint method in Map
	 */
	private void render() {
		PlayMenu.repaint();
	}
	
	/*
	 * The master update method
	 * Calls the update method of a Map instance
	 */
	private void tick() {
		PlayMenu.update();
		if(!PlayMenu.player.isAlive()){
			stop();
		
		}
	}
	
	
	/*
	 * LISTENERS GO HERE
	 */
	
	private class playListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			playGame();
			if(DebugEnvironment==true)
				System.err.println("play pressed");
		}
	}
	
	private class LeaderBoardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(DebugEnvironment==true)
				System.err.println("LeaderBoard pressed");
			displayLeaderBoard();
		}
	}	

	private class MainMenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(DebugEnvironment==true)
				System.err.println("MainMenu pressed");
			displayMainMenu();
		}
	}	

	private class shootListener extends MouseAdapter {
		
		//Aims for the tip of the mouse
		public void mouseMoved(MouseEvent e){
			if(DebugEnvironment==true)
				System.err.println("click coord " +mouseX +", "+mouseY);
			mouseX=e.getX() - 5;
			mouseY=e.getY() - 30;
		}	
		
	}
	/*
	 * A class that listens for the leaderBoard button to be pressed
	 * Currently does nothing
	 */
	private class instructionsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(DebugEnvironment==true)
				System.err.println("Instructions pressed");
			displayInstructions();
		
		}
	}

}
