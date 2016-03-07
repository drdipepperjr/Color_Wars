package character;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

import utilities.ShapeTransforms;
import framework.Game;

/*
 * A GameObject that is controlled by the User
 */
public class Player extends GameObject{	
	
	/*
	 * a variable that represents how many hits the player can take before it is destroyed
	 */
	public int health = 100; 
	public int delay = 0;
	
	private int size = 40;
	private boolean[] keys = new boolean[6];

	private double vX = 0;
	private double vY = 0;
	
	private double xMax = 7;
	private double yMax = 7;
	
	private double accel = 0.3;
	
	/*
	 * boolean to control when player should flicker and stop flickering when hit
	 */
	public boolean isHit = false;
	public int flickerDelay;
	/*
	 * Constructor for objects of class Player
	 */
	public Player(double x, double y, Color color){
		super(x,y,color);
	}
	
	/*
	 * Adds an inner class that listens for key inputs.
	 * Moves the player a certain direction based on which keys are currently being pressed
	 * ex. "a" will make the player move left on the screen
	 * Will prevent the player from leaving the window.
	 */
	public void move(){
		
		//if (KEY.pressed) move in a certain direction
		 class MyDispatcher implements KeyEventDispatcher {
		        @Override
		        public boolean dispatchKeyEvent(KeyEvent e) {
		            if (e.getID() == KeyEvent.KEY_PRESSED) {
						if(e.getKeyChar() == 'w')
							keys[0] = true;
						else if(e.getKeyChar() == 'a')
							keys[1] = true;
						else if(e.getKeyChar() == 's')
							keys[2] = true;
						else if(e.getKeyChar() == 'd')
							keys[3] = true;
		            } 
		            else if (e.getID() == KeyEvent.KEY_RELEASED) {
		            	if(e.getKeyChar() == 'w')
							keys[0] = false;
						else if(e.getKeyChar() == 'a')
							keys[1] = false;
						else if(e.getKeyChar() == 's')
							keys[2] = false;
						else if(e.getKeyChar() == 'd')
							keys[3] = false;
		            	
						else if(e.getKeyChar() == 'q')
							keys[4] = true;
						else if(e.getKeyChar() == 'e')
							keys[5] = true;
		            } 
		            else if (e.getID() == KeyEvent.KEY_TYPED) {
		                
		            }
		            return false;
		        }
		    }
		 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	        manager.addKeyEventDispatcher(new MyDispatcher());
	        //if w pressed
	    	if(keys[0]){
	    		if(vY >= -yMax)
	    			vY = vY - accel;
	    		y += vY;
	    	}
	    	else{
	    		if(vY < 0){
	    			vY = vY + accel;
	    			if(vY > 0) vY = 0;
	    		}
	    		y += vY;
	    	}
	    	//if a pressed
			if(keys[1]){
				if(vX >= -xMax)
					vX = vX - accel;
				x += vX;
			}
			else{
				if(vX < 0){
					vX = vX + accel;
					if(vX > 0) vX = 0;
				}
				x += vX;
			}
			//if s pressed
			if(keys[2]){
				if(vY <= yMax)
	    			vY = vY + accel;
	    		y += vY;
			}
			else{
				if(vY > 0){
	    			vY = vY - accel;
	    			if(vY < 0) vY = 0;
				}
	    		y += vY;
			}
			//if d pressed	
			if(keys[3]){
				if(vX <= xMax)
					vX = vX + accel;
				x += vX;
			}
			else{
				if(vX > 0){
					vX = vX - accel;
					if(vX < 0) vX = 0;
				}
				x += vX;
			}
			
			if(keys[4]) {
				changeColorForward();
				keys[4] = false;
			}
			
			if(keys[5]) {
				changeColorBackwards();
				keys[5] = false;
			}
			// hard coded boundaries
			if(x>= Game.WIDTH-27)
				x = Game.WIDTH-27;
			if(x<=20)
				x = 20;
			if(y>=Game.HEIGHT-50)
				y = Game.HEIGHT-50;
			if(y<=35)
				y = 35;
	}
	
