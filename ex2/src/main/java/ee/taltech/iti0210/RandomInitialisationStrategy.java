package main.java.ee.taltech.iti0210;

import main.java.ee.taltech.iti0210.InitialisationStrategy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A random initialisation strategy for placing queens on the board.
 */
public class RandomInitialisationStrategy extends InitialisationStrategy {

    private Random random;

    /**
     * Constructor for the random initialisation strategy.
     *
     * @param size       size of the board (length of one side)
     * @param queenCount number of queens to generate.
     */
    public RandomInitialisationStrategy(int size, int queenCount) {
        super(size, queenCount);
        this.random = new Random();
    }

    /**
     * @return a Set of positions where the queens are placed.
     * The queens are placed at random, unique positions on the board.
     */
    @Override
    public int[] getQueenPositions() {
        int[] board = new int[getSize()];

        Set<Integer> filledRows = new HashSet<>();

        while (filledRows.size() < getQueenCount()) {
            int row = random.nextInt(getSize()); // Random row within [0, size)
            int col = random.nextInt(getSize()); // Random column within [0, size)

            if (!filledRows.contains(row)) {
                board[row] = col;
                filledRows.add(row);
            }
        }

        return board;  // Return the array with column positions for each row
    }
}
