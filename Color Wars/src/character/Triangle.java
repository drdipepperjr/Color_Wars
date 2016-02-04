package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;

public class Triangle extends Enemy{

	double sideLength = 2*size/Math.sqrt(3);
	
	public Triangle(int x, int y, Color color) {
		super(x, y, color);
	}

	
	
	@Override
	public void spawn(Graphics g) {
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
	
	@Override
	void attack() {
		this.x++;
		
	}

	

}