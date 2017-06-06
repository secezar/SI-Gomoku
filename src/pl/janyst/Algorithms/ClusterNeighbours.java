package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.HashSet;

/**
 * Created by Piotr Janyst on 2017-05-09.
 */
public class ClusterNeighbours implements EvaluationFunction {
    @Override
    public int evaluate(Gomoku gomoku, HashSet<int[]> moves) {
        int sum = 1;
        int[][] board = gomoku.getBoard();
        int boardLength = board.length;
        for (int row = 0; row < boardLength; row++) {
            for (int column = 0; column < boardLength; column++) {
                if (gomoku.isFiveNeighbouring(row, column)) {
                    return 1000 * ((board[row][column] == gomoku.getPlayer()) ? 1 : -1);
                }
                if (board[row][column] != 0) {
                    for (int neighbourRow = row - 2; neighbourRow < row + 2; neighbourRow++) {
                        for (int neighbourColumn = column - 2; neighbourColumn < column + 2; neighbourColumn++) {
                            if (neighbourRow < board.length && neighbourRow >= 0 && neighbourColumn < board.length && neighbourColumn >= 0)
                                if (board[row][column] == gomoku.getPlayer())
                                    sum *= 4;
                                else if (board[row][column] == gomoku.getOpponent())
                                    sum -= 10;
                        }
                    }
                }
            }
        }
        return sum;
    }
}
