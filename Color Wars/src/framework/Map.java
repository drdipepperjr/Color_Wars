package framework;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import character.Player;
import character.Projectile;
import character.Projectiles;
import character.Wave;

/*
 * A class that represents the environment in which the player attempts to survive.
 * Handles all rendering methods and update methods for each current instance of GameObject
 * Handles checking for collisions and removing GameObjects from the game.
 */
@SuppressWarnings("serial")
public class Map extends JPanel{
	public static int score=0;
	JFrame window;
	ImageIcon img = new ImageIcon("res/Map.png");
	
	/*
	 * Creates the one and only instance of Player
	 */
	public Player player = new Player(Game.WIDTH/2, Game.HEIGHT/2, Color.RED);
	private double pX;
	private double pY;
	
	/*
	 * Creates the Waves of Enemies
	 */
	ArrayList<Wave> waveList = new ArrayList<Wave>();
	Wave wave1 = new Wave(4);
	Wave wave2 = new Wave(7);
	Wave wave3 = new Wave(10);
	
	private int currentWave = 0;
	/*
	 * Creates the Projectiles objects.
	 * proj represents the player's projectiles
	 * proj2 represents the Enemies' projectiles
	 */
	Projectiles proj = new Projectiles();
	Projectiles proj2 = new Projectiles();
	
	
	/*
	 * creates player hub
	 */
	Hub hub = new Hub(player.health, wave1);
	/*
	 * Overridden paint method
	 * Calls render methods for Player, Wave, and projectiles
	 */
	public Map(JFrame window){
		this.window = window;
		initializeWaves();
	}
	
	public void initializeWaves(){
		waveList.add(wave1);
		waveList.add(wave2);
		waveList.add(wave3);
		
		//WAVE 1
		for(int i=0;i<wave1.numEnemies;i++) wave1.addTriangle();
		//WAVE 2
		for(int i=0;i<3;i++) wave2.addTriangle();
		for(int i=3;i<wave2.numEnemies;i++) wave2.addSquare();
		//WAVE 3
		for(int i=0;i<3;i++) wave3.addTriangle();
		for(int i=3;i<7;i++) wave3.addCircle();
		for(int i=0;i<wave3.numEnemies;i++) wave3.addSquare();
	}
	
	public void addWave(int currentWave){
		Wave newWave = new Wave(currentWave);
		newWave.autoPopulate();
		waveList.add(newWave);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img.getImage(),0,0,window.getWidth(),window.getHeight(),this);
		player.render(g);
		waveList.get(currentWave).render(g);
		proj.render(g);
		proj2.render(g);
		hub.render(g);
	}
	
	/*
	 * Calls update methods for Player, Wave, and Projectiles
	 * Also checks for all collisions between all GameObjects
	 * Currently ends the game if the Player is no longer alive
	 */
	public void update(){
		player.update();
		if(!player.isAlive()){
			//System.exit(1);
		}
		pX = player.getX();
		pY = player.getY();		
		
		
		player.playerShoot(Game.mouseX,Game.mouseY,proj);
		circleShoot(waveList.get(currentWave));
		
		if(waveList.get(currentWave).numEnemies == 0){
			currentWave++;
			if(currentWave > 2) addWave((currentWave+1)*3+1);
		}
		
		waveList.get(currentWave).update(pX, pY);
		proj.update(pX,pY);
		proj2.update(pX,pY);	
		hub.update(player.health,score, waveList.get(currentWave));
		
		proj2.checkForCollisions(player);
		waveList.get(currentWave).checkForCollisions(waveList.get(currentWave));
		waveList.get(currentWave).checkForCollisions(player);
		waveList.get(currentWave).checkForCollisions(proj);
		waveList.get(currentWave).resetIsHit();
		
	}

	/*
	 * Makes the circles shoot at the player in random intervals
	 *
	 * @param wave the current wave
	 */
	public void circleShoot(Wave wave){
		for(int i=0;i<wave.numEnemies;i++){
			if(wave.get(i).getType() == "Circle"){
				if(wave.get(i).getDelay() == 100){
					proj2.add( new Projectile(wave.get(i).getX(),wave.get(i).getY(),pX,pY, wave.get(i).getColor()));
					wave.get(i).setDelay(0);
				}
				wave.get(i).setDelay(wave.get(i).getDelay() + 1);
			}
			if(wave.get(i).getType() == "Square"){
				if(wave.get(i).getDelay() > 150){
					//proj2.add( new Projectile(wave.get(i).getX(),wave.get(i).getY(),pX,pY, wave.get(i).getColor()));
					wave.get(i).setDelay(0);
				}
				wave.get(i).setDelay(wave.get(i).getDelay() + 1);
			}
		}
	}
	
	/*
	 * updates the score on the Hub
	 */
	public void updateScore(int points){
		score+= points;
	}
}
