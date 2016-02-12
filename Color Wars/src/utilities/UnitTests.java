package utilities;


import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import character.*;

public class UnitTests {
	
	@Test public void testPlayerConstructor(){
		Player pl = new Player(10.5,17.5,Color.BLACK);
		assertEquals(10.5,pl.getX(),0.1);
		assertEquals(17.5,pl.getY(),0.1);
	}
	@Test public void testCircleConstructor(){
		Circle c = new Circle(0,0,Color.BLACK);
		assertEquals(0,c.getX(),0.1);
		assertEquals(0,c.getY(),0.1);
	}
	@Test public void testTriangleConstructor(){
		Triangle t = new Triangle(0,0,Color.BLACK);
		assertEquals(0,t.getX(),0.1);
		assertEquals(0,t.getY(),0.1);
	}

	@Test public void testSquareConstructor(){
		Square s = new Square(0,0,Color.BLACK);
		assertEquals(0,s.getX(),0.1);
		assertEquals(0,s.getY(),0.1);
	}
	@Test public void testProjectileConstructor(){
		GameObject t = new Projectile(0,0,0, 0, Color.BLACK);
		assertEquals(0,t.getX(),0.1);
		assertEquals(0,t.getY(),0.1);
	}
	
	@Test public void testWaveConstructor(){
		Wave W = new Wave(5);
		assertEquals(5,W.size(),0);
	}
	
	@Test public void testProjectilesConstructor(){
		Projectiles p = new Projectiles();
		assertEquals(0,p.size(),0);
		Projectile p1= new Projectile(0,0,0, 0, Color.BLACK);
		p.add(p1);
		assertEquals(1,p.size(),0);
	}
	
	
}

