package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;

import utilities.GeneralPathWrapper;
import framework.Game;

public class Player extends GeneralPathWrapper implements Shape{

	//blah blah blah constructor and instance variables....
	private double x;
	private double y;
	int health = 1;
	int size = 30;
	Color color;
	
	double sideLength = 2*size/Math.sqrt(3);
	
	public Player() {
		this.x = Game.WIDTH/2;
		this.y = Game.HEIGHT/2;
		this.color = Color.BLACK;
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Path2D.Double triangle = new Path2D.Double();
		triangle.moveTo(x,y-size/2);
		triangle.lineTo(x+sideLength/4,y+size/1.5);
		triangle.lineTo(x-sideLength/4, y+size/1.5);
		g2d.setColor(this.color);
		g2d.draw(triangle);
		g2d.fill(triangle);
	}
	
	public void changeColor(){
		//if a button is pressed
		//change color of projectile (and possibly player, not sure if we're still doing that)
	}
	
	public void rotate(){
		//have the player point towards the mouse
	}
	
	
	public void move(){
	}
	
	public void shoot(){
		//if left mouse down(or spacebar or whatever)
		//shoot projectile
	}
	
	public void update(){
		move();
		//rotate;
		//shoot projectile
	}
	
	//LE GETTERS AND LE SETTERS
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}

}