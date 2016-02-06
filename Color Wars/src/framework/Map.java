package framework;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import character.Player;
import character.Wave;


@SuppressWarnings("serial")
public class Map extends JPanel{
		
	//TEST PLAYER
	Player player = new Player();
	double pX = player.getX();
	double pY = player.getY();
	
	//TEST ENEMIES
	
	//TEST WAVE
	Wave wave1 = new Wave(8);
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		player.render(g);
		wave1.render(g);
	}
	
	//updates EVERYTHING
	public void update(){
		player.update();	
		pX = player.getX();
		pY = player.getY();
		wave1.update(pX, pY);
	}

	
	
	
}
