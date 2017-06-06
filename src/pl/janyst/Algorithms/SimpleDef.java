package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * Created by Piotr Janyst on 2017-05-09.
 */
public class SimpleDef implements EvaluationFunction {

    @Override
    public int evaluate(Gomoku gomoku, HashSet<int[]> moves) {
        int sum = 0;
        for (int[] move: moves) {
            int row = move[0];
            int column = move[1];
            ArrayList<ArrayList<Integer>> lineNeighbours = gomoku.getNeighbouring(row, column);
            for (ArrayList<Integer> neighbours: lineNeighbours) {
                if (neighbours.size() >= 5) {
                    for (int group = 5; group > 1; group--) {
                        for (int i = 0; i < neighbours.size() - (group - 1); i++) {
                            boolean isGroup = isGroup(neighbours, group, i);
                            if (isGroup && neighbours.get(i) != 0) {
                                boolean isCurrentPlayer = neighbours.get(i) == gomoku.getPlayer();
                                if (group == 5) {
                                    return isCurrentPlayer ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                                }
                                sum += isCurrentPlayer ? Math.pow(4, group) : -1*Math.pow(100, group);
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    private boolean isGroup(ArrayList<Integer> neighbours, int group, int i) {
        boolean isGroup = true;
        boolean oneEmptyFieldAllowed = true;
        for (int j = 0; isGroup && j < group; j++) {
            if (!Objects.equals(neighbours.get(i), neighbours.get(i + j)) | (neighbours.get(i) == 0 & !oneEmptyFieldAllowed))
                isGroup = false;
            else if (neighbours.get(i) == 0) {
                oneEmptyFieldAllowed = false;
            }
        }
        return isGroup;
    }
}

