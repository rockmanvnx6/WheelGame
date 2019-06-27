package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.WheelResizeAction;
import model.interfaces.GameEngine;
import model.interfaces.Slot;

public class WheelPanel extends JPanel{
	/*
	 * This class draws the WheelPanel and the ball panel
	 * according to the dimension.
	 * 
	 * By default, the ball is 30 times smaller than the view.
	 */
	private static final long serialVersionUID = 1L;
	private Slot currentSlot;

	private int dimension;
	private final int BALL_FACTOR = 30;
	private int ballSize;
	private BufferedImage background = getImage("./img/Basic_roulette_wheel_1024x1024.png");
	
	public WheelPanel(GameEngine model, MainFrame view) {
		/* 
		 * Constructor the height of the application as
		 * the dimension.
		 * 
		 * So if the user resizes the height, it will redraw the wheel and balls.
		 */
		setLayout(new BorderLayout());
		dimension = (int)view.getBounds().getHeight();
		ballSize = dimension/BALL_FACTOR;
		addComponentListener(new WheelResizeAction(view));
	}
	
	public void setDimension(int dimension) {
		/*
		 * Set the new dimension and ballSize.
		 * Repaint the whole wheel and ball.
		 */
		this.dimension = dimension;
		this.ballSize = dimension/BALL_FACTOR;
		repaint();
	}
	
	public void renderSlot(Slot slot) {
		/*
		 * Update the slot, and repaint with the updated
		 * position of the ball.
		 */
		this.currentSlot = slot;
		repaint();
	}
	
	private Point getRadianPosition(int index) {
		/* 
		 * This function use circular function to return a tuple of x and y matches with the index.
		 * It applies a reverse angle finding. Calculate based on pi/2 instead of 0. Since slot 0 aligns with pi/2. 
		 */
		double degreeUnit = (double) 360/Slot.WHEEL_SIZE;
		int x = (int) ((dimension/2 - ballSize/2) +  ((dimension/2 - ballSize) * Math.sin(Math.toRadians((index) * (degreeUnit)))));
		int y = (int) ((dimension/2 - ballSize/2) -  ((dimension/2 - ballSize) * Math.cos(Math.toRadians((index) * (degreeUnit)))));
		
		return new Point(x,y);
	}
	
	public void paintComponent(Graphics g) {
		/* 
		 * Draw both wheel and the slot according to the dimension
		 * and slot index. 
		 */
		super.paintComponent(g);
				
		if(background != null) // if found file
			g.drawImage(background, 0, 0, dimension, dimension, null);
		
		if(currentSlot != null) {
			Point position = getRadianPosition(currentSlot.getPosition());
			g.setColor(new Color(255,255,0,200));
			g.fillOval(position.x, position.y, ballSize, ballSize);
		}
	}
	
	private BufferedImage getImage(String imageFile) {
		/* 
		 * Buffer image from an image file. 
		 * This function makes sure that the program can find
		 * the image.
		 */
		try {
			return ImageIO.read(new File(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
