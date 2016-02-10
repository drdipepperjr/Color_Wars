package character;

import java.awt.Graphics;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Projectiles extends ArrayList<Projectile> {
	
	public void render(Graphics g){	
		for(int i=0;i<size();i++)
			this.get(i).render(g);
	}
	
	//Checks for collisions between player and projectiles
	//Should only be used with enemy projectiles
	public void checkForCollisions(Player p){
		for(int i=0;i<this.size();i++){
			if(this.get(i).isCollidedWith(p)){
				this.get(i).destroy();
				p.health --;
				System.out.println("Player health = " + p.health);
			}
		}
	}
	
	//Updates the clip via a for loop
	public void update(double playerX, double playerY){
		for(int i=0;i<size();i++){
			this.get(i).update(playerX, playerY);
			if(!this.get(i).isAlive||this.get(i).outOfBounds()){
				this.remove(i);
			}
		}
	}
}
