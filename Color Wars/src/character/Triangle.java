package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;

import utilities.ShapeTransforms;
/*
 * An enemy that will attempt to run into the player, causing the player to
 * be damaged
 */
public class Triangle extends Enemy{
 
 	private double theta = 0;
	
 	private double xMove;
	private double yMove;
	private double sideLength = 2*size/Math.sqrt(3);
	
	/*
	 * Constructor for objects of class Triangle
	 */
	public Triangle(int x, int y, Color color) {
		super(x, y, color);
		this.health = 1;
	}
	
	/*
	 * Draws a triangle to the screen at the current position
	 * and with the Triangle's color
	 */
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Path2D.Double triangle = new Path2D.Double();
		triangle.moveTo(x,y-size/2);
		triangle.lineTo(x+sideLength/2,y+size/2);
		triangle.lineTo(x-sideLength/2, y+size/2);
		g2d.setColor(this.color);
		Shape t2 = ShapeTransforms.rotatedCopyOf(triangle, theta);
	 	g2d.draw(t2);
	 	g2d.fill(t2);
	 	if(this.isHit==true){
			g2d.setColor(Color.WHITE);
			g2d.draw(t2);
			g2d.fill(t2);
	 	}
	}
	
	/*
	 * The triangle will attempt to move to the player's coordinates
	 */
	@Override 
	void attack(double playerX, double playerY) {
		double xLength = playerX-x;
		double yLength = playerY-y;
		double length= Math.sqrt(Math.pow(xLength, 2)+Math.pow(yLength, 2));
		this.xMove=xLength/length;
		this.yMove=yLength/length;

		this.x+=xMove*5;
		this.y+=yMove*5;
		
		theta += Math.PI/30;
	}

	@Override
	public String getType(){
		return "Triangle";
	}

}
