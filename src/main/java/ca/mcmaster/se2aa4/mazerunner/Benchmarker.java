package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

/**
 * Performs all benchmarking operations. For our case, we only have one test so just a simple run
 * method is used for this version
 */

public class Benchmarker {

    /**
     * Runs the benchmarking process to get times and speeduo, which are then printed
     * @param baseline_solver Passed maze solver to use as baseline w/ -baseline
     * @param method_solver Passed maze solver to use as the main method w/ -method
     * @param maze_path passed filepath, so a maze can be instantiated here and time can be measured
     */

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