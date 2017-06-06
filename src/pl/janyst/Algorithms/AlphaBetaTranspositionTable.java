package pl.janyst.Algorithms;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Piotr Janyst on 2017-06-05.
 */
public class AlphaBetaTranspositionTable extends ArtificalIntelligence {
    HashMap<String, Integer> transpositionTable;

    public AlphaBetaTranspositionTable(EvaluationFunction evaluationFunction, int depth) {
        this.evaluationFunction = evaluationFunction;
        this.depth = depth;
        transpositionTable = new HashMap<>();
    }

    public int[] move() {
        int[] best = getStartMoves();
        if (best != null)
            return best;

        int[] result = alphabeta(depth, gomoku.getPlayer(), Integer.MIN_VALUE, Integer.MAX_VALUE); // depth, player, alpha, beta
        return new int[] {result[1], result[2]};   // row, col
    }

    private int[] alphabeta(int depth, int player, int alpha, int beta) {
        HashSet<int[]> nextMoves = generateMoves();

        int score;
        int bestRow = -1;
        int bestColumn = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            String transpositionTableKey = gomoku.ternaryBoard();
            Integer indexOfKey = transpositionTable.getOrDefault(transpositionTableKey, -1);
            if (indexOfKey == -1) {
                score = evaluate(gomoku.getFilledElems());
                transpositionTable.put(Integer.toString(bestRow) + Integer.toString(bestColumn), score);
                return new int[] {score, bestRow, bestColumn};
            }
            else
                return new int[] {transpositionTable.get(transpositionTableKey), bestRow, bestColumn};
        }
        else {
            for (int[] move : nextMoves) {
                gomoku.setBoardElement(move[0], move[1], player);
                if (player == gomoku.getPlayer()) {
                    score = alphabeta(depth - 1, gomoku.getOpponent(), alpha, beta)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                } else {
                    score = alphabeta(depth - 1, gomoku.getPlayer(), alpha, beta)[0];
                    if (score < beta) {
                        beta = score;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                }
                gomoku.setBoardElement(move[0], move[1], 0);
                if (alpha >= beta) break;
            }
        }
        return new int[] {(player == gomoku.getPlayer()) ? alpha : beta, bestRow, bestColumn};
    }
}
