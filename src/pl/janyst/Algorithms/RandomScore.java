package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.Random;

/**
 * Created by Piotr Janyst on 2017-05-09.
 */
public class RandomScore implements EvaluationFunction {
    @Override
    public int evaluate(Gomoku gomoku) {
        Random random = new Random();
        int score = random.nextInt(4^4);
        return score;
    }
}
