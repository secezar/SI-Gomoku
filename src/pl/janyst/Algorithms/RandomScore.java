package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by Piotr Janyst on 2017-05-09.
 */
public class RandomScore implements EvaluationFunction {
    @Override
    public int evaluate(Gomoku gomoku, HashSet<int[]> moves) {
        Random random = new Random();
        int score = random.nextInt((int) Math.pow(4, 4));
        return score;
    }
}
