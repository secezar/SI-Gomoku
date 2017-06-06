package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.HashSet;

/**
 * Created by Piotr Janyst on 2017-05-06.
 */
public interface EvaluationFunction {
    public int evaluate(Gomoku gomoku, HashSet<int[]> moves);
}
