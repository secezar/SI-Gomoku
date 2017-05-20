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
        int[] result = minimax(depth, gomoku.getPlayer()); // depth, max turn
        return new int[] {result[1], result[2]};   // row, col
    }

    private int[] minimax(int depth, int player) {
        // Generate possible next moves in a List of int[2] of {row, col}.
        List<int[]> nextMoves = generateMoves();

        // mySeed is maximizing; while oppSeed is minimizing
        int bestScore = (player == gomoku.getPlayer()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestColumn = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            // Gameover or depth reached, evaluate score
            bestScore = evaluate();
        } else {
            for (int[] move : nextMoves) {
                // Try this move for the current "player"
                gomoku.setBoardElement(move[0], move[1], player);
                if (player == gomoku.getPlayer()) {  // mySeed (computer) is maximizing player
                    currentScore = minimax(depth - 1, gomoku.getOpponent())[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                } else {  // oppSeed is minimizing player
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

    public List<int[]> generateMoves() {
        int[][] board = gomoku.getBoard();
        List<int[]> moves = new ArrayList<>();
        int[] vertexes = gomoku.getVertexes();
        for (int row = vertexes[0]-2; row < vertexes[1]+2; row++)
            for (int column = vertexes[2]-2; column < vertexes[3]+2; column++)
                if (row < board.length && row >= 0 && column < board.length && column >= 0 && gomoku.getBoardElement(row,column) == 0)
                    moves.add(new int[] {row, column});
        return moves;
    }

    public int evaluate() {
        return evaluationFunction.evaluate(gomoku);
    }

}
