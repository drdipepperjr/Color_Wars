package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

import framework.Game;

/*
 * A GameObject that is controlled by the User
 */
public class Player extends GameObject{	
	
	/*
	 * a variable that represents how many hits the player can take before it is destroyed
	 */
	int health = 100; 
	
	private int size = 30;
	private double sideLength = 2*size/Math.sqrt(3);
	private boolean[] keys = new boolean[6];

	
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
	        
	    	if(keys[0]){y -= 10;}
			if(keys[1]){x -= 10;}
			if(keys[2]){y += 10;}
			if(keys[3]){x += 10;}
			
			if(keys[4]) {
				changeColorForward();
				keys[4] = false;
			}
			
			if(keys[5]) {
				changeColorBackwards();
				keys[5] = false;
			}
			// hard coded boundaries
			if(x>= Game.WIDTH-15)
				x = Game.WIDTH-15;
			if(x<=9)
				x = 9;
			if(y>=Game.HEIGHT-50)
				y = Game.HEIGHT-50;
			if(y<=16)
				y = 16;
	}
	
	/*
	 * Called when the master update() method is called.
	 * Destroys the player when its health reaches 0
	 * and calls its move() method
	 */
	public void update(){
		if(this.health == 0){
			this.destroy();
		}
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
		 	Path2D.Double triangle = new Path2D.Double();
		 	triangle.moveTo(x,y-size/2);
		 	triangle.lineTo(x+sideLength/4,y+size/1.5);
		 	triangle.lineTo(x-sideLength/4, y+size/1.5);
		 	g2d.setColor(this.color);
		 	g2d.draw(triangle);
		 	g2d.fill(triangle);
	}
	
	@Override
	public Rectangle getBounds(){
		Rectangle r =  new Rectangle((int)this.getX(),(int)this.getY(),this.size,this.size*2);
		return r;
	}
	
	@Override
	public String getType(){
		return "Player";
	}
	
	
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