	/*
	 * Called when the master update() method is called.
	 * Destroys the player when its health reaches 0
	 * and calls its move() method
	 */
	public void update(){
		if(this.health <= 0){
			this.destroy();
		}
		this.delay++;
		move();
	}
	
	/*
	 * Called when the master render() method is called.
	 * Draws a black triangle to the screen which represents the player
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		 	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 			RenderingHints.VALUE_ANTIALIAS_ON);
		 	Path2D.Double player = new Path2D.Double();
		 	 	
		 	player.moveTo(x+size/6, y-size/2);  //1
		 	player.lineTo(x+size/2.5, y+size/3);//2
		 	player.lineTo(x+size/4, y+size/2);  //3
		 	player.lineTo(x-size/4, y+size/2);  //4
		 	player.lineTo(x-size/2.5, y+size/3);//5
		 	player.lineTo(x-size/6, y-size/2);  //6
		 	player.lineTo(x-size/8, y+size/4);  //7
		 	player.lineTo(x+size/8, y+size/4);  //8
		 	player.lineTo(x+size/6, y-size/2); //1
		 	player.lineTo(x+size/2.5, y+size/3);//2
			g2d.setColor(this.color);
		 	
		 	//Variables used for rotating
		 	double xLength = x-Game.mouseX;
		 	double yLength = y-Game.mouseY;
		 	double hyp = Math.sqrt(Math.pow(xLength, 2)+Math.pow(yLength,2));
		 	double theta = 0;
		 	
		 	if(xLength > 0)
		 		theta = Math.asin(yLength/hyp);
		 	if(xLength <= 0)
		 		theta = Math.PI - Math.asin(yLength/hyp);
		 	
		 	Shape p2 = ShapeTransforms.rotatedCopyOf(player, theta-Math.PI/2);
		 	g2d.setColor(Color.black);
		 	g2d.fill(p2);
		 	g2d.setColor(this.color);
		 	g2d.setStroke(new BasicStroke(2));
		 	g2d.draw(p2);
		 	
		 	if(this.isHit==true){
				g2d.setColor(Color.WHITE);
				g2d.draw(p2);
				g2d.fill(p2);
				
				if(flickerDelay > 10){
					isHit=false;
					flickerDelay = 0;
				}
				else flickerDelay++;
				
		 	}
		 	
	}
	
	@Override
	public Rectangle getBounds(){
		Rectangle r =  new Rectangle((int)this.getX()-size/2,(int)this.getY()-size/2,this.size,this.size);
		return r;
	}
	
	/*
	 * @return the current delay
	 */
	public int getDelay(){
		return this.delay;
	}
	
	/*
	 * Sets the delay of the player
	 * @param delay the delay of the player
	 */
	public void setDelay(int delay){
		this.delay = delay;
	}
	
	@Override
	public String getType(){
		return "Player";
	}
	
	
	/*
	 * Creates a new projectile that will travel from the player
	 * to the position specified by the current mouse position
	 * 
	 * @param x the x-coordinate of the mouse
	 * @param y the y-coordinate of the mouse
	 */
	public void playerShoot(double x, double y, Projectiles p){
		if(this.getDelay()==20){
			
			p.add(new Projectile(this.x-5,this.y-5,x,y, this.color));
			this.setDelay(0);
			
			if(Game.DebugEnvironment==true)
				System.err.println("proj size "+ p.size());
		}
	}
	
	/*
	 * Methods for changing the color of the player
	 */
	private void changeColorForward(){
		if(this.color == Color.RED)
			this.color = Color.BLUE;
		else if(this.color == Color.BLUE)
			this.color = Color.GREEN;
		else if(this.color == Color.GREEN)
			this.color = Color.YELLOW;
		else if(this.color == Color.YELLOW)
			this.color = Color.RED;
	}
	
	private void changeColorBackwards(){
		if(this.color == Color.RED)
			this.color = Color.YELLOW;
		else if(this.color == Color.YELLOW)
			this.color = Color.GREEN;
		else if(this.color == Color.GREEN)
			this.color = Color.BLUE;
		else if(this.color == Color.BLUE)
			this.color = Color.RED;
	}
	
}
