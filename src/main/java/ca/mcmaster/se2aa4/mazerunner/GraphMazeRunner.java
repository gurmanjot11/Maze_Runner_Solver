package ca.mcmaster.se2aa4.mazerunner;

import java.util.Map;
import java.util.Stack;

public class GraphMazeRunner implements MazeSolver{
    @Override
    public Path solveMaze(Maze maze) {
        Graph g = convertToUndirectedGraph(maze);
        //g.printGraph();
        Map<CoordinatesVertex,CoordinatesVertex> index = g.breadthFirstSearch(new CoordinatesVertex(maze.getWestEntry()), new CoordinatesVertex(maze.getEastEntry()));
        extractPath(index,maze.getWestEntry(),maze.getEastEntry());
        Path p = new Path("FFFF");
        return p;
    }
    private Graph convertToUndirectedGraph(Maze maze){
        Graph<Coordinates> g= new AdjacencyList<>();
        for (int y=0; y<maze.getHeight(); y++){
            for (int x=0; x< maze.getWidth(); x++){
                Coordinates curr_coords=new Coordinates(x,y);
                if (maze.isOpen(curr_coords)){
                    // add the vertex and all of its edges
                    g.addVertex(new CoordinatesVertex(curr_coords));
                    for (DIRECTION direction : DIRECTION.values()){
                        Coordinates adjacent_coord = curr_coords.preMoveCheck(direction);
                        if (maze.isOpen(adjacent_coord)){
                            CoordinatesVertex v1 = new CoordinatesVertex(adjacent_coord);
                            CoordinatesVertex v2 = new CoordinatesVertex(curr_coords);
                            g.addEdge(v2,v1);
                        }
                    }
                }
            }
        }
        return g;
    }
    private void extractPath(Map<CoordinatesVertex,CoordinatesVertex> index,Coordinates start, Coordinates end){
        Stack<Character> instructions = new Stack<>();

        CoordinatesVertex cv = index.get(new CoordinatesVertex(end));
        while(!cv.equals(new CoordinatesVertex(start))){
            System.out.print(cv.getLabel()+"->");
            cv = index.get(cv);
        }
        System.out.print(cv.getLabel());

    }
//    private String determineSequence(Coordinates prev_coord, Coordinates curr_coord){
//
//    }
}
