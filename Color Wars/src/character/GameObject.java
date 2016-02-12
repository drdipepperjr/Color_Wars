package character;

import java.awt.Color;
import java.awt.Shape;

import utilities.GeneralPathWrapper;

/*
 * A general abstract class that represents any character on the screen.
 */
public abstract class GameObject extends GeneralPathWrapper implements Shape{

	protected double x; 
	protected double y; 
	protected Color color; 
	protected boolean isAlive = true; //tells us if the object is alive or not
	protected int size;
	
	/*
	 * Constructor for objects of class GameObject
	 * @param x x-coordinate 
	 * @param y y-coordinate
	 * @param color color of the object
	 */
	public GameObject(double x, double y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	/*
	 * Determines if another gamoeBoject has collided with this one
	 * @return true if this gameObject is collided with another GameObject
	 * @param another GameObject as a parameter
	*/
	public boolean isCollidedWith(GameObject o){
		if(this.getBounds().intersects(o.getBounds()))
			return true;
		
		return false;
	}
	
	/*
	 * @return true is the object is alive, false otherwise
	 */ 
	public boolean isAlive(){
		return this.isAlive;
	}
	
	/*
	 * Sets the member variable isAlive to false.
	 * which allows GameObject to be removed 
	 * from the game by other methods.
	 */
	public void destroy(){
		isAlive = false;
	}
	
	/*
	 * @return the current x-value of the GameObject as a double
	 */
	public double getX(){
		return this.x;
	}
	
	/*
	 * @ return the current y-value of the GameObject as a double
	 */
	public double getY(){
		 return this.y;
	}
	
	/*
	 * Set the x-coordinate to the value specified.
	 * @param a double x-coordinate.
	 */
	public void setX(double x){
		 this.x = x;
	}
	
	/*
	 * Set the y-coordinate to the value specified.
	 * @param a double y-coordinate
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/*
	 * @return a String representing the GameObject
	 * ex. a triangle returns "Triangle"
	 */
	public abstract String getType();
}
