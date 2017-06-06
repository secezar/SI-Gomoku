package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * Created by Piotr Janyst on 2017-05-09.
 */
public class Simple implements EvaluationFunction {

    @Override
    public int evaluate(Gomoku gomoku, HashSet<int[]> moves) {
        int sum = 0;
        for (int[] move: moves) {
            int row = move[0];
            int column = move[1];
            ArrayList<ArrayList<Integer>> lineNeighbours = gomoku.getNeighbouring(row, column);
            for (ArrayList<Integer> neighbour : lineNeighbours) {
                if (neighbour.size() >= 5) {
                    for (int group = 5; group > 1; group--) {
                        for (int i = 0; i < neighbour.size() - 4; i++) {
                            boolean isGroup = true;
                            for (int j = 1; isGroup && j < group; j++) {
                                if (!Objects.equals(neighbour.get(i), neighbour.get(i + j)))
                                    isGroup = false;
                            }
                            if (isGroup && neighbour.get(i) != 0) {
                                if (Objects.equals(neighbour.get(i), gomoku.getPlayer()))
                                    sum += Math.pow(4, group);
                                else
                                    sum -= Math.pow(100, group);
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }
}
