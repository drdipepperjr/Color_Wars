package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
public class Square extends Enemy {

	/*
	 * Constructor for objects of class Square
	 */
	public Square(int x, int y, Color color) {
		super(x, y, color);
		this.health = 5;
		this.delay = 149;
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
		//double distanceFromPlayer = Math.sqrt(Math.pow(this.x-playerX,2.0)+Math.pow(this.y-playerY,2.0));
		if(this.getDelay()==150)
			{
				Random randomNum = new Random();
				int ranNum = randomNum.nextInt(4);
				int ranNum2 = randomNum.nextInt(50) +100;
				if(ranNum==0)
					this.x = playerX+ranNum2;
				if(ranNum==1)
					this.y = playerY+ranNum2;
				if(ranNum==2)
					this.x = playerX-ranNum2;
				if(ranNum==3)
					this.y = playerY-ranNum2;
			}
		
	}

	@Override
	public String getType(){
		return "Square";
	}
}