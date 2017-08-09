/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import java.util.ArrayList;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private Square square; // the snake
	private Circle snitch; // the apple
	private Poison poison; // the Poison blocks

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)

	// Game constants
	public static final int COURT_WIDTH = 1242;
	public static final int COURT_HEIGHT = 642;
	public static final int SQUARE_VELOCITY = 12;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 25;
	
	public int score = 0;
	
	public ArrayList<Poison> poisonHolder = new ArrayList<Poison>();
	
	// CORRECTION //

	public GameCourt(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tick();
				} catch (IOException e1) {
					// FIIIXXX //////////////////////////////
					throw new IllegalArgumentException();
				}
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// This key listener allows the square to move as long
		// as an arrow key is pressed, by changing the square's
		// velocity accordingly. (The tick method below actually
		// moves the square.)
		addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT && square.v_x == 0) {
					square.v_x = -SQUARE_VELOCITY;
				    square.v_y = 0;
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT && 
						square.v_x == 0) {
					square.v_x = SQUARE_VELOCITY;
					square.v_y = 0;
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN && 
						square.v_y == 0) {
					square.v_y = SQUARE_VELOCITY;
					square.v_x = 0;
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP && square.v_y == 0) {
					square.v_y = -SQUARE_VELOCITY;
					square.v_x = 0;
				}
			}		
		});

		this.status = status;
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {
		square = new Square(COURT_WIDTH, COURT_HEIGHT);
		poison = new Poison(COURT_WIDTH, COURT_HEIGHT);
		snitch = new Circle(COURT_WIDTH, COURT_HEIGHT);

		playing = true;
		status.setText("Running...");

		ArrayList<Point> empty = new ArrayList<Point>();
		square.trail = empty;
		score = 0;
		
		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}
	
	public void createObstacles(String dialogue) {
		ArrayList<Poison> emptyPoison = new ArrayList<Poison>();
		poisonHolder = emptyPoison;
		BufferedReader br;
	    try {
	    	br = new BufferedReader(new FileReader(dialogue));
	    	for(int i = 0; i < 6; i++) {
	    		for(int j = 0; j < 12; j++) {
	    			if(Integer.parseInt("" + (char)br.read()) == 1) {
	    				Poison p = new Poison(COURT_WIDTH, COURT_HEIGHT);
	    				p.pos_x = j * 100 + 21;
	    				p.pos_y = i * 100 + 21;
	    				poisonHolder.add(p);
	    			}
	    		}
	    			if(i != 5) {
	    				br.readLine();
	    			}
	    	}
	    	br.close();
	    } catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 * @throws IOException 
	 */
	void tick() throws IOException {
		if (playing) {
			
			// make the snitch bounce off walls...
			snitch.bounce(snitch.hitWall());
			// ...and the mushroom
			
			status.setText("Score: " + String.valueOf(score));
			
			boolean checkHitObstacle = false;
			for(int i = 0; i < poisonHolder.size(); i++) {
				snitch.bounce(snitch.hitObj(poisonHolder.get(i)));
				if(square.intersects(poisonHolder.get(i))) {
					checkHitObstacle = true;
				}
			}
			
			// check for the game end conditions
			if (square.checkHitWall() || square.checkHitTrail() || 
					checkHitObstacle ) {
				playing = false;
				status.setText("You lost!    " + 
						"Score: " + String.valueOf(score));
				
				
				// Check for high scores
				BufferedReader br = 
						new BufferedReader(new FileReader("HighScores.txt"));
					int holderScore = score;
					boolean newRecord = false;
					String[] recordHolder = new String[10];
					//String nextRecord;
					for(int i = 0; i < 10; i++) {
						String currentLine = br.readLine();
						if(holderScore > Integer.valueOf(currentLine) && 
								holderScore > 0) {
							holderScore = -1;
							newRecord = true;
							recordHolder[i] = String.valueOf(score);
							if(i != 9) {
								recordHolder[i+1] = currentLine;
								i++;
							}
						}
						else{
							recordHolder[i] = currentLine;
						}
					}
				br.close();
					
					if(newRecord) {
						BufferedWriter out = 
								new BufferedWriter(new FileWriter
										("HighScores.txt", false));
						for(int i = 0; i < 10; i++) {
							out.write(recordHolder[i]);
							out.newLine();
						}
						out.flush();
						out.close();
					}							
			}
			
			if (square.intersects(snitch)) {
				square.plusTrail();
				score++;
				
				boolean intersectsAny = true;
				
				while(intersectsAny == true) {
					snitch.resetPosition();
					intersectsAny = false;
					for(int i = 0; i < poisonHolder.size(); i++) {
						if (snitch.intersects(poisonHolder.get(i))) {
							intersectsAny = true;
						}
					}
				}
				
			}
			
			// advance the square and snitch in their
			// current direction.
			square.updateTrail();
			square.move();
			snitch.move();

			// update the display
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		square.draw(g);
		poison.draw(g);
		snitch.draw(g);
		for(int i = 0; i < poisonHolder.size(); i++) {
			poisonHolder.get(i).draw(g);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
