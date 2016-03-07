package framework;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Instructions extends JPanel{
	CustomButton mainMenu;
	JFrame window;
	ImageIcon img = new ImageIcon("res/instructions.jpg");;
	public boolean started = false;
	public Instructions(JFrame window){
		this.window = window;
		
		this.setLayout(null);
		mainMenu = new CustomButton("Play",247,37);
		mainMenu.setBounds(372,679,247,37);
		add(mainMenu);
		
		window.getContentPane().add(this);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(!started)	
			g.drawImage(img.getImage(),0,0,window.getWidth(),window.getHeight(),this);
	}
}
