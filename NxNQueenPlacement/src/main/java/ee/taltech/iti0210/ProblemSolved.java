package main.java.ee.taltech.iti0210;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ProblemSolved implements QueenProblemSolver {
    @Override
    public Board solve(InitialisationStrategy strategy) {
        boolean improved = true;
        int[] positions = strategy.getQueenPositions();
        Board newBoard = new Board(strategy);

        for (int row = 0; row < newBoard.getSize(); row++) {
            newBoard.addPosition(row, positions[row]);
        }

        ValueApproximator approximator = new ValueApproximator();
        int currentValue = approximator.value(positions, newBoard.getSize());

        while (improved && currentValue > 0) {
            improved = false;
            int bestValue = currentValue;
            int[] bestPositions = positions.clone();

            for (int row = 0; row < newBoard.getSize(); row++) {
                int originalCol = positions[row];

                // this loop will test whether an improvement was made
                for (int col = 0; col < newBoard.getSize(); col++) {
                    if (col == originalCol) continue;

                    positions[row] = col;
                    int newValue = approximator.value(positions, newBoard.getSize());

                    if (newValue < bestValue) {  // The aim is to minimize conflicts
                        bestValue = newValue;
                        // if the newValue is better than the old one, record it
                        bestPositions = positions.clone();
                        improved = true;
                    }
                }

                positions[row] = originalCol;  // Restore original column
            }

            if (improved) {
                positions = bestPositions.clone();
                currentValue = bestValue;

                // Update the board with the new positions
                newBoard.clear();  // Clear the board before updating positions
                for (int row = 0; row < newBoard.getSize(); row++) {
                    newBoard.addPosition(row, positions[row]);
                }
            }
        }


        return newBoard;
    }
}
