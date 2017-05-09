package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

/**
 * Created by Piotr Janyst on 2017-05-09.
 */
public class ClusterNeighbours implements EvaluationFunction {
    @Override
    public int evaluate(Gomoku gomoku) {
        int sum = 0;
        int[][] board = gomoku.getBoard();
        int boardLength = board.length;
        for (int row = 0; row < boardLength; row++) {
            for (int column = 0; column < boardLength; column++) {
                for (int neighbourRow = row - 2; neighbourRow < row + 2; neighbourRow++) {
                    for (int neighbourColumn = column - 2; neighbourColumn < column + 2; neighbourColumn++) {
                        if (row < board.length && row >= 0 && column < board.length && column >= 0)
                            if (board[row][column] == gomoku.getPlayer())
                                sum += 4;
                            else if (board[row][column] == gomoku.getOpponent())
                                sum -= 4;
                            else
                                sum += 1;
                    }
                }
            }
        }
        return sum;
    }
}
