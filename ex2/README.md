# ITI0210 Homework 2: Hill climbing

This repository contains a template to aid you in solving this homework.

Please read through this whole file before you start solving as there are quite many hints on how you can solve this that may change how you would approach this problem.
You don't have to implement everything described in here, as long as you use some hill climber to solve required boards from `4x4` to `8x8` and provide a report about your results you will have done the required part.

## `InitialisationStrategy` and `StaticInitialisationStrategy`

`InitialisationStrategy` is a base class for board initialisation strategies with `StaticInitialisationStrategy` being one simple example.

You probably don't want to use `StaticInitialisationStrategy` in your solvers, think of it as a debugging strategy in case you want to visualise some configuration of queens (used in `SolutionVerifier.visualiseSolution` that you can just call).

For your own strategies you probably want to implement some form of random strategy that will give you different configurations on each call.
Your solver can then use that strategy to restart if you choose to do that.
Feel free to introduce your own restrictions to your strategies, as there are better and worse ways to generate starting positions.
One good example is to note that in the final solution no queen will occupy same row AND column so you can make a strategy that takes this into account.

## `Board`

This class is supposed to be representation of the playing board.
Currently it's implemented in that only the queens' positions are stored.

You don't have to store them this way, and feel free to use whatever option feels more natural to you.
Do mind that some representations are better than others, however.

You could store them all in a 2D (or 1D) list of booleans that are set if the queen is (or isn't) in that position and opposite otherwise.
Problem you will run into with this representation is that you need to query for queens' position quite often, so it might become cumbersome and slow to do it.

Another option you have is to not that at the end of the day each queen will have to occupy her own row (and column) so you can take advantage of that and only store a 1D list of integers, where each _position_ in the list represents a row and each _value_ represents a column. (or you can swap columns/rows, it works both ways)

## `SolutionVerifier`

This is a helper class that verifies whether given board was solved correctly.

It was not optimized for your solvers (and not really intended to be used there), but you can use it after getting an answer from your solver to make sure that you indeed solved it correctly.

## Tests

There are some tests included in this repository, you don't have to look at them and if you want to feel free to delete them.

There is also no main method, so if you want to test manually feel free to add one yourself.

## `QueenProblemSolver`

This is the main interface that you are supposed to override and put your solution into.

Before you do though, you will want to write a class to evaluate a given set of queen positions (you can take in whatever way is more convenient, be it a set of positions, entire board, or something else).
This evaluator should return you some number that represents how "good" a board is, there should also ideally be some number that hill climber "strives" towards and represents a solved board.
One way to evaluate a board is to count the amount of conflicts, then `0` is the number on which you can deduce that you no longer have any problems and can quit early.

Some examples:
* Count how many queens can each queen capture.
* Count how many extra queens are in each row, column, diagonals. (you can take base code from SolutionVerifier and make it instead return a number of EXTRA queens from each of the calls)

Since this will be called quite often, you might want to try to keep track of this number in your board and update it on each change.
If that feels difficult you can implement a separate counter as described above, it will be fast enough for smaller boards.

Rough outline for hill climbing algorithm is as follows:
```
1. Get a starting state to solve from. (this can be some preset, like all in same row/column/diagonal, or completely random)
2. Create a variable where you will store your best solution so far, initialise it to what you started with.
3. While the problem is not solved (and some amount of consequtive tries to improve solutions is not exceeded):
	1. Calculate what's the current score of the solution.
	2. Alter the solution slightly (e.g pick a random queen and move her to some neighbouring cell)
	3. Check if the solution has improved.
	4. If it did, keep the move, otherwise revert the move.
	5. Check against your best solution and update accordingly.
4. Report your best solution.
```

If you implement it as required, and give it enough time (and tries) to run, it should produce you a solution.
However, it heavily depends on randomness.

Here are some ways you can improve.

### Restarting

Instead of trying to solve a board once (and give it a ton of tries) you can instead try to solve a few boards with fewer tries for each.
This works because of the local extrema - your hill climber might get into a situation where it might be hard (or even impossible) to improve solution by small changes, but if you keep on restarting you give it more chances to avoid such situations.

Rough outline:
```
1. Initialise a board (works best with random)
2. Create a variable where you will store your best solution so far, initialise it to what you started with.
3. While the board is not solved (and some amount of tries is not exceeded):
	1. Solve the board with normal hill climber.
	2. Update your best solution accordingly.
4. Return your best solution.
```

### Change moves you are optimizing

Some changes to solutions are better than others.

For example, you might have a queen that is not conflicting with any other and one that conflicts with a lot.
If you just keep picking randomly you might pick queens that have fewer (or none) conflicts and moving them is then likely to NOT improve your situation.
So, you could try to keep track of how many conflicts each queen has and give more priority to those that have more conflicts.
That's not to say that you should pick the most conflicting one all the time (though you can try that strategy too), but instead just give it more chance to be chosen.

Another thing you can do is note that at the end of the day each queen will have to be at her own row AND column.
You can take advantage of this by requiring that board you get already fulfils this requirement and just swap rows (or columns).
This will reduce your search space significantly.

### Simulated Annealing

SA tends to be quite fast when solving bigger problems because it is very likely to avoid local extrema.
The way you make your hill climber into simulated annealing is to change how you pick whether you keep your change or not.

In standard hill climber you always pick the better change and always reject the worse one.
In SA instead of always rejecting the worse move you introduce a probability (also called temperature) that depends on time.
At first it basically accepts everything, but as time goes on it becomes pickier and pickier until either the board is solved, or it becomes a normal hill climber and exceeds the amount of tries.

There are many ways to calculate probability, one way is to use the following formula:
```
e^[(old_cost - new_cost) / current_temperature]
```
This will return you a float value in `[0, 1]`, so all you need to do is check whether the number returned by this formula is greater than some random number in the same interval.

Formula for changing temperature can also be anything, but one that works reasonably well for this homework is a geometric progression like this:
```
current_temperature *= cooling_factor
```
Where `cooling_factor` is something like `0.9`, `0.99`, `0.999`, etc.

Rough outline:
```
1. Get a starting state to solve from. (this can be some preset, like all in same row/column/diagonal, or completely random)
2. Create a variable where you will store your best solution so far, initialise it to what you started with.
3. While the problem is not solved (and some amount of consequtive tries to improve solutions is not exceeded):
	1. Calculate what's the current score of the solution.
	2. Alter the solution slightly (e.g pick a random queen and move her to some neighbouring cell)
	3. Check if the solution has improved.
	4. If it did, keep the move, otherwise roll the probability check on whether to revert the move.
	5. Check against your best solution and update accordingly.
	6. Lower the temperature.
4. Report your best solution.
```
