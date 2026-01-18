# Snake
A game of Snake made in Java with JavaFX. With the added features of jumping, powerups and multiplayer.
JavaFX is required for this program to run. The used version of JavaFX can be downloaded from this link: https://www.azul.com/core-post-download/?endpoint=zulu&uuid=d309c9af-e6f5-4175-a794-1ac6b8c564cc

# Run
When running the jar file jar file you can specify game size as parameters. If parameters are invalid or missing it defaults to saved or default values.
Example command to run the game with a board size of 20x10    
    java -jar Snake.jar 20 10

# Controls
Clicking jump makes it possible to jump over the body of snakes to avoid colliding, but also avoiding fruits. 
Up, Left, Down, Right, Jump
- Blue snake: UP LEFT DOWN RIGHT, MINUS
- Violet snake: W A S D, Q
- Orange snake: I, J, K, L, U
- Yellow snake: G, V, B, N, F

# Purple powerups
PowerUps have a chance to spawn after a fruit is eaten. Eating a powerUp grants one of three random temporary effects of varying benefit. 

# Settings
- Snakespeed: Variable that decides how many spaces the snakes moves per second.
- Playercount: When more than 1 player is selected, the goal of the game changes from eating the most fruit to surviving the longest. Multiplayer is for playing with other people.

