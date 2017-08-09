/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.util.ArrayList;

/**
 * A game object displayed as a snake, starting in the upper left
 * corner of the game court.
 * 
 */
public class Square extends GameObj {
	public static final int SIZE = 10;
	public static final int INIT_X = 10;
	public static final int INIT_Y = 10;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;	
	// CORRECTION //
	public static final ArrayList<Point> EMPTY_TRAIL = new ArrayList<Point>();

	/**
	 * Note that, because we don't need to do anything special when constructing
	 * a Square, we simply use the superclass constructor called with the
	 * correct parameters
	 */
	// Snake constructor
	public Square(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE, SIZE, courtWidth,
				courtHeight, EMPTY_TRAIL);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);

		for(Point p : trail) {
			// snake's head
			if(trail.indexOf(p) == trail.size() - 1 ) {
				g.fillOval(p.getX(), p.getY(), width, height);
				g.setColor(Color.RED);
				g.fillOval(p.getX() + (width / 2 - 2), p.getY() + 
						(height / 2 - 3), 4, 4);
			}
			else {
				// snake's body and tail
				g.fillRoundRect(p.getX(), p.getY(), width, height, 4, 4);
			}
		}
	}
}
