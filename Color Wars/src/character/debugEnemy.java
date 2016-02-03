package character;

import java.awt.Color;
import java.util.Random;


public class debugEnemy extends Enemy{
	public int x;
	public int y;
	int health;
	int size;
	Color color;
	Random random=new Random();
	public debugEnemy(int x, int y, int size, int health, Color color) {
		super(x, y, size, health, color);
	
	}

	public void update(){
		attack();
		
	}
	@Override
	void attack(){
		//this.x++;
	//	System.err.println("attack "+ this.x+this.y);
	x+=random.nextInt(5)-2;
	y+=random.nextInt(5)-2;	
	}
}
