package pl.janyst.Game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import pl.janyst.Algorithms.AlphaBeta;
import pl.janyst.Algorithms.Simple;

import java.util.ArrayList;

/**
 * Created by Piotr Janyst on 2017-05-07.
 */
public class GomokuTests {

    @Test
    public void sizesOfNeighboursByFirstRowAndFirstColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<ArrayList<Integer>> neighbours = gomoku.getNeighbouring(0,0);

        assertEquals(5, neighbours.get(0).size());
        assertEquals(5, neighbours.get(1).size());
        assertEquals(5, neighbours.get(2).size());
        assertEquals(1, neighbours.get(3).size());
    }


    @Test void sizesOfNeighboursBySecondRowAndSecondColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<ArrayList<Integer>> neighbours = gomoku.getNeighbouring(1,1);

        assertEquals(6, neighbours.get(0).size());
        assertEquals(6, neighbours.get(1).size());
        assertEquals(6, neighbours.get(2).size());
        assertEquals(3, neighbours.get(3).size());
    }

    @Test void sizesOfNeighboursByFirstRowAndSecondColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<ArrayList<Integer>> neighbours = gomoku.getNeighbouring(0,1);

        assertEquals(6, neighbours.get(0).size());
        assertEquals(5, neighbours.get(1).size());
        assertEquals(5, neighbours.get(2).size());
        assertEquals(2, neighbours.get(3).size());
    }

    @Test void sizesOfNeighboursBySeventhRowAndSeventhColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<ArrayList<Integer>> neighbours = gomoku.getNeighbouring(7,7);

        assertEquals(9, neighbours.get(0).size());
        assertEquals(9, neighbours.get(1).size());
        assertEquals(9, neighbours.get(2).size());
        assertEquals(9, neighbours.get(3).size());
    }

    @Test void sizesOfNeighboursByEleventhRowAndTenthColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<ArrayList<Integer>> neighbours = gomoku.getNeighbouring(11,10);

        assertEquals(9, neighbours.get(0).size());
        assertEquals(8, neighbours.get(1).size());
        assertEquals(8, neighbours.get(2).size());
        assertEquals(8, neighbours.get(3).size());
    }
}

//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 1 0 0 0 1 0 0 0 1
//0 0 0 0 0 0 0 1 0 0 1 0 0 1 0
//0 0 0 0 0 0 0 0 1 0 1 0 1 0 0
//0 0 0 0 0 0 0 0 0 1 1 1 0 0 0
//0 0 0 0 0 0 1 1 1 1 1 1 1 1 1
//0 0 0 0 0 0 0 0 0 1 1 1 0 0 0
//0 0 0 0 0 0 0 0 1 0 1 0 1 0 0
//0 0 0 0 0 0 0 1 0 0 1 0 0 1 0





