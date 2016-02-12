package utilities;


import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import character.Player;

public class UnitTests {
	
	@Test public void testPlayerConstructor(){
		Player pl = new Player(10.5,17.5,Color.BLACK);
		assertEquals(10.5,pl.getX(),0.1);
		assertEquals(17.5,pl.getY(),0.1);
	}

}
