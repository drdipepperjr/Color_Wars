package character;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

import utilities.GeneralPathWrapper;

public class GameObject extends GeneralPathWrapper implements Shape{

	double x;
	double y;
	int size;
	Color color;
	boolean isAlive;
	
	public GameObject(double x, double y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
		isAlive = true;
	}
	
	@Override
	public Rectangle getBounds(){
		Rectangle r =  new Rectangle((int)x,(int)y,size,size);
		return r;
	}
	
	public boolean isCollidedWith(Enemy e){
		if(this.getBounds().intersects(e.getBounds()))
			return true;
		
		return false;
	}
	
	public boolean isAlive(){
		return this.isAlive;
	}
	
	public void destroy(){
		isAlive = false;
	}
	
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
