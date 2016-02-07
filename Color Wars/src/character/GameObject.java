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
	
	//Returns a rectangle that we use for getBounds()
	@Override
	public Rectangle getBounds(){
		Rectangle r =  new Rectangle((int)x,(int)y,size,size);
		return r;
	}
	
	//Returns true if this gameObject collides with another GameObject
	public boolean isCollidedWith(GameObject o){
		if(this.getBounds().intersects(o.getBounds()))
			return true;
		
		return false;
	}
	
	//Check if the GameObject is still alive
	public boolean isAlive(){
		return this.isAlive;
	}
	
	//Sets isAlive to false, other methods actually remove the object from memory
	public void destroy(){
		isAlive = false;
	}
	
	//LE GETTERS AND SETTERS
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
