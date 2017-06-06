package pl.janyst.Algorithms;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Piotr Janyst on 2017-06-05.
 */
public class AlphaBetaStaticOrdering extends ArtificalIntelligence {

    public AlphaBetaStaticOrdering(EvaluationFunction evaluationFunction, int depth) {
        this.evaluationFunction = evaluationFunction;
        this.depth = depth;
    }

    public int[] move() {
        int[] best = getStartMoves();
        if (best != null)
            return best;
        int[] result = alphabeta(depth, gomoku.getPlayer(), Integer.MIN_VALUE, Integer.MAX_VALUE); // depth, max turn, alpha, beta
        return new int[] {result[1], result[2]};   // row, col
    }

    private int[] alphabeta(int depth, int player, int alpha, int beta) {
        HashSet<int[]> nextMoves = generateMoves();
        ArrayList<int[]> sortedMoves = sort(nextMoves);
        int score;
        int bestRow = -1;
        int bestColumn = -1;

        if (sortedMoves.isEmpty() || depth == 0) {
            score = evaluate(gomoku.getFilledElems());
            return new int[] {score, bestRow, bestColumn};
        }
        else {
            for (int[] move : sortedMoves) {
                gomoku.setBoardElement(move[0], move[1], player);
                if (player == gomoku.getPlayer()) {
                    score = alphabeta(depth - 1, gomoku.getOpponent(), alpha, beta)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                } else {
                    score = alphabeta(depth - 1, gomoku.getPlayer(), alpha, beta)[0];
                    if (score < beta) {
                        beta = score;
                        bestRow = move[0];
                        bestColumn = move[1];
                    }
                }
                gomoku.setBoardElement(move[0], move[1], 0);
                if (alpha >= beta) break;
            }
        }
        return new int[] {(player == gomoku.getPlayer()) ? alpha : beta, bestRow, bestColumn};
    }

    private ArrayList<int[]> sort(HashSet<int[]> moves) {
        ArrayList<int[]> arrayMoves = new ArrayList<>(moves);
        ArrayList<Integer> lines = calculateLines(arrayMoves);
        ArrayList<int[]> unsorted = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            unsorted.add(new int[] {arrayMoves.get(i)[0], arrayMoves.get(i)[1], lines.get(i)});
        }
        return unsorted.stream().sorted(Comparator.comparingInt(x -> x[2])).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Integer> calculateLines(ArrayList<int[]> moves) {
        ArrayList<Integer> lines = new ArrayList<>();
        for (int[] move : moves) {
            int countLines = getCountLines(move);
            lines.add(countLines);
        }
        return lines;
    }

    private int getCountLines(int[] move) {
        int countLines = 0;
        ArrayList<ArrayList<Integer>> neighbouring = getNeighbours(move[0], move[1]);
        for (ArrayList<Integer> neighbours: neighbouring) {
            countLines += countLines(neighbours);
        }
        return countLines;
    }

    private ArrayList<ArrayList<Integer>> getNeighbours(int row, int column) {
        gomoku.setBoardElement(row, column, gomoku.getPlayer());
        ArrayList<ArrayList<Integer>> neighbouring = gomoku.getNeighbouring(row, column);
        gomoku.setBoardElement(row, column, 0);
        return neighbouring;
    }

    private int countLines(ArrayList<Integer> neighbours) {
        int lines = 0;
        if (neighbours.size() > 5) {
            int counter = 0;
            int currentPlayerCount = gomoku.getPlayer();
            for(int neighbour = 0; neighbour < neighbours.size(); neighbour++) {
                if (neighbour == currentPlayerCount) {
                    counter += 1;
                }
                else {
                    int opponent = currentPlayerCount == gomoku.getPlayer() ? 2 : 1;
                    if (neighbour == opponent) {
                        lines = appendLines(lines, counter);
                        currentPlayerCount = opponent;
                        counter = 1;
                    }
                    else {
                        lines = appendLines(lines, counter);
                        counter = 0;
                    }
                }
            }

        }
        return lines;
    }

    private int appendLines(int lines, int counter) {
        if (counter > 2)
            lines += counter;
        return lines;
    }
}
