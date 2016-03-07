package character;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import framework.Game;
import framework.Map;

/*
 * A class used for handling all current insatnces of enemies.
 * Each instance of wave represents a new horde of enemies
 */
@SuppressWarnings("serial")
public class Wave extends ArrayList<Enemy> {

	/*
	 * The number of enemies that are currently alive
	 */
	Random random = new Random();
	public int numEnemies;
	private Enemy e;
	private Color color;
	private int x;
	private int y;
	
	/*
	 * Constructor for objects of class Wave
	 * This creates a wave of enemies
	 * All of them are randomly generated
	 * @param numEnemies the number of enemies in the current wave
	 */
	public Wave(int numEnemies){
		
		this.numEnemies = numEnemies;
	}
	
	/*
	 * Fills the wave with random enemies
	 */
	public void autoPopulate(){
		for(int i=0; i<numEnemies; i++){
			int enemyType = random.nextInt(3);
			int enemyColor = random.nextInt(4);
			int xpos = random.nextInt(2);
			int ypos = random.nextInt(2);
			
			if(xpos==0) x = 0-random.nextInt(Game.WIDTH);
			if(xpos==1) x = Game.WIDTH + random.nextInt(Game.WIDTH);
			if(ypos==0) y = 0-random.nextInt(Game.HEIGHT);
			if(ypos==1) y = Game.HEIGHT + random.nextInt(Game.HEIGHT);
	
			if(enemyColor==0) color = Color.RED;
			if(enemyColor==1) color = Color.BLUE;
			if(enemyColor==2) color = Color.GREEN;
			if(enemyColor==3) color = Color.YELLOW;
			
			if(enemyType == 0) e = new Circle(x,y,color);
			if(enemyType == 1) e = new Square(x,y,color);
			if(enemyType == 2) e = new Triangle(x,y,color);
			
			this.add(e);
			
		}
	}
	
	/*
	 * add a triangle to the wave
	 */
	public void addTriangle(){
		int enemyColor = random.nextInt(4);
		int xpos = random.nextInt(2);
		int ypos = random.nextInt(2);
		
		if(xpos==0) x = 0-random.nextInt(Game.WIDTH);
		if(xpos==1) x = Game.WIDTH + random.nextInt(Game.WIDTH);
		if(ypos==0) y = 0-random.nextInt(Game.HEIGHT);
		if(ypos==1) y = Game.HEIGHT + random.nextInt(Game.HEIGHT);
		
		if(enemyColor==0) color = Color.RED;
		if(enemyColor==1) color = Color.BLUE;
		if(enemyColor==2) color = Color.GREEN;
		if(enemyColor==3) color = Color.YELLOW;
		
		e = new Triangle(x,y,color);
		this.add(e);
	}
	
	/*
	 * add a circle to the wave
	 */
	public void addCircle(){
		int enemyColor = random.nextInt(4);
		int xpos = random.nextInt(2);
		int ypos = random.nextInt(2);
		
		if(xpos==0) x = 0-random.nextInt(Game.WIDTH);
		if(xpos==1) x = Game.WIDTH + random.nextInt(Game.WIDTH);
		if(ypos==0) y = 0-random.nextInt(Game.HEIGHT);
		if(ypos==1) y = Game.HEIGHT + random.nextInt(Game.HEIGHT);
		
		if(enemyColor==0) color = Color.RED;
		if(enemyColor==1) color = Color.BLUE;
		if(enemyColor==2) color = Color.GREEN;
		if(enemyColor==3) color = Color.YELLOW;
		
		e = new Circle(x,y,color);
		this.add(e);
	}
	
