package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import utilities.GeneralPathWrapper;

public abstract class Enemy extends GeneralPathWrapper implements Shape{
	
	int x;
	int y;
	int health = 1;
	int size = 30;
	Color color;
	boolean isAlive;

	public Enemy(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.isAlive = true;
	}

	public Color getColor(){
		return color;
	}
	
	
	public abstract void render(Graphics g);
	//draw the enemy with its initial color
	//and set its position to x and y
	
	
	abstract void attack(double playerX, double playerY); //how the enemy will attempt to attack you	
	
	
	public void update(double playerX, double playerY){
		//call attack method
		attack(playerX, playerY);
		//check if the enemy is still alive
		
	}
	
	public void destroy(){
		this.isAlive = false;
	}
	
}