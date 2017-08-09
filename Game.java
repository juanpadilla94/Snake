/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public void run() {
		// NOTE : recall that the 'final' keyword notes inmutability
		// even for local variables.

		// Top-level frame in which game components live
		// Be sure to change "TOP LEVEL FRAME" to the name of your game
		final JFrame frame = new JFrame("SNAKE");
		frame.setLocation(300, 300);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);

		// Main playing area
		final GameCourt court = new GameCourt(status);
		frame.add(court, BorderLayout.CENTER);

		// Reset button
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);

		// Note here that when we add an action listener to the reset
		// button, we define it as an anonymous inner class that is
		// an instance of ActionListener with its actionPerformed()
		// method overridden. When the button is pressed,
		// actionPerformed() will be called.
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.add(court, BorderLayout.CENTER);
				court.reset();
			}
		});
		
		final JButton instructions = new JButton("Instructions");
		instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent g) {
				frame.remove(court);
				final JPanel status_panel = new JPanel();
				frame.add(status_panel, BorderLayout.CENTER);
				final JLabel status = new JLabel();
				status.setText("<html>"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "INSTRUCTIONS"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "MOVE THE SNAKE AROUND THE AREA AND EAT THE APPLE. "
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "THE MORE APPLES YOU EAT, THE HIGHER THE SCORE BUT "
						+ "THE LARGER YOUR SNAKE WILL GROW, MAKING IT "
						+ "MORE DIFFICULT TO MOVE AROUND"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "AVOID COLLIDING WITH THE WALLS, WITH THE OBSTACLES, "
						+ "AND WITH YOURSELF"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "Cool Features: High Scores Tracker and "
						+ "Level Generator"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "CONTROLS :"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "MOVE LEFT : LEFT KEY"
						+ "<br/>"
						+ "<br/>"
						+ "MOVE RIGHT : RIGHT KEY"
						+ "<br/>"
						+ "<br/>"
						+ "MOVE UP : UP KEY"
						+ "<br/>"
						+ "<br/>"
						+ "MOVE DOWN : DOWN KEY"
						);
				status_panel.add(status);
				court.reset();
			}
		});
		
		final JButton highScores = new JButton("High Scores");
		highScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent g) {
				BufferedReader br;
				try {
				br = new BufferedReader(new FileReader("HighScores.txt"));
				frame.remove(court);
				final JPanel status_panel = new JPanel();
				frame.add(status_panel, BorderLayout.CENTER);
				final JLabel status = new JLabel();
				status.setText("<html>"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "HIGH SCORES : TOP 10"
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "1:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "2:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "3:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "4:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "5:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "6:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "7:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "8:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "9:         " + br.readLine()
						+ "<br/>"
						+ "<br/>"
						+ "<br/>"
						+ "10:        " + br.readLine()
						);
				status_panel.add(status);
				br.close();
				court.reset();
				} catch (IOException e) {
					throw new IllegalArgumentException();
				}
			}
		});
		
		// creates new levels
		final JButton levelGenerator = new JButton("Level Generator");
		levelGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent g) {
				JFrame frame = new JFrame("InputDialog Example #1");
				try {
			    String dialogue = JOptionPane.showInputDialog(frame, 
			    		"Input name of file");
			    	if(dialogue != null) {
			    		court.createObstacles(dialogue);
			    	}
				} catch(IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, 
							"Re-enter a valid and existing file name", 
							"File Not Found", 
							JOptionPane.ERROR_MESSAGE);
				}
			    court.reset();
			}
		});
		control_panel.add(instructions);
		control_panel.add(levelGenerator);
		control_panel.add(highScores);
		control_panel.add(reset);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
