# README

## Requirements:

i) Must be connected to the internet
ii) Emulator must be Pixel2 emulator with the device's OS as Android 8.1 API 27

## Features:

i) Sign Up/Sign In/Sign Out:
The application has a sign up and sign in feature at the home screen, the user must sign up or sign in, in order to select games to play.
There is also a sign out feature which would sign the user out and return them back to the home screen. Note to do this
the user must be connected to the internet.

ii) Profile View:
The user can view their own profile this would includes the user's highest score for all the games they have played. The user
can also logout from their profile viewing screen.

iii) Game Selection:
The user has the option to choose which game they wish to play after signing up or signing in, the choices the user can make are
the three games: Sliding Tiles, Memory Game, or Mine Sweeper.

iv) Sliding Tiles:
The Sliding Tiles game is a game where the user must order the tiles in order from lowest to highest where the first tile in the top left would
be Tile #1 and the tile in the bottom right would be the Blank tile. The user can only select a tile which has a blank tile
adjacent to it in order to slide the tile, if the user selects a tile which doesn't have the blank tile adjacent to it, it will be
an invalid tap thus must try again. The game impliments autosave (described below) which automatically saves every 4 moves.

v) Memory Game:
The Memory Game is a game where the user must match all the pairs of tiles on the board to complete the game, the user does this by
selecting the first tile with a tap which would reveal an image then would try to find the same tile with that image on the second tap.
If the image on both tiles is the same both tiles will stay flipped then the user proceeds to continue this process until all tiles have been matched
If the image on the tiles are different both tiles will flip back to the blank state and the user would have to pick two tiles again.
The user can only tap tiles that are not flipped.

vi) Minesweeper:
Minesweeper is a classic game where the goal is to discover the location of all the mines without clicking on them. Clicking on a tile reveals a number
that tells you the number of mines in the adjacent tiles. You win the game when you have clicked on all tiles other than the ones that contain mines and
you lose if you click on a mine. The game impliments autosave (described below) which automatically saves every 4 moves.

vii) Game Complexity:
The application allows for the user to choose different board complexities depending on the game. Sliding Tiles provides three different complexities
a 3x3 board, 4x4 board, and a 5x5 board. The Memory Game also has three different complexities to choose from: 2x2 board, 4x4 board, and a 6x6
board. The Mine Sweeper game also has 3 different complexities: a 9x9 board, 16x16 board, and 20x20 board.

viii) Scores:
The application also has a score system to keep track of each users top 10 highest scores for each game, the user can view the top 10 highest
scores for each game in the game's start up screen, the user can also view their own top 10 highest scores in their profile.

ix) Saves:
The Game Centre allows for users to save and load their current games, and also has an autosave feature which will save the game's current state
every 4 moves.

## Setup:

1. The user must be connected to the internet to sign-up or sign-in
2. If the user already has an account they may sign-in directly which would lead to the options screen, they may select to view their
profile or start playing games. If the user doesn't have an account they must sign-up afterwards it would also redirect them to the option screen.
3. If the user selects the profile screen they may see their high scores or logout, if the user selects to start playing games they would
select a game from the three choices: Sliding Tiles, Memory Game, and Mine Sweeper.
4. After selecting the game the user can choose to load a previous saved game or start a new game, if the user selects to start a new game
they would then have a choice to select the complexity of the game.
5. After playing the game if the user wishes to save their current game they must go back and select the save game button. If the user finishes the game
they can choose to check out the highscore for that game or go back and select another game.

## FAQ:

Q1. How do I logout?
A1. The user must navigate to the profile screen where there is an option to logout
