package character;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class Player {

	//blah blah blah constructor and instance variables....
	boolean[] keys = new boolean[4];
	public int x = 0;
	public int y = 0;
	public int health = 3;
	public Player()
	{
		System.err.println("HElo");
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
		if(keys[0]){y -= 5;}
		if(keys[1]){x -= 5;}
		if(keys[2]){y += 5;}
		if(keys[3]){x += 5;}
		//rotate;
		//shootprojectile
	}
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Rectangle2D.Double square = new Rectangle2D.Double(x,y,10,10);
		g2d.setColor(Color.RED);
		g2d.draw(square);
		
	}
}
