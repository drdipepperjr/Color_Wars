COLOR WARS README
CS48 Project for W16
Domenic Dipeppe, Andrew Huang, Jackey Lau, Alex Thielk

How to Play:

- Use the build.xml
-- Go into /Color_Wars/Color Wars/ directory
-- There will be a file build.xml in that directory
--- Use command "ant run" to run
--- Use command "ant compile" to compile
--- Use command "ant clean" to clean build
--- Use command "ant test" to run unit tests

Gameplay:

- Objective: To destroy all colorful shapes on screen and achieve a high score. The game will continue to generate random enemies until the Player dies.

- The user will control a black spaces ship and must shoot the enemies to destroy them
-- Use the "W" "A" "S" "D" keys on the keyboard to move up, left, down, right respectively
-- The space ship will automatically fire towards the cursor, which is a reticle during game.
-- You must use "Q" and "E" to shift through your different projectiles. This is important because your projectiles can only hurt an enemy if they are the same color.


- Enemies are color circles, squares, and triangles. These enemies come in four colors and can only be hurt if they are shot with the same color.
-- Circles will always avoid the player and shoot projectiles.
-- Squares can teleport, and are meant to block the player's movement, but they will never teleport on the player. Furthermore, if a Player collides with them, they will do damage equivalent to how much HP the player has.
-- Triangles will always move towards the player, attempting to crash into them.

- All of these objects have health, and will be destroyed if their health reaches 0. The object will flicker white if hurt.
- If the user is destroyed, the game will end

Development:

The following describe the folders in "src" & resources such as music and images are in "rec"

src components:

Framework

- Game.java hosts the JFrame and chooses which JPanel to display (Map, Main Menu, Leaderboard)
- Map.java is the JPanel for the game. It generate and take action listeners for the Player and Enemies
- Leaderboard.java needs to be implemented
- MainMenu.java is a JPanel that the user can use to play the game or check out the leaderboard
- CustomButton this class overloads JButton and is essentially an invisble box that is meant to overlay its background.
- GameOver.java this class also overloads JPanel and displays the GameOver screen.
- HighScores.java this class implements serialazable and handles manipulating the HighScores.ser. Also creates a dialog box if the user creates a new high score
- Hub.java is a class for the "Heads Up Display" in Map.java, essentially lets the user know its HP, score and what wave it is on
- Instructions.java this class implements JPanel and displays the instructions.
- Leaderboard.java this class implements JPanel and is used to display the highscores the user creates by reading from the HighScores.ser
- Sound.java this class that handles the music. Has methods such as load and play.

Characters

- GameObject.java has core methods for all objects and is the base class for player, enemy and projectile
- Player.java creates a class for the player to have various methods such as shoot, move etc
- Projectile.java is essentially an object that takes 4 inputs, (xStart,yStart,xEnd,yEnd) to calculate a line on which the projectile will move.
- Projectiles.java (with an s at the end) is an array of projectiles. Currently, there are two Projectiles instances: one for the player, and one for circles.
- Enemy.java is the base class for Square.java, Circle.java, and Triangle.java
- Wave.java generates enemies

res components:
- Various sounds, music and background images are stored here