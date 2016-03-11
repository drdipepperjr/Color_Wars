package framework;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*
 * A class that handles the music
 */
public class Sound {
    private Clip clip;
    
    /*
     * no-arg contsructor for Sound
     */
    public Sound(){
    }
    
    /*
     * Constructor for Sound
     * 
     * @param fileName The name of the file that is to be played
     */
    public Sound(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (Exception e){
        	System.out.println("R I P");
        }
    }
    
    /*
     * Changes the current song
     * 
     * @param fileName The new file that is to be played
     */
    public void setClip(String fileName)
    {
    	try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (Exception e){
        	e.getStackTrace();
        }
    }
    
    /*
     * Begins playing a new song
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    
    /*
     * Loops the current song if it reaches the end
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    /*
     * Stops the current song
     */
    public void stop(){
        clip.stop();
    }
}