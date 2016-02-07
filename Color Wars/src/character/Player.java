package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

import framework.Game;

public class Player extends GameObject{

	boolean[] keys = new boolean[4];
	
	int health = 1;
	int size = 30;
	double sideLength = 2*size/Math.sqrt(3);
	
	public Player(double x, double y, Color color){
		super(x,y,color);
	}
	
	public void changeColor(){
		//if a button is pressed
		//change color of projectile (and possibly player, not sure if we're still doing that)
	}
	
	public void rotate(){
		//have the player point towards the mouse
	}
	
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
		            } 
		            else if (e.getID() == KeyEvent.KEY_TYPED) {
		                
		            }
		            return false;
		        }
		    }
		 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	        manager.addKeyEventDispatcher(new MyDispatcher());
	}
			
		
	public void shoot(){
		//if left mouse down(or spacebar or whatever)
		//shoot projectile
	}
	
	public void update(){
		move();
		if(keys[0]){y -= 10;}
		if(keys[1]){x -= 10;}
		if(keys[2]){y += 10;}
		if(keys[3]){x += 10;}
		// hard coded boundaries
		if(x>= Game.WIDTH-15)
			x = Game.WIDTH-15;
		if(x<=9)
			x = 9;
		if(y>=Game.HEIGHT-50)
			y = Game.HEIGHT-50;
		if(y<=16)
			y = 16;
		//rotate;
		//shootprojectile
	}
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
	
}
