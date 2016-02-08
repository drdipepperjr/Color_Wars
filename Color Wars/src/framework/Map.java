package framework;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import character.Player;
import character.Projectile;
import character.Projectiles;
import character.Wave;


@SuppressWarnings("serial")
public class Map extends JPanel{
	
	//TEST PLAYER
	Player player = new Player(Game.WIDTH, Game.HEIGHT, Color.black);
	double pX = player.getX();
	double pY = player.getY();
	
	//TEST ENEMIES
	
	//TEST WAVE
	Wave wave1 = new Wave(10);
	
	//TEST PROJECTILE
	Projectiles proj=new Projectiles();
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		player.render(g);
		wave1.render(g);
		proj.render(g);
	}
	
	//updates EVERYTHING
	public void update(){
		player.update();	
		pX = player.getX();
		pY = player.getY();
		wave1.checkForCollisions(player);
		wave1.checkForCollisions(proj);
		wave1.update(pX, pY);
		proj.update(pX,pY);
		
	}

	public void playerShoot(double x, double y){
		proj.add( new Projectile(pX,pY,x,y, Color.MAGENTA));
		System.err.println("proj size "+ proj.size());
		
	}
	
}
