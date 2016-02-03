package character;

import java.awt.Color;

public abstract class Enemy {
	
	int x;
	int y;
	int health;
	int size;
	Color color;

	Enemy(int x, int y, int size, int health, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		
		this.color = color;
		this.health = health;
	}
	/* dont work dont know why. worked around
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	*/
	public Color getColor(){
		return color;
	}
	
	abstract void attack(); //how the enemy will attempt to attack you
	
	public void spawn(){
		//draw the enemy with its initial color
		//and set its position to x and y
	}
	
	
	public void update(){
		//call attack method
		attack();
		//check if the enemy is still alive
	}
	
}