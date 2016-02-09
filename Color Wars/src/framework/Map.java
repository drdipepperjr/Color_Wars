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
	
	
	int shootDelay = 0;
	//TEST PLAYER
	Player player = new Player(Game.WIDTH/2, Game.HEIGHT/2, Color.black);
	double pX = player.getX();
	double pY = player.getY();
	
	//TEST ENEMIES
	
	//TEST WAVE
	Wave wave1 = new Wave(10);
	
	//TEST PROJECTILE
	Projectiles proj = new Projectiles();
	Projectiles proj2 = new Projectiles();
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		player.render(g);
		wave1.render(g);
		proj.render(g);
		proj2.render(g);
	}
	
	//updates EVERYTHING
	public void update(){
		player.update();	
		pX = player.getX();
		pY = player.getY();		
		wave1.update(pX, pY);
		proj.update(pX,pY);
		proj2.update(pX,pY);
		
		circleShoot(wave1);
		wave1.checkForCollisions(wave1);
		wave1.checkForCollisions(player);
		wave1.checkForCollisions(proj);
		
	}

	public void playerShoot(double x, double y){
		proj.add( new Projectile(pX,pY,x,y, Color.MAGENTA));
		System.err.println("proj size "+ proj.size());
		
	}
	
	public void circleShoot(Wave wave){
		if(this.shootDelay == 60){
			for(int i=0;i<wave.numEnemies;i++){
				if(wave.get(i).getType() == "Circle")
					proj2.add( new Projectile(wave.get(i).getX(),wave.get(i).getY(),pX,pY, Color.CYAN));
					shootDelay = 0;
			}	
		}
		else this.shootDelay++;
	}
}
