/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.util.ArrayList;

/**
 * A game object displayed as a 100x100 black square
 */
public class Poison extends GameObj implements Comparable<Poison> {
	public static final int SIZE = 100;
	public static final int INIT_X = -500;
	public static final int INIT_Y = -500;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	
	// CORRECTION //
	public static ArrayList<Point> EMPTY_TRAIL = new ArrayList<Point>();

	// Block constructor
	public Poison(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE, SIZE, courtWidth,
				courtHeight, EMPTY_TRAIL);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(pos_x, pos_y, width, height);
	}

	@Override
	public int compareTo(Poison f) {
		return 0;
	}

}