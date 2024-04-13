package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListTest {
    Graph<Coordinates> g;
    Coordinates c1 = new Coordinates(1,1);
    Coordinates c2 = new Coordinates(2,1);
    Coordinates c3 = new Coordinates(1,2);

    @BeforeEach
    public void initialization(){
        g = new AdjacencyList<>();
    }
    @Test
    public void addVertexTest(){
        g.addVertex(new CoordinatesVertex(c1));
        boolean b1 = g.checkVertex(new CoordinatesVertex(c1));
        boolean b2 = g.checkVertex(new CoordinatesVertex(c2));
        assertTrue(b1);
        assertFalse(b2);
    }
    @Test
    public void addEdgeTest(){
        g.addEdge(new CoordinatesVertex(c1),new CoordinatesVertex(c2));
        boolean b1 = g.checkEdge(new CoordinatesVertex(c1),new CoordinatesVertex(c2));
        boolean b2 = g.checkEdge(new CoordinatesVertex(c2), new CoordinatesVertex(c1));
        assertTrue(b1);
        assertFalse(b2);
    }
    @Test
    public void removeEdgeTest(){
        g.addEdge(new CoordinatesVertex(c1),new CoordinatesVertex(c2));
        g.removeEdge(new CoordinatesVertex(c1),new CoordinatesVertex(c2));
        boolean b1 = g.checkEdge(new CoordinatesVertex(c1),new CoordinatesVertex(c2));
        assertFalse(b1);
    }
    @Test
    public void bfsTest() {
        g.addEdge(new CoordinatesVertex(c1),new CoordinatesVertex(c2));
        g.addEdge(new CoordinatesVertex(c2),new CoordinatesVertex(c3));
        Map<Vertex<Coordinates>, Vertex<Coordinates>> index = g.breadthFirstSearch(new CoordinatesVertex(c1),new CoordinatesVertex(c3));
        CoordinatesVertex cv1 = (CoordinatesVertex) index.get(new CoordinatesVertex(c3));
        boolean b1 = (cv1.getObject()).coordEquals(c2);
        boolean b2 = cv1.equals(new CoordinatesVertex(c2));
        assertTrue(b1);
        assertTrue(b2);
    }

}
