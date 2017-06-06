package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Jola on 2017-05-16.
 */
public abstract class ArtificalIntelligence {
    EvaluationFunction evaluationFunction;
    int depth;
    Gomoku gomoku = null;

    public abstract int[] move();

    public void setGomoku(Gomoku gomoku) {
        this.gomoku = gomoku;
    }

    public int evaluate(HashSet<int[]> moves) {
        return evaluationFunction.evaluate(gomoku, moves);
    }

    public int[] getStartMoves() {
        if (gomoku.isStart()) {
            if (gomoku.getBoardElement(7,7) == 0)
                return new int[] {7, 7};
            else {
                Random random = new Random();
                int[][] moves = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
                int[] best = Arrays.stream(moves).max(Comparator.comparing(elem -> random.nextInt(100))).get();
                return new int[] {7 + best[0], 7 + best[1]};
            }
        }
        return null;
    }

    public HashSet<int[]> generateMoves() {
        HashSet<int[]> moves = new HashSet<>();
        int[][] relativeMoves = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1},
                {-2, 0}, {-2, 2}, {0, 2}, {2, 2}, {2, 0}, {2, -2}, {0, -2}, {-2, -2}};
        for (int[] filledElem : gomoku.getFilledElems())
            for (int[] move : relativeMoves) {
                int rowNeighbour = filledElem[0] + move[0];
                int columnNeighbour = filledElem[1] + move[1];
                if (gomoku.isInBoard(rowNeighbour, columnNeighbour))
                    if (!gomoku.isFilledElem(rowNeighbour, columnNeighbour))
                        moves.add(new int[] {rowNeighbour, columnNeighbour});
            }
        return moves;
    }
}
