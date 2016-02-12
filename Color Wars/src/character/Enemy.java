package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy extends GameObject{
	
	int health;
	int size = 30;
	Color color;
	boolean isAlive;
	int delay;
	
	public Enemy(double x, double y, Color color) {
		super(x,y,color);
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
	
	@Override
	public Rectangle getBounds(){
		Rectangle r =  new Rectangle((int)this.getX(),(int)this.getY(),this.size,this.size);
		return r;
	}
	
	public int getDelay(){
		return this.delay;
	}
	public void setDelay(int delay){
		this.delay = delay;
	}
}