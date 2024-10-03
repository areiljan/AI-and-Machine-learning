# NxN queen placement hillclimber exercise
The hill-climbing algorithm starts with an initial configuration of queens on the board and iteratively moves queens to reduce the number of conflicts. The algorithm aims to reach a state with zero conflicts, representing a valid solution to the N-Queens problem. I changed the initial configurations around quite a bit. 

### Changes in structure
I changed the board to a 1D array, not ever using the Position object. The most important part of the hillclimber algorithm is to get the value approximator right. I got the value setter to take in a 1d array of integers and tell me how many conflicts there are, as was recommended in the README that was in the .zip file. Then, after many changes were made to the Board and Strategy classes, I made a RandomInitialisationStrategy that took in the board size and how many queens need to be placed. It then placed all of these queens in random places. Note that because I used a 1D array, conflicts could only arise in 2 dimensions, not 3 (if I got a conflict in a row it would be replaced and recalculated).

### Solving the problem
Then the solving part. I created a ProblemSolved class that implements the QueenProblemSolver. Quickly summarized, the loop will try a random change in one of the positions on the column, if the row memory address is empty, it will simply add. Then it will check whether an improvement in value is seen. The lower the better, because I am using the conflict amount as reference. And voila! This worked for me. Solving 4x4 to 8x8 boards is easy with this setup, correctly doing it every time. If I go beyond 20x20 boards, then after a couple of rolls I might see one conflict. This might be because the approximator is not perfect or some bug in the solver.