	/*
	 * add a square to the wave
	 */
	public void addSquare(){
		int enemyColor = random.nextInt(4);
		int xpos = random.nextInt(2);
		int ypos = random.nextInt(2);
		
		if(xpos==0) x = 0-random.nextInt(Game.WIDTH);
		if(xpos==1) x = Game.WIDTH + random.nextInt(Game.WIDTH);
		if(ypos==0) y = 0-random.nextInt(Game.HEIGHT);
		if(ypos==1) y = Game.HEIGHT + random.nextInt(Game.HEIGHT);
		
		if(enemyColor==0) color = Color.RED;
		if(enemyColor==1) color = Color.BLUE;
		if(enemyColor==2) color = Color.GREEN;
		if(enemyColor==3) color = Color.YELLOW;
		
		e = new Square(x,y,color);
		this.add(e);
	}
	
	/*
	 * Calls the render method for each Enemy in the Wave
	 */
	public void render(Graphics g){	
		for(int i=0;i<numEnemies;i++)
			this.get(i).render(g);
	}
	
	/*
	 * Checks for collisions between each Enemy in the Wave
	 * 
	 * @param the current wave
	 */
	public void checkForCollisions(Wave w){
		int z = random.nextInt(50);
		for(int i=0;i<numEnemies;i++){
			for(int j=0;j<numEnemies;j++){
				if(i!=j){
					if(this.get(i).isCollidedWith(this.get(j))){
						//Attempt to move away from other enemy
						if (this.get(i).getType()=="Square") {this.get(i).x+=z; this.get(i).y+=z; }
						if (this.get(i).getType()=="Circle") {this.get(i).x ++; this.get(i).y++;}
					}
				}
			}
		}
	}
	
	/*
	 * Checks for collisions between the Player and each Enemy
	 * If an Enemy isCollidedWith the Player, destroy the enemy
	 * and reduce the Player's health by 1
	 * 
	 * @param the player
	 */
	public void checkForCollisions(Player p){
		for(int i=0;i<numEnemies;i++){
			if(this.get(i).isCollidedWith(p)){
				if(Game.DebugEnvironment==true){
					System.err.println("Enemy "+ this.get(i).getType()+ " colided with player");
				}
				this.get(i).destroy();
				p.health--;
				p.isHit = true;
				if(Game.DebugEnvironment==true){
					System.err.println("PlayerHealth = " + p.health);
				}
			}
		}
	}
	
	/*
	 * Checks for collisions between each Enemy and each
	 * projectile inside a Projectiles object.
	 * Reduces the enemy's health if it does collide, and destroys it when
	 * it's health reaches 0.
	 * 
	 * @param a Projectiles object
	 */
	public void checkForCollisions(Projectiles p){
		for(int i=0;i<numEnemies;i++){
			for(int j=0;j<p.size();j++){
				if(this.get(i).isCollidedWith(p.get(j))){
					if(Game.DebugEnvironment==true){
						System.err.println("Enemy "+ this.get(i).getType()+ " colided with projectile "+ j);
					}
					p.get(j).destroy();
					
					//now that it's collided, check to see if the color matches
					if(this.get(i).getColor()==p.get(j).getColor()){
						this.get(i).health--;
						this.get(i).isHit = true;
						if(this.get(i).health == 0)
							this.get(i).destroy();
					}
					else{
						p.get(j).blockedAudio();
					}
				}
			}
		}
	}
	
	/*
	 * Calls the update method on each Enemy and checks to see if it is still alive.
	 * If not, the Enemy is removed from the Wave and numEnemies decreases by 1
	 * 
	 * @param the player's current x-coordinate
	 * @param the player's current y-coordinate
	 */
	public void update(double playerX, double playerY){
		for(int i=0;i<numEnemies;i++){
			this.get(i).update(playerX, playerY);
			if(!this.get(i).isAlive()){
				Map.score+=this.get(i).getPoints();
				this.remove(i);
				numEnemies --;
			}
		}
	}

	public void resetIsHit(){
		
		for(int i=0;i<numEnemies;i++){
			if (this.get(i).isHit==true){
				if(this.get(i).flickerDelay > 5){
					this.get(i).isHit=false;
					this.get(i).flickerDelay = 0;
				}
				else this.get(i).flickerDelay++;
			}
		}
	}
}
