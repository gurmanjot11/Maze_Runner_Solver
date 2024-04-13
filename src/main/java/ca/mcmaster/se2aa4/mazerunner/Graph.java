package ca.mcmaster.se2aa4.mazerunner;

import java.util.Map;

public interface Graph<T> {
    void addEdge(Vertex<T> v_source, Vertex<T> v_target);
    boolean checkEdge(Vertex<T> v_source, Vertex<T> v_target);
    void removeEdge(Vertex<T> v_source, Vertex<T> v_target);
    void addVertex(Vertex<T> v_source);
    void removeVertex(Vertex<T> v_source);
    boolean checkVertex(Vertex<T> v_source);
    void printGraph();
    Map<Vertex<T>,Vertex<T>> breadthFirstSearch(Vertex<T> v_start, Vertex<T> v_end);
}
