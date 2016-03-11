package character;

import java.awt.Graphics;
import java.util.ArrayList;

/*
 * A class used for handling projectiles.
 * A Projectiles object can be used by the Player or by Enemies
 */
@SuppressWarnings("serial")
public class Projectiles extends ArrayList<Projectile> {
	private static boolean DebugEnvironment=false;
	/*
	 * Calls the render method of each projectile
	 */
	public void render(Graphics g){	
		for(int i=0;i<size();i++)
			this.get(i).render(g);
	}
	
	/* 
	 * Checks for collisions between player and projectiles
	 * Should only be called on an Enemy's Projectiles object
	 * 
	 *  @param the player
	 */
	public void checkForCollisions(Player p){
		for(int i=0;i<this.size();i++){
			if(this.get(i).isCollidedWith(p)){
				this.get(i).destroy();
				p.health --;
				p.isHit=true;
				if(DebugEnvironment==true){
					System.err.println("Projectile "+ i+ " colided with player");
					System.out.println("Player health = " + p.health);
				}
			}
		}
	}
	
	/*
	 * Calls the update method of each Projectile
	 * Checks to see if the projectile should be removed from the game
	 * 
	 * @param the player's current x-coordinate
	 * @param the player's current y-coordinate
	 */
	public void update(double playerX, double playerY){
		for(int i=0;i<size();i++){
			this.get(i).update(playerX, playerY);
			if(!this.get(i).isAlive||this.get(i).outOfBounds()){
				this.remove(i);
			}
		}
	}
}
