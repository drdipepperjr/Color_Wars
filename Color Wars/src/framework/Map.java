package framework;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import character.Circle;
import character.Enemy;
import character.Square;
import character.Triangle;
import character.debugEnemy;


@SuppressWarnings("serial")
public class Map extends JPanel{
			
	int currentWave = 0;
	
	debugEnemy e1=new debugEnemy(100,100, Color.CYAN);
	Enemy c1 = new Circle(300,300,Color.BLUE);
	Enemy s1 = new Square(200,200,Color.RED);
	Enemy t1 = new Triangle(100,100,Color.GREEN);
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		c1.spawn(g);	
		s1.spawn(g);
		t1.spawn(g);
		/*
		g.setColor(e1.getColor());
		g.fillOval(e1.x, e1.y, 50, 50);
		g.setColor(Color.red);
		g.fillRect(100, 100, 60, 76);
		*/
	}
	
	public void update(){
		//updates EVERYTHING
		e1.update();
		c1.update();
		s1.update();
		t1.update();
//		System.err.println("e1 cordinates are "+ e1.getX()+e1.getY());
		
	}
}
