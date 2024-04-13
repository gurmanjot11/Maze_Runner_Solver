package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;

public class GraphMazeRunnerTest {
    @Test
    public void solveMazeTest() throws IOException {
        // please run from a3 directory
        String maze_filepath="examples/straight.maz.txt";
        Maze maze = new Maze(maze_filepath);
        MazeSolver ms = new GraphMazeRunner();
        Path p = ms.solveMaze(maze);
        assertEquals(p.toString(),"FFFF");
    }
}
