package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr Janyst on 2017-05-09.
 */
public class AlphaBeta {
    EvaluationFunction evaluationFunction;
    Gomoku gomoku;
    int depth;

    public AlphaBeta(EvaluationFunction evaluationFunction, Gomoku gomoku, int depth) {
        this.evaluationFunction = evaluationFunction;
        this.gomoku = gomoku;
        this.depth = depth;
    }

    public int[] move() {
        if (gomoku.getBoardElement(7,7) == 0)
            return new int[] {7, 7};
        int[] result = alphabeta(depth, 1, Integer.MIN_VALUE, Integer.MAX_VALUE); // depth, max turn, alpha, beta
        return new int[] {result[1], result[2]};   // row, col
    }

    private int[] alphabeta(int depth, int player, int alpha, int beta) {
        // Generate possible next moves in a List of int[2] of {row, col}.
        List<int[]> nextMoves = generateMoves();

        // 1 is maximizing; while 2 is minimizing
        int score;
        int bestRow = -1;
        int bestColumn = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            // Gameover or depth reached, evaluate score
            score = evaluate();
        } else {
            for (int[] move : nextMoves) {
                // Try this move for the current "player"
                gomoku.setBoardElement(move[0], move[1], player);
                if (player == 1) {  // 1 is maximizing player
                    score = alphabeta(depth - 1, 2, alpha, beta)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                } else {  // 2 is minimizing player
                    score = alphabeta(depth - 1, 1, alpha, beta)[0];
                    if (score < beta) {
                        beta = score;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                }
                // Undo
                gomoku.setBoardElement(move[0], move[1], 0);
                // Cut-off
                if (alpha >= beta) break;
            }
        }
        return new int[] {(player == 1) ? alpha : beta, bestRow, bestColumn};
    }

    public List<int[]> generateMoves() {
        int[][] board = gomoku.getBoard();
        List<int[]> moves = new ArrayList<>();
        int[] vertexes = gomoku.getVertexes();
        for (int row = vertexes[0]-2; row < vertexes[1]+2; row++)
            for (int column = vertexes[2]-2; column < vertexes[3]+2; column++)
                if (row < board.length && row >= 0 && column < board.length && column >= 0)
                    moves.add(new int[] {row, column});
        return moves;
    }

    public int evaluate() {
        return evaluationFunction.evaluate(gomoku);
    }

}
