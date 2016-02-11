# Color_Wars
A Java Application that runs an exciting in game in which the user controls a black ship and must navigate through enemies and destroy them with the ship's blasters

##CS48 Project for W16
Domenic Dipeppe, Andrew Huang, Jackey Lau, Alex Thielk

##User View
####Main Menu
The user will be brought to a screen that will direct them to either the leaderboard or game

####Leaderboard
* a work in process (unimplemented)

####Game
The user controls the black triangle and must take down enemies by shooting them. The player can die and then lose should the player's HP reach 0. The player's HP is decremented by one for every enemy collision (including enemy projectiles)

#####Player
* Takes WASD for movement
* Shoots projectiles at the mouse location

#####Enemies
* Triangle Enemy - Kamikaze's straight into the player
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
* Projectile.java handles the projectiles, essentially an object is created that takes 4 inputs, (x,y) for where the projectile starts and ends
* Enemy.java is the base class for Square.java, Circle.java, and Triangle.java
* Wave.java generates enemies
* 
####Future implementations
* Color feature
* Leaderboard
* sprites
* updated UI
* music
* more enemies
