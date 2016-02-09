package character;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import framework.Game;

@SuppressWarnings("serial")
public class Wave extends ArrayList<Enemy> {

	Enemy e;
	Color color;
	int x;
	int y;
	public int numEnemies;
	
	/*
	 * This creates a wave of enemies
	 * All of them are randomly generated
	 * Creates numEnemies Enemies
	 */
	public Wave(int numEnemies){
		Random random = new Random();
		for(int i=0; i<numEnemies; i++){
			int enemyType = random.nextInt(3);
			int enemyColor = random.nextInt(4);
			int xpos = random.nextInt(2);
			int ypos = random.nextInt(2);
			
			if(xpos==0) x = 0-random.nextInt(Game.WIDTH);
			if(xpos==1) x = Game.WIDTH + random.nextInt(Game.WIDTH);
			if(ypos==0) y = 0-random.nextInt(Game.HEIGHT);
			if(ypos==1) x = Game.HEIGHT + random.nextInt(Game.HEIGHT);
	
			if(enemyColor==0) color = Color.RED;
			if(enemyColor==1) color = Color.BLUE;
			if(enemyColor==2) color = Color.GREEN;
			if(enemyColor==3) color = Color.YELLOW;
			
			if(enemyType == 0) e = new Circle(x,y,color);
			if(enemyType == 1) e = new Square(x,y,color);
			if(enemyType == 2) e = new Triangle(x,y,color);
			
			this.add(e);
			
		}
		this.numEnemies=this.size();
	}
	
	//Renders the whole wave via a for loop
	public void render(Graphics g){	
		for(int i=0;i<numEnemies;i++)
			this.get(i).render(g);
	}
	
	//Checks for collisions with other enemies
	//Currently does nothing
	public void checkForCollisions(Wave w){
		for(int i=0;i<numEnemies;i++){
			for(int j=0;j<numEnemies;j++){
				if(i!=j){
					if(this.get(i).isCollidedWith(this.get(j))){
						//Attempt to move away from other enemy
						//System.out.println("COLLISION DETECTED BETWEEN ENEMIES");
					}
				}
			}
		}
	}
	
	//Checks for collisions with the player
	//Currently destroys all enemies that come in contact with the player
	public void checkForCollisions(Player p){
		for(int i=0;i<numEnemies;i++){
			if(this.get(i).isCollidedWith(p)){
				System.out.println("COLLISION DETECTED WITH PLAYER. ENEMY DESTROYED");
				this.get(i).destroy();
			}
		}
	}
	
	//Checks for collisions with projectiles
	public void checkForCollisions(Projectiles p){
		for(int i=0;i<numEnemies;i++){
			for(int j=0;j<p.size();j++){
				if(this.get(i).isCollidedWith(p.get(j))){
					System.out.println("COLLISION DETECTED WITH PROJECTILE. ENEMY DESTROYED");
					p.get(j).destroy();
					this.get(i).destroy();
				}
			}
		}
	}
	
	//Updates the whole wave via a for loop
	public void update(double playerX, double playerY){
		for(int i=0;i<numEnemies;i++){
			this.get(i).update(playerX, playerY);
			if(!this.get(i).isAlive()){
				this.remove(i);
				numEnemies --;
			}
		}
	}
}
