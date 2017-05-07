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
        ArrayList<ArrayList<Integer>> neighbours = getNeighbouring(row, column);
        boolean result = false;
        for (ArrayList<Integer> neighbour: neighbours) {
            for (int i = 0; i < neighbour.size()-5; i++) {
                int sum = neighbour.get(i) + neighbour.get(i+1) + neighbour.get(i+2) + neighbour.get(i+3) + neighbour.get(i+4);
                if (sum == 5 || sum == 10) {
                    result = true;
                }
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Integer>> getNeighbouring(int row, int column) {

        ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>();

        ArrayList<Integer> leftCrossNeighbouring = new ArrayList<>();
        ArrayList<Integer> rightCrossNeighbouring = new ArrayList<>();
        ArrayList<Integer> rowNeighbouring = new ArrayList<>();
        ArrayList<Integer> columnNeighbouring = new ArrayList<>();


        for (int i = -4; i < 5; i++) {
            try {
                leftCrossNeighbouring.add(board[row+i][column+i]);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        for (int i = -4; i < 5; i++) {
            try {
                rightCrossNeighbouring.add(board[row+i][column-i]);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        for (int i = -4; i < 5; i++) {
            try {
        rowNeighbouring.add(board[row][column+i]);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        for (int i = -4; i < 5; i++) {
            try {
        columnNeighbouring.add(board[row+i][column]);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
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
