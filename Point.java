
/**
 *A Point Class representing the points traversed by the square
 * 
 */
public class Point {

	private int x_coord = 0;
	private int y_coord = 0;

	public Point(int x_coord, int y_coord) {
		this.x_coord = x_coord;
		this.y_coord = y_coord;
	}
	
	public int getX() {
		return x_coord;
	}
	
	public int getY() {
		return y_coord;
	}
}