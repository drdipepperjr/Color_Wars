package framework;

import character.debugEnemy;
import java.awt.Color;
import java.awt.Graphics;
 
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Map extends JPanel{
	int x=0;		
	debugEnemy e1=new debugEnemy(100,100, 3, 100, Color.CYAN);

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(e1.getColor());
		g.fillOval(e1.x, e1.y, 50, 50);
		g.setColor(Color.red);
		g.fillRect(x, 100, 60, 76);
	}
	
	public void update(){
		//updates EVERYTHING
		e1.update();
//		System.err.println("e1 cordinates are "+ e1.getX()+e1.getY());
		this.x++;
	}
}
