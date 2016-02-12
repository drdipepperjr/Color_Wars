# Color_Wars
A Java Application that runs an exciting in game in which the user controls a black ship and must navigate through enemies and destroy them with the ship's blasters

##CS48 Project for W16
Domenic Dipeppe, Andrew Huang, Jackey Lau, Alex Thielk

##User View
####How To Play
1) Use Eclipse to pull from this repo. Open the Game.java file and press Eclipse's "Run as..." and the game will start.
2) Run our executable .jar file located here: https://www.dropbox.com/s/1o0gjgwh7dbe6pl/ColorWars.jar?dl=0
Note: You'll need java to execute it 

####Main Menu
The user will be brought to a screen that will direct them to either the leaderboard or game

####Leaderboard
* a work in process (unimplemented)

####Game
The user controls the black triangle and must take down enemies by shooting them. The player can die and then lose should the player's health reach 0. The player's health is decremented by one for every enemy collision (including enemy projectiles)

#####Player
* Takes WASD for movement
* Shoots projectiles at the mouse location

#####Enemies
* Triangle Enemy - Runs straight into the player
* Square Enemey - Randomly teleports near player's location
* Circle Enemy - Stays away and shoots at player

##Developer View
Actual gameplay files are located in the "Color_Wars/src" folder
####Framework
* Game.java hosts the JFrame and chooses which JPanel to display (Map, Main Menu, Leaderboard)
* Map.java is the JPanel for the game. It generate and take action listeners for the Player and Enemies
* Leaderboard.java needs to be implemented
* MainMenu.java is a JPanel that the user can use to play the game or check out the leaderboard

####Characters
Mostly self explanatory
* GameObject.java has core methods for all objects and is the base class for player, enemy and projectile
* Player.java creates a class for the player to have various methods such as shoot, move etc
* Projectile.java is essentially an object that takes 4 inputs, (xStart,yStart,xEnd,yEnd) to calculate a line on which the projectile will move.
* Projectiles.java (with an s at the end) is an array of projectiles. Currently, there are two Projectiles instances: one for the player, and one for circles.
* Enemy.java is the base class for Square.java, Circle.java, and Triangle.java
* Wave.java generates enemies

####Future implementations
* Color feature
* Leaderboard
* sprites
* updated UI
* music
* more enemies
