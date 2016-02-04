package framework;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import character.Circle;
import character.Enemy;
import character.Square;
import character.Triangle;
import character.Wave;


@SuppressWarnings("serial")
public class Map extends JPanel{
			
	int currentWave = 0;
	
	//TEST ENEMIES
	Enemy c1 = new Circle(300,300,Color.BLUE);
	Enemy s1 = new Square(200,200,Color.RED);
	Enemy t1 = new Triangle(100,100,Color.GREEN);
	
	//TEST WAVE
	Wave wave1 = new Wave(2);
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		wave1.render(g);
		
		c1.render(g);	
		s1.render(g);
		t1.render(g);
		
	}
	
	//updates EVERYTHING
	public void update(){
		wave1.update();
		
		c1.update();
		s1.update();
		t1.update();
		
	}
}
