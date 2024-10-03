package main.java.ee.taltech.iti0210;

import java.util.HashSet;
import java.util.Set;

public class ValueApproximator {
    public int value(int[] positions, int boardSize) {
        int conflicts = 0;

        for (int i = 0; i < boardSize; i++) {
            for (int j = i + 1; j < boardSize; j++) {
                if (positions[i] == positions[j]) {
                    // Queens are in the same column
                    conflicts++;
                } else if (Math.abs(positions[i] - positions[j]) == Math.abs(i - j)) {
                    // Queens are on the same diagonal
                    conflicts++;
                }
            }
        }

        return conflicts;
    }
}
