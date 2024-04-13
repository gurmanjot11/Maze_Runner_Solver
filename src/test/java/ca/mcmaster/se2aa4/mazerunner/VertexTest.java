package ca.mcmaster.se2aa4.mazerunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class VertexTest {
    Vertex<Coordinates> v1;
    @BeforeEach
    public void initialization(){
        Vertex<Coordinates> v1 = new CoordinatesVertex(new Coordinates(1,2));
    }
    @Test
    public void getLabel(){
        assertEquals("(1,2)",v1.getLabel());
    }

    @Test
    public void equals1(){
        Vertex<Coordinates> v2 = new CoordinatesVertex(new Coordinates(2,2));
        boolean b = v1.equals(v2);
        assertFalse(b);
    }
    @Test
    public void equals2(){
        Vertex<Coordinates> v2 = new CoordinatesVertex(new Coordinates(1,2));
        boolean b = v1.equals(v2);
        assertTrue(b);
    }
    @Test
    public void equals3(){
        String v2 = "(1,2)";
        boolean b = v1.equals(v2);
        assertFalse(b);
    }
}
