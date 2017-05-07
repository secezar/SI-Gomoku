package pl.janyst.Game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

/**
 * Created by Piotr Janyst on 2017-05-07.
 */
public class GomokuTests {

    @Test
    public void sizesOfNeighboursByFirstRowAndFirstColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<int[]> neighbours = gomoku.getNeighbouring(0,0);

        assertEquals(5, neighbours.get(0).length);
        assertEquals(5, neighbours.get(1).length);
        assertEquals(5, neighbours.get(2).length);
        assertEquals(5, neighbours.get(3).length);
    }


    @Test void sizesOfNeighboursBySecondRowAndSecondColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<int[]> neighbours = gomoku.getNeighbouring(1,1);

        assertEquals(6, neighbours.get(0).length);
        assertEquals(6, neighbours.get(1).length);
        assertEquals(3, neighbours.get(2).length);
        assertEquals(6, neighbours.get(3).length);
    }

    @Test void sizesOfNeighboursByFirstRowAndSecondColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<int[]> neighbours = gomoku.getNeighbouring(1,1);

        assertEquals(6, neighbours.get(0).length);
        assertEquals(6, neighbours.get(1).length);
        assertEquals(5, neighbours.get(2).length);
        assertEquals(5, neighbours.get(3).length);
    }

    @Test void sizesOfNeighboursBySeventhRowAndSeventhColumnTest() {
        Gomoku gomoku = new Gomoku(15);
        ArrayList<int[]> neighbours = gomoku.getNeighbouring(7,7);

        assertEquals(9, neighbours.get(0).length);
        assertEquals(9, neighbours.get(1).length);
        assertEquals(9, neighbours.get(2).length);
        assertEquals(9, neighbours.get(3).length);
    }
}

//1 1 1 0 0 0 0 0 0 0 0 0 0 0 0
//1 1 1 1 1 1 0 0 0 0 0 0 0 0 0
//1 1 1 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 0 1 0 0 0 0 0 0 0 0 0 0 0
//0 1 0 0 1 0 0 0 0 0 0 0 0 0 0
//0 1 0 0 0 1 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0





