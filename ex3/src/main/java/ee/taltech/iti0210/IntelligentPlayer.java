package main.java.ee.taltech.iti0210;

import main.java.ee.taltech.iti0210.Board;
import main.java.ee.taltech.iti0210.Player;

import java.util.*;

public class IntelligentPlayer extends Player {
    private int gamesToPlay;
    private Random random;

    public IntelligentPlayer(Integer gamesToPlay) {
        this.gamesToPlay = gamesToPlay;
        this.random = new Random();
    }

    @Override
    public int decideTurn(Board b) {
        Set<Integer> availableColumns = b.getAvailableColumns();
        Map<Integer, Integer> moveScores = new HashMap<>();

        // keep count of the score for each possible variation
        for (int move : availableColumns) {
            moveScores.put(move, 0);
        }

        // For each possible move, simulate games starting with that move
        for (int move : availableColumns) {
            int totalScore = 0;

            for (int i = 0; i < gamesToPlay; i++) {
                Board boardCopy = new Board(b);
                // Make the initial move
                boardCopy.doTurn(getCellType(), move);

                // Simulate the rest of the game
                Cell result = simulateRandomGame(boardCopy);

                // Assign scores based on the outcome
                if (result == getCellType()) {
                    // Win
                    totalScore += 2;
                } else if (result == Cell.EMPTY) {
                    // Tie
                    totalScore += 1;
                } else {
                    // Loss
                    totalScore += 0;
                }
            }

            // Store the total score for this move
            moveScores.put(move, totalScore);
        }

        int bestMove = selectBestMove(moveScores);

        return bestMove;
    }

    private Cell simulateRandomGame(Board board) {
        Cell currentPlayer = getOpponentCellType(getCellType());

        // Only continue the game if these conditions are not met
        while (!board.isFull() && board.getWinner().orElse(Cell.EMPTY) == Cell.EMPTY) {
            List<Integer> availableColumns = new ArrayList<>(board.getAvailableColumns());

            if (availableColumns.isEmpty()) {
                break;
            }

            int randomColumn = availableColumns.get(random.nextInt(availableColumns.size()));

            board.doTurn(currentPlayer, randomColumn);

            currentPlayer = getOpponentCellType(currentPlayer);
        }

        Cell winner = board.getWinner().orElse(Cell.EMPTY);
        return winner;
    }

    private Cell getOpponentCellType(Cell cell) {
        if (cell == Cell.P1) {
            return Cell.P2;
        } else if (cell == Cell.P2) {
            return Cell.P1;
        } else {
            return Cell.EMPTY;
        }
    }

    private int selectBestMove(Map<Integer, Integer> moveScores) {
        int bestScore = Integer.MIN_VALUE;
        List<Integer> bestMoves = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : moveScores.entrySet()) {
            int move = entry.getKey();
            int score = entry.getValue();

            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }

        // Break ties randomly
        return bestMoves.get(random.nextInt(bestMoves.size()));
    }
}
