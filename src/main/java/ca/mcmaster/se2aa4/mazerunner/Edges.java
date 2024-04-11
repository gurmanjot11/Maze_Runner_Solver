package ca.mcmaster.se2aa4.mazerunner;

public interface Edges {
    void addEdge(Vertex v_source, Vertex v_target);
    boolean checkEdge(Vertex v_source, Vertex v_target);
    void removeEdge(Vertex v_source, Vertex v_target);
}
