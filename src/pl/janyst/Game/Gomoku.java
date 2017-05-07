package pl.janyst.Game;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Piotr Janyst on 2017-05-06.
 */
public class Gomoku {
    int size;
    int [][] board;
    int player;

    public Gomoku(int size) {
        this.size = size;
        this.board = new int[size][size];
        this.player = 1;
    }

    public void takeTurn(int row, int column) {
        makeMove(row, column);
        isWin(row, column);
        switchPlayer();
    }

    private void makeMove(int row, int column) {
        if (board[row][column] == 0) {
            board[row][column] = player;
        }
    }

    private void switchPlayer() {
        player = (player == 1) ? 2 : 1;
    }

    private boolean isWin(int row, int column) {
        return isFiveNeighbouring(row, column);
    }

    public boolean isFiveNeighbouring(int row, int column) {
        getNeighbouring(row, column);
        return true;
    }

    public ArrayList<int[]> getNeighbouring(int row, int column) {

        ArrayList<int[]> neighbours = new ArrayList<>();
        int[] leftCrossNeighbouring = new int[row < 4 || row > size-4 || column < 4 || column > size-4 ? 5+(row > column ? column : row) : 9];
        int[] rightCrossNeighbouring = new int[row < 4 || row > size-4 || column < 4 || column > size-4 ? 5+(row > column ? column : row) : 9];
        int[] rowNeighbouring = Arrays.copyOfRange(board[row], column < 4 ? 0 : column-4, column > size-4 ? size : column+5);
        int[] columnNeighbouring = Arrays.copyOfRange(getColumn(column), column < 4 ? 0 : column-4, column > size-4 ? size : column+5);

//        if (column < 4) {
//            for (int i = row >= 4 ? -column : column > row ? -row : -column; row <= size-4 ? i < 5 : i < size-row; i++) {
//                leftCrossNeighbouring[i] = board[row+i][column+i];
//                rightCrossNeighbouring[i] = board[row+i][column-i];
//            }
//        }
//        else if (column >=4 && column <= size-4) {
//            for (int i = row >= 4 ? -4 : -row; row <= size-4 ? i < 5 : i < size-row; i++) {
//                leftCrossNeighbouring[i] = board[row+i][column+i];
//                rightCrossNeighbouring[i] = board[row+i][column-i];
//            }
//        }
//        else if (column > size-4) {
//            for (int i = row >= 4 ? -4 : -row; row <= size-4 ? i < size-column : column > row ? i < size-row : i < size-column; i++) {
//                leftCrossNeighbouring[i] = board[row+i][column+i];
//                rightCrossNeighbouring[i] = board[row+i][column-i];
//            }
//        }
        neighbours.add(rowNeighbouring);
        neighbours.add(leftCrossNeighbouring);
        neighbours.add(columnNeighbouring);
        neighbours.add(rightCrossNeighbouring);
        return neighbours;
    }

    private int[] getColumn(int column) {
        int[] boardColumn = new int[size];
        for (int i = 0; i < size; i++) {
            boardColumn[i] = board[i][column];
        }
        return boardColumn;
    }
}
