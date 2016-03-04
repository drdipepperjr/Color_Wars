package framework;
import java.awt.geom.Ellipse2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class CustomButton extends JButton implements MouseListener{
	private static final long serialVersionUID = 1L;
	Dimension size;
	private String name;
	boolean click = false;
	boolean hover = false;
	int width;
	int height;
	
	public CustomButton(String name, int width, int height)
	{	
		super();
		setVisible(true);
		setFocusable(true);
		setContentAreaFilled(false);
		setBorderPainted(false);
		// might need more stuff
		addMouseListener(this);
		this.name = name;
		size = new Dimension(width, height);
		this.width = width;
		this.height = height;
	}
	public void setButtonText(String text)
	{
		this.name = text;
	}
	public String getButtonText()
	{
		return this.name;
	}
	@Override
	public Dimension getPreferredSize()
	{
		return size;
	}
	@Override
	public Dimension getMaximumSize()
	{
		return size;
	}
	@Override
	public Dimension getMinimumSize()
	{
		return size;
	}
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
}
