package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Benchmarker {
    private static final Logger logger = LogManager.getLogger();
    public void runBenchmark(String maze_path, MazeSolver method_solver, MazeSolver baseline_solver) throws IOException {

        long maze_load_start = System.currentTimeMillis();
        Maze maze= new Maze(maze_path);
        long maze_load_end = System.currentTimeMillis();
        Path method_path = method_solver.solveMaze(maze);
        long method_solver_time = System.currentTimeMillis();
        Path baseline_path = baseline_solver.solveMaze(maze);
        long baseline_solver_time = System.currentTimeMillis();


        Double load_time = (double) (maze_load_end - maze_load_start);
        Double method_time = (double)(method_solver_time - maze_load_end);
        Double baseline_time = (double)(baseline_solver_time - method_solver_time);

        Double method_length = Double.valueOf(method_path.getPathLength());
        Double baseline_length = Double.valueOf(baseline_path.getPathLength());

        Double speedup = baseline_length/method_length;

        System.out.printf("Time spent loading maze from file (ms): %.2f\n",load_time);
        System.out.printf("Time spent using method: method algorithm (ms): %.2f\n",method_time);
        System.out.printf("Time spent using baseline: baseline algorithm (ms): %.2f\n",baseline_time);
        System.out.printf("Speedup in terms of path length - baseline/method: %.2f\n",speedup);
    }
}