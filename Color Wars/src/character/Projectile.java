package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import framework.Game;

/*
 * A class that represents projectiles which are shot out of the
 * Player or enemies
 */
public class Projectile extends GameObject{
	private double xMove;
	private double yMove;
	private int size = 10;
	private static boolean DebugEnvironment=false;
	
	/*
	 * Constructor for objects of class projectile
	 * NOTE: xEnd and yEnd are not the ending coordinates. They are used to calculate
	 * a line on which the projectile will move and at what speed.
	 * 
	 * @param xStart the starting x-coordinate
	 * @param yStart the starting x-coordinate
	 * @param xEnd the x-position that the porectile will move to
	 * @param yEnd the y-position that the porectile will move to
	 * @param color color of the object
	 */
	public Projectile(double xStart, double yStart, double xEnd, double yEnd,  Color color) {
		super(xStart, yStart, color);
		double xLength = xEnd-xStart;
		double yLength = yEnd-yStart;
		double length= Math.sqrt(Math.pow(xLength, 2)+Math.pow(yLength, 2));
		this.xMove=xLength/length;
		this.yMove=yLength/length;
	
	}

	/*
	 * Draws a circle representing the projectile at the current position
	 * and with the projectile's color
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Ellipse2D.Double circle = new Ellipse2D.Double(x,y,size,size);
		g2d.setColor(this.color);
		g2d.draw(circle);
		g2d.fill(circle);
	}

	/*
	 * Makes the Projectile move and destroys it if it becomes out of bounds
	 * NOTE: playerX and playerY are the starting coordinates for the Player's projectiles,
	 * but are the ending coordinates for Enemy projectiles
	 * 
	 * @param playerX the player's current x-coordinate
	 * @param playerY the player's current y-coordinate
	 */
	public void update(double playerX, double playerY) {
		x+=xMove*5;
		y+=yMove*5;
		if(outOfBounds()){
			if(DebugEnvironment){
				System.err.println("projectile moved out of bounds");
			}
			destroy();
		}
	}
	
	/*
	 * checks to see if the projectile has left the screen.
	 * 
	 * @return true if the projectile has left the screen. False otherwise.
	 */
	public boolean outOfBounds(){
		if((this.x > Game.WIDTH)||(this.x < 0)||(this.y > Game.HEIGHT)||(this.y < 0))
			return true;
		return false;
	}
	
	/*
	 * @return a Rectangle object which is used for detecting collisions
	 */
	@Override
	public Rectangle getBounds(){
		Rectangle r =  new Rectangle((int)this.getX(),(int)this.getY(),this.size,this.size);
		return r;
	}
	
	@Override
	public String getType(){
		return "Projectile";
	}

	/*
	 * plays a sound when the projectile is blocked
	 */
	public void blockedAudio() {
		Clip clip;
		String soundName="res/blocked.wav";  
		AudioInputStream audioInputStream;	    		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			} 
		catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} 
		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}

}