package ca.mcmaster.se2aa4.mazerunner;

/**
 * MazeSolver interface offers a solve service which takes a maze and will return a valid path that
 * solves the maze
 * Any maze solving algorithm will implement this. Ideally Maze should be more general to support different
 * solving algorithms more natively (i.e. filepath straight to graph instead of going to maze, then graph).
 * However, this is how it works for now.
 */

public interface MazeSolver {
    /**
     * When called, the respective algorithm will compute a path to escape the maze
     * @param maze the maze to solve
     * @return a path which contains the solved result.
     */
    Path solveMaze(Maze maze);
}
