package pl.janyst.Game;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * Created by Piotr Janyst on 2017-05-06.
 */
public class Gomoku {
    private int size;
    private int [][] board;
    private int player;
    private int moves;
    private int[] vertexes;

    public Gomoku() {
        this(15);
    }

    public Gomoku(int size) {
        this.size = size;
        this.board = new int[size][size];
        this.player = 1;
        this.moves = 0;
        this.vertexes = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
    }

    public int getSize() {
        return size;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getBoardElement(int row, int column) {
        return board[row][column];
    }

    public void setBoardElement(int row, int column, int element) {
        board[row][column] = element;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(int[] vertexes) {
        this.vertexes = vertexes;
    }

    public boolean makeMove(int row, int column) {
        boolean isMoved = false;
        System.out.println(row + " " + column);
        if (board[row][column] == 0) {
            board[row][column] = player;
            updateVertexes(row, column);
            moves++;
            isMoved = true;
        }
        return isMoved;
    }

    private void updateVertexes(int row, int column) {
        if (row < vertexes[0]) {
            System.out.println(String.format("Old vertex[0] = %d, new = %d", vertexes[0], row));
            vertexes[0] = row;
        }
        if (row > vertexes[1]) {
            System.out.println(String.format("Old vertex[1] = %d, new = %d", vertexes[1], row));
            vertexes[1] = row;
        }
        if (column < vertexes[2]) {
            System.out.println(String.format("Old vertex[2] = %d, new = %d", vertexes[2], column));
            vertexes[2] = column;
        }
        if (column > vertexes[3]) {
            System.out.println(String.format("Old vertex[3] = %d, new = %d", vertexes[3], column));
            vertexes[3] = column;
        }
        System.out.printf("***VERTEXES: (%d, %d), (%d, %d)***%n", vertexes[0], vertexes[1], vertexes[2], vertexes[3]);
    }

    public void switchPlayer() {
        player = getOpponent();
    }

    public int getOpponent() {
        return player == 1 ? 2 : 1;
    }

    public boolean isWin(int row, int column) {
        boolean result = isFiveNeighbouring(row, column);
        System.out.println("RESULT: " + result);
        return result;
    }

    public boolean isFiveNeighbouring(int row, int column) {
        ArrayList<ArrayList<Integer>> neighbours = getNeighbouring(row, column);
        boolean result = false;
        int neighbourList = 0;
        for (ArrayList<Integer> neighbour: neighbours) {
            if (neighbour.size() >= 5)
                for (int i = 0; i < neighbour.size()-4; i++) {
                    if (Objects.equals(neighbour.get(i), neighbour.get(i + 1)) && Objects.equals(neighbour.get(i), neighbour.get(i + 2))
                            && Objects.equals(neighbour.get(i), neighbour.get(i + 3)) && Objects.equals(neighbour.get(i), neighbour.get(i + 4))) {
                        result = true;
                    }
                }
            neighbourList++;
        }
        return result;
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

    public boolean isStart() {
        boolean result = false;
        for (int vertex : getVertexes()) {
            if (vertex > 15 | vertex < -15) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isInBoard(int row, int column) {
        return (row < getSize() && row >= 0 && column < getSize() && column >= 0);
    }

    public boolean isFilledElem(int row, int column) {
        return getBoardElement(row, column) != 0;
    }

    public HashSet<int[]> getFilledElems(){
    HashSet<int[]> filledElements = new HashSet<>();
    int[] vertexes = getVertexes();
        for (int row = vertexes[0]; row <= vertexes[1]; row++)
            for (int column = vertexes[2]; column <= vertexes[3]; column++)
                if (isFilledElem(row, column))
                    filledElements.add(new int[] {row, column});
        return filledElements;
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                System.out.printf("%d ", board[row][column]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public String ternaryBoard() {
        StringBuilder board_ternary = new StringBuilder();
        for (int[] aBoard : board)
            for (int column = 0; column < board.length; column++)
                board_ternary.append(aBoard[column]);
        return board_ternary.toString();
    }
}
