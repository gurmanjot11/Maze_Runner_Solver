package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Stack;

/**
 * Maze solver which uses graph algorithm. For public methods, refer to MazeSolver interface.
 */

public class GraphMazeRunner implements MazeSolver{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Path solveMaze(Maze maze) {
        Graph g = convertToUndirectedAdjacencyList(maze);
        Map<CoordinatesVertex,CoordinatesVertex> index;
        try{
            index = g.breadthFirstSearch(new CoordinatesVertex(maze.getWestEntry()), new CoordinatesVertex(maze.getEastEntry()));
            extractPath(index,maze.getWestEntry(),maze.getEastEntry());
        }
        catch(RuntimeException rte){
            logger.error("Invalid maze or start/end parameters");
            System.exit(1);
        }
        index = g.breadthFirstSearch(new CoordinatesVertex(maze.getWestEntry()), new CoordinatesVertex(maze.getEastEntry()));
        Path p = extractPath(index,maze.getWestEntry(),maze.getEastEntry());
        return p;
    }
    /**
     *  Converts a given maze into an undirected adjacency list
     * @param maze input maze to turn into a graph
     * @return returns a graph (i.e. adjacency list) object encoding  of the maze
     */
    private Graph convertToUndirectedAdjacencyList(Maze maze){
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
    /**
     * Given the index from the BFS, it will extract previous vertices to create the full path
     * from start vertex to end
     * @param index Map that contains each vertex's previous vertex
     * @param end coordinates of the starting vertex
     * @param start coordinates of the ending vertex
     * @return returns corresponding path to the input index
     */
    private Path extractPath(Map<CoordinatesVertex,CoordinatesVertex> index,Coordinates start, Coordinates end){
        Stack<Character> instructions = new Stack<>();
        DIRECTION direction = DIRECTION.WEST;

        CoordinatesVertex cv_curr = new CoordinatesVertex(end);
        CoordinatesVertex cv_prev = index.get(cv_curr);
        while(!cv_curr.equals(new CoordinatesVertex(start))){
            String movement = determineSequence(cv_prev.getObject(),cv_curr.getObject(),direction);
            //System.out.print(movement);
            if(movement.charAt(0)=='R'){
                direction = direction.rotateRight();
            }
            else if (movement.charAt(0)=='L') {
                direction = direction.rotateLeft();
            }
            cv_curr=cv_prev;
            cv_prev = index.get(cv_prev);
            for (int i=0; i<movement.length();i++){
                if(movement.charAt(i)=='L'){
                    instructions.add('R');
                }
                else if (movement.charAt(i)=='R') {
                    instructions.add('L');
                }
                else{
                    instructions.add('F');
                }
            }
        }
        String path="";
        while (!instructions.isEmpty()){
            path+=instructions.pop();
        }
        return new Path(path);
    }
    /**
     * based on a given previous vertex, decides which steps had to be taken to reach that new vertex
     * @param curr_coord coordinates of current vertex
     * @param prev_coord coordinates of previous vertex from index
     * @param curr_direction current direction the solver is facing
     * @return returns corresponding movement taken
     */
    private String determineSequence(Coordinates prev_coord, Coordinates curr_coord, DIRECTION curr_direction){
        Coordinates fwd_coord = curr_coord.preMoveCheck(curr_direction);
        Coordinates left_coord = curr_coord.preMoveCheck(curr_direction.rotateLeft());
        Coordinates right_coord = curr_coord.preMoveCheck(curr_direction.rotateRight());
        if(fwd_coord.coordEquals(prev_coord)){
            return "F";
        }
        else if (left_coord.coordEquals(prev_coord)){
            return "LF";
        }
        else if (right_coord.coordEquals(prev_coord)){
            return "RF";
        }
        else{
            throw new RuntimeException("bad path");
        }
    }
}
