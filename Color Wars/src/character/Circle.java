package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.Random;
/*
 * An Enemy that will try to stay a certain distance away from
 * the Player and shoot at the Player
 */
public class Circle extends Enemy {

	private Random random = new Random();
	private double xMove;
	private double yMove;
	
	/*
	 * Constructor for objects of class Circle
	 */
	public Circle(int x, int y, Color color) {
		super(x, y, color);
		this.health = 2;
		this.delay = random.nextInt(100);
	}
	
	/*
	 * Draws a circle to the screen at the current position
	 * and with the Circle's color
	 */
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Ellipse2D.Double circle = new Ellipse2D.Double(x,y,size,size);
		g2d.setColor(this.color);
		g2d.draw(circle);
		g2d.fill(circle);
		if(this.isHit==true){
			Ellipse2D.Double circle2 = new Ellipse2D.Double(x,y,size,size);
			g2d.setColor(Color.WHITE);
			g2d.draw(circle2);
			g2d.fill(circle2);
		}
	}
	
	/*
	 * The circle will attempt to stay at a certain distance from the player
	 */
	@Override
	void attack(double playerX, double playerY) {
		double xLength = playerX-x;
		double yLength = playerY-y;
		double length= Math.sqrt(Math.pow(xLength, 2)+Math.pow(yLength, 2));
		this.xMove=xLength/length;
		this.yMove=yLength/length;
		
		if(length > 300){
			this.x+=xMove*3;
			this.y+=yMove*3;
		}
		if(length <= 310){
			this.x-=xMove*3;
			this.y-=yMove*3;
		}
		
	}

	@Override
	public String getType(){
		return "Circle";
	}

}