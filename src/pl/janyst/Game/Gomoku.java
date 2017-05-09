package pl.janyst.Game;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    public int[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(int[] vertexes) {
        this.vertexes = vertexes;
    }

    public void takeTurn(int row, int column) {
        boolean isMoved = makeMove(row, column);
        if(isWin(row, column)) {
            JOptionPane.showMessageDialog(null, "The player " + player + " win.");
            board = new int[size][size];
            this.player = 1;
            this.moves = 0;
            this.vertexes = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        else if(moves == size*size+1) {
            JOptionPane.showMessageDialog(null, "DRAW!");
            board = new int[size][size];
            this.player = 1;
            this.moves = 0;
            this.vertexes = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        if (isMoved)
            switchPlayer();
    }

    private boolean makeMove(int row, int column) {
        boolean isMoved = false;
        if (board[row][column] == 0) {
            board[row][column] = player;
            updateVertexes(row, column);
            moves++;
            isMoved = true;
        }
        return isMoved;
    }

    private void updateVertexes(int row, int column) {
        if (row < vertexes[0])
            vertexes[0] = row;
        else if (row > vertexes[1])
            vertexes[1] = row;
        if (column < vertexes[2])
            vertexes[2] = column;
        else if (column > vertexes[3])
            vertexes[3] = column;
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
            if (neighbourList == 0)
                System.out.println("Row neighbour!");
            else if (neighbourList == 1)
                System.out.println("Crossleft neighbour!");
            else if (neighbourList == 2)
                System.out.println("Column neighbour!");
            else if (neighbourList == 3)
                System.out.println("Crossright neighbour!");

            if (neighbour.size() >= 5)
                for (int i = 0; i < neighbour.size()-4; i++) {
                    System.out.println(neighbour.get(i) + " " + neighbour.get(i+1) + " " + neighbour.get(i+2) + " " + neighbour.get(i+3) + " " + neighbour.get(i+4));
                    System.out.println(Objects.equals(neighbour.get(i), neighbour.get(i + 1)) && Objects.equals(neighbour.get(i), neighbour.get(i + 2))
                            && Objects.equals(neighbour.get(i), neighbour.get(i + 3)) && Objects.equals(neighbour.get(i), neighbour.get(i + 4)));
                    if (Objects.equals(neighbour.get(i), neighbour.get(i + 1)) && Objects.equals(neighbour.get(i), neighbour.get(i + 2))
                            && Objects.equals(neighbour.get(i), neighbour.get(i + 3)) && Objects.equals(neighbour.get(i), neighbour.get(i + 4))) {
                        System.out.println("WIN");
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
}
