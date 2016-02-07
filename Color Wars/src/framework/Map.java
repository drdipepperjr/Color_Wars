package framework;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.util.Random;

import character.Player;
import character.Wave;
import character.Projectile;


@SuppressWarnings("serial")
public class Map extends JPanel{
		
	//TEST PLAYER
	Player player = new Player();
	double pX = player.getX();
	double pY = player.getY();
	
	//TEST ENEMIES
	
	//TEST WAVE
	Wave wave1 = new Wave(8);
	
	//TEST PROJECTILE
	Random random= new Random();
	double xStart= random.nextInt(Game.WIDTH);
	double yStart=random.nextInt(Game.HEIGHT);
	double xEnd =random.nextInt(Game.WIDTH);
	double yEnd= random.nextInt(Game.HEIGHT);
	Color color=Color.MAGENTA;
	Projectile proj = new Projectile(xStart,yStart, xEnd, yEnd, color);
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		player.render(g);
		wave1.render(g);
		proj.render(g);
	}
	
	//updates EVERYTHING
	public void update(){
		player.update();	
		pX = player.getX();
		pY = player.getY();
		wave1.update(pX, pY);
		proj.update(pX,pY);
	}

	
	
	
}
