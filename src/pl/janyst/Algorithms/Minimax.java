package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.*;

/**
 * Created by Piotr Janyst on 2017-05-06.
 */
public class Minimax extends ArtificalIntelligence {

    public Minimax(EvaluationFunction evaluationFunction, int depth) {
        this.evaluationFunction = evaluationFunction;
        this.depth = depth;
    }

    public int[] move() {
        int[] best = getStartMoves();
        if (best != null)
            return best;
        int[] result = minimax(depth, gomoku.getPlayer());
        return new int[] {result[1], result[2]};
    }

    private int[] minimax(int depth, int player) {
        HashSet<int[]> nextMoves = generateMoves();

        int bestScore = (player == gomoku.getPlayer()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestColumn = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            bestScore = evaluate(gomoku.getFilledElems());
        } else {
            for (int[] move : nextMoves) {
                gomoku.setBoardElement(move[0], move[1], player);
                if (player == gomoku.getPlayer()) {
                    currentScore = minimax(depth - 1, gomoku.getOpponent())[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                } else {
                    currentScore = minimax(depth - 1, gomoku.getPlayer())[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                }
                // Undo
                gomoku.setBoardElement(move[0], move[1], 0);
            }
        }
        return new int[] {bestScore, bestRow, bestColumn};
    }
}
