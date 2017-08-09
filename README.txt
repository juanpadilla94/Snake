Snake Implementation

Note: 
Square.java represents the snake
Circle.java represents the apple


Classes:

Game.java:
The main class of the project. It handles the frame and widgets of the project. I added the "instructions" button,
the "high scores" button, and the "level generator" button so that the user can choose any of them at any point during
the game. It has a method with a reader that checks from a file, HighScores.txt all the top ten high scores. I also have a 
Writer that corrects the txt file in case a new record is broken. I also have another method that allows the user
to input a txt file that has information about which level he/she wants to upload.

GameCourt.java:
This class checks the state of the game through a timer. It checks if the user gained points, lost, or is still playing.
I created a method called "createObstacles" that reads in a String from game.java and creates the level based on what
the user created. Each level is defined by the number of blocks that will be displayed in the game. Since there could
be a numerous amount of blocks at any point during the game, I created an ArrayList of "poisons i.e blocks" 
called poisonHolder. Everytime the reader reads in the file and checks for the blocks that the user requested, the blocks
are displayed in the game and added to poisonHolder. This is important because in order to check if the snake hit
the blocks or check if the apple bounced off the blocks, I need to go through each one of the blocks, check their positions
and see if they actually intersect the snake/apple. I also used poisonHolder to go through each one the blocks and
draw them in the paintComponent method. 

GameObj.java:
This class manages the state of the snake, the apple, and the poison. It updates the movement of these objects and also
the interaction between them, such as checking if they hit each other or not. The big feature of this class is my
ArrayList of Points called "trail", which represent the coordinates of each of the squares that make the snake. The Snake starts with
3 squares as its body. Everytime it moves, the Snake's body must be updated in order to reflect the fact that the
Snake only has a body of 3 squares. In order to do this, I add the new square based on the coordinate of the head,
and remove one square from the tail. The method that has this function is called updateTrail(). If the snake
eats an apple, the snake's body grows by 1, so this must be reflected on the ArrayList of Points. I do this by adding
one new Point to the trail of points using the method plusTrail().

Point.java:
Since there is no efficient way of having a data set representing a pair of something, I made a class that represents
the coordinates of the Snake at any given time during the game. I could not use a map because a map would involve having
one coordinate, say x, be the key, and the y-coordinate be the value, but then if one x-coordinate has several y-values,
it would be hard to pair them up without creating a new key that has the same x-value.

Square.java:
Class that represents the snake. It goes through the ArrayList of Points called trail (mentioned above) and draws each one
of them using for each loops. It checks if a square is the head in order to give it a distint look compared to the body.

Circle.java:
Simply goes through each coordinate of the apple and colors it red, given a set width and height.

Poison.java:
Creates a single block of "poison". Each block of poison is 100 x 100 and is black. This method is called several times
whenever there are multiple blocks of poison at any given time.