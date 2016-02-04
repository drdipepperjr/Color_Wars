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

	public Enemy(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
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
	
	
	public abstract void render(Graphics g);
	//draw the enemy with its initial color
	//and set its position to x and y
	
	
	abstract void attack(); //how the enemy will attempt to attack you	
	
	
	public void update(){
		//call attack method
		attack();
		//check if the enemy is still alive
	}
	
	public String toString(Enemy e){
		String result = "";
		result += this.getClass() +", " + x + ", " + y + ", " + this.color;
		return result;
	}
	
}