# Monte Carlo game solving exercise
This exercise was about solving the game Connect Four with Monte Carlo method. Monte Carlo generates a number of random games and chooses the move that led to the highest number of points. 

### Solving the problem
I created the IntelligentPlayer class that acts as the AI agent in this case. IntelligentPlayer takes the amount of games to simulate as the input. decideTurn takes the board as input and then simulates the amount of games for each variation. The game simulation plays out totally randomly, taking into account the board that already exists. I have applied no pruning. The game is simple enough that computation could theoretically totally solve the game. The simulateRandomGame is called out on iteration of a loop in decideTurn, the simulateRandomGame takes the current board as input. Finally the moves are valued by the selectBestMove method that looks at the stored bestMoves list and takes the best outcome. The ties are broken randomly for even more randomness.

The game is playable. I applied time calculations for the problem using 1000 gamesToPlay. The time to solve one turn was average of about 10ms.
1000 gamesToPlay was also the amount of games after which i did not find any improvement in the skill of the bot.

Even knowing combinatorics I was suprised to learn that the game Connect Four has 4.5 trillion different states. This is still a calculation that can be brute forced by todays computers, but a lot more than I anticipated. This means that 1000 calculations is clearly not enough.
I tried the game with 1 million gamesToPlay, I did not see any improvement, but the time was 71000ms.