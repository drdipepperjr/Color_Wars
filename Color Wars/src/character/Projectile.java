package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Projectile extends Enemy{
	double xMove;
	double yMove;
	int size =10;
	public Projectile(double xStart, double yStart, double xEnd, double yEnd,  Color color) {
		super(xStart, yStart, color);
		double xLength = xEnd-xStart;
		double yLength = yEnd-yStart;
		double length= Math.sqrt(Math.pow(xLength, 2)+Math.pow(yLength, 2));
		this.xMove=xLength/length;
		this.yMove=yLength/length;
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Ellipse2D.Double circle = new Ellipse2D.Double(x,y,size,size);
		g2d.setColor(this.color);
		g2d.draw(circle);
		g2d.fill(circle);
	}

	@Override
	void attack(double playerX, double playerY) {
		x+=xMove;
		y-=yMove;
	}

}
