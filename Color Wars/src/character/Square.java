package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
public class Square extends Enemy {
	int distanceFromPlayer;

	/*
	 * Constructor for objects of class Square
	 */
	public Square(int x, int y, Color color) {
		super(x, y, color);
		this.health = 5;
		this.delay = 149;
		this.distanceFromPlayer=600;
		
	}
	
	/*
	 * Draws a square to the screen at the current position
	 * and with the Square's color
	 */
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Rectangle2D.Double square = new Rectangle2D.Double(x,y,size,size);
		g2d.setColor(this.color);
		g2d.draw(square);
		g2d.fill(square);
		if(this.health < 4){
			g2d.setColor(Color.BLACK);
			Line2D.Double line = new Line2D.Double(x+15,y,x+20,y+20);
			g2d.draw(line);
			if(health <3){
				Line2D.Double line2 = new Line2D.Double(x+20,y+20,x+15,y+30); 
				g2d.draw(line2);
				if(health<2){
					Line2D.Double line3 = new Line2D.Double(x+20,y+10,x+5,y+25); 
					g2d.draw(line3);
				}
			}
			
			
		}
		if(this.isHit==true){
			g2d.setColor(Color.WHITE);
			g2d.draw(square);
			g2d.fill(square);
		}
	}

	/*
	 * The square will teleport a certain distance away from the player
	 * and will stay there until the player moves out of it's range
	 */
	@Override
	void attack(double playerX, double playerY) {
		if(this.getDelay()==150){
			double xLength = playerX-x;
			double yLength = playerY-y;
			double length= Math.sqrt(Math.pow(xLength, 2)+Math.pow(yLength, 2));
			if(length<0)
				length=1;
			Random randomNum = new Random();
			double weight=randomNum.nextInt((int)length);
			double xMove=xLength/length;
			double yMove=yLength/length;
			int sign=randomNum.nextInt(2);
			double xOffset=randomNum.nextInt(25)*Math.pow(-1, sign);
			double yOffset=randomNum.nextInt(25)*Math.pow(-1, sign);
			this.setX(x+xMove*weight+xOffset);
			this.setY(y+yMove*weight+yOffset);
			//System.err.println("("+x+","+y+")");
			/*
			Random randomNum = new Random();
			double xCoor=randomNum.nextInt(distanceFromPlayer);
			double yCoor=distanceFromPlayer-xCoor;
			int sign=randomNum.nextInt(2);
			xCoor=xCoor*Math.pow(-1, sign);
			sign=randomNum.nextInt(2);
			yCoor=yCoor*Math.pow(-1, sign);
			

			distanceFromPlayer-=randomNum.nextInt(150);
			if (distanceFromPlayer<0){	
				distanceFromPlayer=1;
			}*/
		}		
	}

	@Override
	public String getType(){
		return "Square";
	}
}