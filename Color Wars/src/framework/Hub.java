package framework;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

import character.Wave;
import utilities.GeneralPathWrapper;

public class Hub extends GeneralPathWrapper implements Shape{
	protected int x; 
	protected int y;
	protected int playerHealth; 
	protected int maxHealth; 
	protected Wave wave;
	protected int score;
	
	public Hub(int playerHealth, Wave wave) {
		this.x=Game.WIDTH;
		this.y=Game.HEIGHT;
		this.playerHealth=maxHealth=playerHealth;
		this.wave=wave;
		this.score=2;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Rectangle healthbar = new Rectangle( playerHealth*10, 10);
		GradientPaint gp = new GradientPaint(0, 10, Color.red,
				 maxHealth*10,10, Color.green, true);
		g2d.setPaint(gp);
		g2d.draw(healthbar);
		g2d.fill(healthbar);
		g2d.setColor(Color.black);
		g2d.drawString("Remaining Enemies " + wave.numEnemies, 0, 25);
		g2d.drawString("Score "+ score, 0, 35);
	}

	public void update(int playerHealth, int score) {
		this.playerHealth=playerHealth;
		this.score=score;
		
	}

}
