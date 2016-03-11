package framework;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

/*
 * A custom button that is placed on the GUI
 */
@SuppressWarnings("serial")
public class CustomButton extends JButton implements MouseListener{
	
	private boolean click = false;
	private boolean hover = false;
	
	/*
	 * Constructor for objects of type CustomButton
	 * 
	 * @param width the width of the button
	 * @param height the height of the button
	 */
	public CustomButton()
	{	
		super();
		setVisible(true);
		setFocusable(true);
		setContentAreaFilled(false);
		setBorderPainted(false);
		addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g)
	{

		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double(10,7,25,25);
		super.paint(g2d);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(2));
		if(hover){
			g2d.setColor(Color.WHITE);
			g2d.draw(circle);
		}

		if(click)
		{
			g2d.fill(circle);
		}
	}
	
	
	//Overriden stuff from MouseListener
	@Override
	public void mouseClicked(MouseEvent arg0) {
		click = true;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		hover = true;
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		hover = false;
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		click = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		click = false;	
	}
	
}
