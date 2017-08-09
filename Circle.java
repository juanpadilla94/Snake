/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.util.ArrayList;

/**
 * A basic game object displayed as an apple, starting in the upper left
 * corner of the game court.
 * 
 */
public class Circle extends GameObj {

	public static final int SIZE = 20;
	public static final int INIT_POS_X = 30;
	public static final int INIT_POS_Y = 10;
	public static final int INIT_VEL_X = 4;
	public static final int INIT_VEL_Y = 4;
	
	// CORRECTION //
	public static ArrayList<Point> EMPTY_TRAIL = new ArrayList<Point>();
	
	// Apple constructor
	public Circle(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE,
				courtWidth, courtHeight, EMPTY_TRAIL);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(pos_x, pos_y, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(pos_x + 2 + width / 3, pos_y + 4 - height / 2, 6, 6);
	}
}