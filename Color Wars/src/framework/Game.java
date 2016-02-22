package framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * A class that initializes the game window and creates the game loop
 * upon which the whole game is based. 
 */
public class Game implements Runnable {
	
	/*
	 * Variables that represent the height and width of the screen
	 */
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static double mouseX;
	public static double mouseY;

	Sound soundPlayer;
	private JFrame window;
	private JPanel mainMenu;
	private Map PlayMenu;
	
	@SuppressWarnings("unused")
	private JPanel LeaderBoard; //TO BE IMPLEMENTED AT A LATER TIME

	public static boolean DebugEnviorment =false;

	private boolean isRunning= false;
	private Thread thread;
	
	/*
	 * Creates the Game object on which everything depends on and calls it's go method
	 */
	public static void main(String args[]){
		if(args.length!=0 && args[0]== "Debug")
			DebugEnviorment=true;
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
		
		window.setVisible(true);
	}
	
	/*
	 * Displays the main menu and creates buttons to access various parts of the game.
	 */
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
	
	/*
	 * Self-explanatory. Starts the gameplay aspect of the game.
	 */
	public void playGame(){
		
		soundPlayer.stop();
		soundPlayer.setClip("res/MoodyLoop.wav");
		soundPlayer.loop();
		 
		window.getContentPane().remove(mainMenu);
		PlayMenu= new Map();
		window.setVisible(false);
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
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
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
			if(DebugEnviorment==true){
				if(System.currentTimeMillis()-timer > 1000){
					timer += 1000;
					System.err.println(updates + " ticks, fps " +frames);
					updates = 0;
					frames = 0;
				}
			}
		}
		stop();
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
	}
	
	/*
	 * A class that listens for the play button to be pressed.
	 * Calls playGame() when it is pressed
	 */
	class playListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			playGame();
			if(DebugEnviorment==true)
				System.err.println("play pressed");
		}
	}
	
	/*
	 * A class that listens for the leaderBoard button to be pressed
	 * Currently does nothing
	 */
	class leaderBoardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {

			System.err.println("LeaderBoard pressed");

			if(DebugEnviorment==true)
				System.err.println("LeaderBoard pressed");
			//game.displayLeaderBoard();
			//doesn't do anything yet

		}
	}	
	

	/*
	 * A class that listens for mouse input from the player during the game.
	 */
	public class shootListener extends MouseMotionAdapter{
		
		//Aims for the tip of the mouse
		public void mouseMoved(MouseEvent e){

			mouseX=e.getX() - 5;
			mouseY=e.getY() - 38;

			if(DebugEnviorment==true)
				System.err.println("click coord " +mouseX +", "+mouseY);
			
		}	
	}

	  public class Sound {
	        private Clip clip;
	        public Sound(){
	        }
	        public Sound(String fileName) {
	            try {
	                File file = new File(fileName);
	                if (file.exists()) {
	                    AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	                    clip = AudioSystem.getClip();
	                    clip.open(sound);
	                }
	                else {
	                    throw new RuntimeException("Sound: file not found: " + fileName);
	                }
	            }
	            catch (Exception e){
	            	System.out.println("R I P");
	            }
	        }
	        
	        public void setClip(String fileName)
	        {
	        	try {
	                File file = new File(fileName);
	                if (file.exists()) {
	                    AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	                    clip = AudioSystem.getClip();
	                    clip.open(sound);
	                }
	                else {
	                    throw new RuntimeException("Sound: file not found: " + fileName);
	                }
	            }
	            catch (Exception e){
	            	System.out.println("R I P");
	            }
	        }
	        public void play(){
	            clip.setFramePosition(0);
	            clip.start();
	        }
	        public void loop(){
	            clip.loop(Clip.LOOP_CONTINUOUSLY);
	        }
	        public void stop(){
	            clip.stop();
	        }
	    }

}
