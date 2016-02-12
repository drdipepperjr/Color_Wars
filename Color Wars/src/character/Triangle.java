package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;

public class Triangle extends Enemy{

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
		g2d.draw(triangle);
		g2d.fill(triangle);
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
	}

	@Override
	public String getType(){
		return "Triangle";
	}

}