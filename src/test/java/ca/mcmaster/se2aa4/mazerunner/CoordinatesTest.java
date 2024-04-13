package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CoordinatesTest {
    Coordinates c1;
    Coordinates c2;
    Coordinates c3;
    @BeforeEach
    public void initialization(){
        c1 = new Coordinates(1,2);
        c2 = new Coordinates(1,2);
        c3 = new Coordinates(2,2);
    }
    @Test
    public void getterTest(){
        Integer x1=c1.getX();
        Integer y1=c1.getY();
        assertEquals(1,x1);
        assertEquals(2,y1);
    }
    @Test
    public void coordEqualsTest(){
        boolean b1 = c1.coordEquals(c2);
        boolean b2 = c1.coordEquals(c3);
        assertTrue(b1);
        assertFalse(b2);
    }
    @Test
    public void moveTest(){
        Coordinates c4 = c1.preMoveCheck(DIRECTION.EAST);
        boolean b1 = c3.coordEquals(c4);
        assertTrue(b1);
    }

}
