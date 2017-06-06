package pl.janyst.Algorithms;

import pl.janyst.Game.Gomoku;

import java.util.HashSet;

/**
 * Created by Piotr Janyst on 2017-05-20.
 */
public class MoveHeuristicCombined implements EvaluationFunction {
    private int simpleWeight;
    private int clusterWeight;
    private int neighboursWeight;

    public MoveHeuristicCombined(int simpleWeight, int clusterWeight, int neighboursWeight) {
        this.simpleWeight = simpleWeight;
        this.clusterWeight = clusterWeight;
        this.neighboursWeight = neighboursWeight;
    }

    @Override
    public int evaluate(Gomoku gomoku, HashSet<int[]> moves) {
        return simpleWeight * new Simple().evaluate(gomoku, moves) + clusterWeight * new ClusterNeighbours().evaluate(gomoku, moves)
                + neighboursWeight * new RandomScore().evaluate(gomoku, moves);
    }
}
