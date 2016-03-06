package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;

import utilities.ShapeTransforms;

public class Triangle extends Enemy{
	double xLength;
 	double yLength;
 	double hyp;
 	double theta;
	
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
		Shape t2 = ShapeTransforms.rotatedCopyOf(triangle, theta-Math.PI/2);
	 	g2d.draw(t2);
	 	g2d.fill(t2);
	}
	
	/*
	 * The triangle will attempt to move to the player's coordinates
	 */
	@Override 
	void attack(double playerX, double playerY) {
		if (this.x < playerX) this.x+=3;
		if (this.x > playerX) this.x-=3;
		if (this.y < playerY) this.y+=3;
		if (this.y > playerY) this.y-=3;
		xLength = x-playerX;
	 	yLength = y-playerY;
	 	hyp = Math.sqrt(Math.pow(xLength, 2)+Math.pow(yLength,2));
	 	theta = 0;
	 	
	 	if(xLength > 0)
	 		theta = Math.asin(yLength/hyp);
	 	if(xLength <= 0)
	 		theta = Math.PI - Math.asin(yLength/hyp);
	}

	@Override
	public String getType(){
		return "Triangle";
	}

}
