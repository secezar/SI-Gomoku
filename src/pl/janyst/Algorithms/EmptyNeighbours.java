package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Piotr Janyst on 2017-05-10.
 */
public class EmptyNeighbours implements EvaluationFunction {
    @Override
    public int evaluate(Gomoku gomoku) {
        int sum = 0;
        int boardLength = gomoku.getSize();
        for (int row = 0; row < boardLength; row++) {
            for (int column = 0; column < boardLength; column++) {
                ArrayList<ArrayList<Integer>> lineNeighbours = gomoku.getNeighbouring(row,column);
                for (ArrayList<Integer> neighbour: lineNeighbours) {
                    for (Integer aNeighbour : neighbour) {
                        if (aNeighbour == 0)
                            sum += 1;
                    }
                }
            }
        }
        return 0;
    }
}
