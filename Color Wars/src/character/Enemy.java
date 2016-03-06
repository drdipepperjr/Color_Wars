package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * An abstract class that represents all characters that try to
 * destroy the player
 */
public abstract class Enemy extends GameObject{
	
	/*
	 * a variable that represents how many hits the enemy can take before it is destroyed
	 */
	int health; 
	int points=10;
	protected int size = 30;
	protected int delay;
	public boolean isHit = false;
	public int flickerDelay;
	
	/*
	 * Constructor for objects of class Enemy
	 */
	public Enemy(double x, double y, Color color) {
		super(x,y,color);
		this.color = color;
	}
	
	/*
	 * Draws the enemy to the screen
	 */
	public abstract void render(Graphics g);
	
	/*
	 * Specifies how the enemy will attempt to attack the player
	 */
	abstract void attack(double playerX, double playerY); //how the enemy will attempt to attack you	
	
	/*
	 * Calls the attack method of the Enemy
	 * @param the player's current x-coordinate
	 * @param the player's current y-coordinate
	 */
	public void update(double playerX, double playerY){
		attack(playerX, playerY);
		
	}
	
	/*
	 * @return a Rectangle object which is used for detecting collisions
	 */
	@Override
	public Rectangle getBounds(){
		Rectangle r =  new Rectangle((int)this.getX(),(int)this.getY(),this.size,this.size);
		return r;
	}
	
	/*
	 * Used for calculating enemy attack patterns and delaying them
	 * so that they aren't calling their attack patterns 60 times a second
	 * @return the delay of the enemy
	 */
	public int getDelay(){
		return this.delay;
	}
	
	/*
	 * Sets the delay of the enemy.
	 * @param number of ticks to be delayed
	 */
	public void setDelay(int delay){
		this.delay = delay;
	}
	
	/*
	 * gets points for killing enemy
	 */
	public int getPoints(){
		return points;
	}
	public void deathAudio(){
		Clip clip;
		String soundName="res/explosion.wav";  
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
	
	public void destroy(){
		deathAudio();
		super.destroy();
	}
}