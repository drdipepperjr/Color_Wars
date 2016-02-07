package framework;

import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;

import character.Player;
import character.Wave;
import character.Projectile;
import character.Projectiles;


@SuppressWarnings("serial")
public class Map extends JPanel{
	
	


	//TEST PLAYER
	Player player = new Player(Game.WIDTH, Game.HEIGHT, Color.black);
	double pX = player.getX();
	double pY = player.getY();
	
	//TEST ENEMIES
	
	//TEST WAVE
	Wave wave1 = new Wave(8);
	
	//TEST PROJECTILE
	Projectiles proj=new Projectiles();
	
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

	public void playerShoot(double x, double y){
		proj.add( new Projectile(pX,pY,x,y, Color.MAGENTA));
		System.err.println("proj size "+ proj.size());
		
	}
	
}
