package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

/**
 * Created by Piotr Janyst on 2017-05-06.
 */
public interface EvaluationFunction {
    public int evaluate(Gomoku gomoku);
}
