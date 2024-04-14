package ca.mcmaster.se2aa4.mazerunner;

import java.util.Map;

/**
 * Graph interface that uses a general type T to remain abstract and offer services that a graph should
 * The general typing is used to govern the fact that if the graph is of type T, it can only be comprised
 * of vertices that are also type T. This will prevent undesired use of the graph by mixing vertices of
 * type A with vertices of type B.
 *
 * This will apply to adjacency list and all further implementations.
 */


public interface Graph<T> {
    /**
     * Will add an edge to the graph. If the specified vertices are not yet in the graph, it will add them
     * if the edge already exists, it does nothing
     * @param v_source source vertex/start
     * @param v_target target vertex/endpoint
     */
    void addEdge(Vertex<T> v_source, Vertex<T> v_target);
    /**
     *  checks whether an edge is already in the graph
     * @param v_source start vertex of edge
     * @param v_target endpoint vertex of edge
     * @return true if edge in graph, else false
     */
    boolean checkEdge(Vertex<T> v_source, Vertex<T> v_target);
    /**
     * removes an edge from the graph. If the specified edge doesn't exist, it does nothing.
     * @param v_source start vertex of edge
     * @param v_target endpoint vertex of edge
     */
    void removeEdge(Vertex<T> v_source, Vertex<T> v_target);
    /**
     * Will add a vertex to the graph. If it is already there, does nothing
     * @param v_source  vertex to be added
     */
    void addVertex(Vertex<T> v_source);
    /**
     * Will remove a vertex from the graph. If not there already , it does nothing.
     * @param v_source  vertex to be removed
     */
    void removeVertex(Vertex<T> v_source);
    /**
     * Checks whether a vertex is in the graph.
     * @param v_source  vertex to be checked
     * @return true if vertex is in, else false
     */
    boolean checkVertex(Vertex<T> v_source);
    void printGraph();
    /**
     * Will run a breadth first search on the graph.
     * Credit to 2CO3 textbook for algorithm, which is cited in the report
     * @param v_start starting vertex for the BFS
     * @param v_end ending vertex for the BFS
     * @return returns an index of previous vertices as provided by a BFS algorithm
     */
    Map<Vertex<T>,Vertex<T>> breadthFirstSearch(Vertex<T> v_start, Vertex<T> v_end);
}
