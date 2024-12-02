package main.java.ee.taltech.iti0210;

import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        int boardSize = 20;
        int queenCount = boardSize;  // Number of queens to place on the board

        InitialisationStrategy strategy = new RandomInitialisationStrategy(boardSize, queenCount);
        QueenProblemSolver solver = new ProblemSolved();

        Instant start = Instant.now();

        Board solvedBoard = solver.solve(strategy);

        Instant end = Instant.now();

        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time: " + timeElapsed.toMillis() + " milliseconds");

        System.out.println("Final board configuration: ");
        int[] positions = solvedBoard.getPositions();
        for (int row = 0; row < positions.length; row++) {
            System.out.println("Row " + row + ": Queen at column " + positions[row]);
        }
    }
}
