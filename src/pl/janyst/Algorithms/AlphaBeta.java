package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * Created by Piotr Janyst on 2017-05-09.
 */
public class AlphaBeta extends ArtificalIntelligence {

    public AlphaBeta(EvaluationFunction evaluationFunction, int depth) {
        this.evaluationFunction = evaluationFunction;
        this.depth = depth;
    }

    public int[] move() {
        int[] best = getStartMoves();
        if (best != null)
            return best;
        int[] result = alphabeta(depth, gomoku.getPlayer(), Integer.MIN_VALUE, Integer.MAX_VALUE); // depth, max turn, alpha, beta
        return new int[] {result[1], result[2]};   // row, col
    }

    private int[] alphabeta(int depth, int player, int alpha, int beta) {
        List<int[]> nextMoves = generateMoves();

        int score;
        int bestRow = -1;
        int bestColumn = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            score = evaluate();
            return new int[] {score, bestRow, bestColumn};
        }
        else {
            for (int[] move : nextMoves) {
                gomoku.setBoardElement(move[0], move[1], player);
                if (player == gomoku.getPlayer()) {
                    score = alphabeta(depth - 1, gomoku.getOpponent(), alpha, beta)[0];
                    System.out.println("score curr: " + score);
                    if (score > alpha) {
                        alpha = score;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                } else {
                    score = alphabeta(depth - 1, gomoku.getPlayer(), alpha, beta)[0];
                    System.out.println("score opp: " + score);
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

    public List<int[]> generateMoves() {
        int[][] board = gomoku.getBoard();
        List<int[]> moves = new ArrayList<>();
        int[] vertexes = gomoku.getVertexes();
        for (int row = vertexes[0]-2; row < vertexes[1]+2; row++)
            for (int column = vertexes[2]-2; column < vertexes[3]+2; column++)
                if (row < board.length && row >= 0 && column < board.length && column >= 0 && gomoku.getBoardElement(row, column) == 0)
                    moves.add(new int[] {row, column});
        return moves;
    }

    public int evaluate() {
        return evaluationFunction.evaluate(gomoku);
    }

}
