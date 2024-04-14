package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;
/**
 * Implements graph, so for method explanations refer to that.
 *
 * Simulates a graph using an adjacency list (vertex that maps to a linked list of its connected vertices)
 * i.e.
 * v1: v2->v3
 * v2: null
 * v3: v2
 * :
 * vn: v1 -> v2 -> v3
 */

public class AdjacencyList<T> implements Graph<T> {
    private Map<Vertex<T>, LinkedList<Vertex<T>>> edges;
    public AdjacencyList(){
        this.edges = new HashMap<>();
    }

    @Override
    public void addEdge(Vertex<T> v_source, Vertex<T> v_target) {
        //edge already exists
        if(checkEdge(v_source,v_target)){
            return;
        }
        //ensures vertices are in the graph
        if(!checkVertex(v_source)){
            addVertex(v_source);
        }
        if(!checkVertex(v_target)){
            addVertex(v_target);
        }
        LinkedList<Vertex<T>> connected_components= this.edges.get(v_source);
        connected_components.add(v_target);
    }

    @Override
    public boolean checkEdge(Vertex<T> v_source, Vertex<T> v_target) {
        LinkedList<Vertex<T>> connected_components = edges.get(v_source);
        if (connected_components == null){
            return false;
        }
        else if(connected_components.isEmpty()){
            return false;
        }
        Integer size = connected_components.size();
        for (int i=0; i<size; i++){
            if(connected_components.get(i).equals(v_target)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeEdge(Vertex<T> v_source, Vertex<T> v_target) {
        if(!checkEdge(v_source,v_target)){
            return;
        }
        LinkedList connected_components = edges.get(v_source);
        connected_components.remove(v_target);
    }

    @Override
    public void addVertex(Vertex<T> v_source) {
        if (!checkVertex(v_source)){
            this.edges.put(v_source,new LinkedList<Vertex<T>>());
        }
    }

    @Override
    public void removeVertex(Vertex<T> v_source) {
        if(!checkVertex(v_source)){
            return;
        }
        edges.remove(v_source);
        Set<Vertex<T>> s = this.edges.keySet();
        for (Vertex<T> v : s){
            if (checkEdge(v,v_source)){
                removeEdge(v,v_source);
            }
        }
    }
    @Override
    public boolean checkVertex(Vertex<T> v_source){
        Set<Vertex<T>> s = this.edges.keySet();
        if (s.isEmpty()){
            return false;
        }
        for (Vertex<T> v : s){
            if(v.equals(v_source)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void printGraph(){
        Set<Vertex<T>> s = edges.keySet();
        if(s.isEmpty()){
            System.out.println("Graph is empty.");
            return;
        }
        else{
            for (Vertex<T> v : s){
                System.out.print(v.getLabel()+": ");
                LinkedList<Vertex<T>> connected_components = edges.get(v);
                if (connected_components == null){
                    System.out.print("\n");
                    continue;
                }
                else if(connected_components.isEmpty()){
                    System.out.print("\n");
                    continue;
                }
                Integer size = connected_components.size();
                for (int i=0; i<size; i++){
                    System.out.print(connected_components.get(i).getLabel());
                    if (!(i==size-1)){
                        System.out.print(" -> ");
                    }
                }
                System.out.print("\n");
            }
        }
    }

    @Override
    public Map<Vertex<T>,Vertex<T>> breadthFirstSearch(Vertex<T> v_start,Vertex<T> v_end) {
        if(!checkVertex(v_start) | !checkVertex(v_end)){
            throw new RuntimeException("Error: Start or End vertex not in graph");
        }
        HashMap<Vertex<T>,Boolean> visited = new HashMap();
        HashMap<Vertex<T>,Double> cost = new HashMap<>();
        Map<Vertex<T>,Vertex<T>> prev_vertex = new HashMap<>();
        //initialize values
        for (Vertex<T> v : edges.keySet()){
            visited.put(v,false);
            cost.put(v, Double.POSITIVE_INFINITY);
            prev_vertex.put(v,null);
        }
        //start algo
        Queue<Vertex<T>> q = new LinkedList<>();
        q.offer(v_start);
        cost.put(v_start,0.0);
        visited.put(v_start,true);
        prev_vertex.put(v_start,null);
        while (!q.isEmpty()){
            if(visited.get(v_end)){
                break;
            }
            Vertex<T> front = q.poll();
            LinkedList<Vertex<T>> connected_components = getConnectedComponents(front);

            if (connected_components == null){
                continue;
            }
            if (connected_components.size()==0){
                continue;
            }
            for(Vertex<T> v : connected_components){
                if (!visited.get(v)){
                    visited.put(v,true);
                    prev_vertex.put(v,front);
                    cost.put(v,cost.get(front)+1);
                    q.offer(v);
                }
            }
        }
        return prev_vertex;
    }
    /**
     * takes a vertex key and returns all vertices which is shares an edge with
     * @param v vertex object as key
     * @return linked list of all connected vertices
     */
    private LinkedList<Vertex<T>> getConnectedComponents(Vertex<T> v){
        if (!checkVertex(v)){
            return null;
        }
        return edges.get(v);
    }
}
