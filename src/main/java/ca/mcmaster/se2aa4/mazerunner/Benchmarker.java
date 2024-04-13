package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Benchmarker {
    private static final Logger logger = LogManager.getLogger();
    public void runBenchmark(String maze_path, String method, String baseline) throws IOException {
        String baseline1 = baseline.toLowerCase();
        String method1 = method.toLowerCase();

        MazeSolver baseline_solver;
        if (baseline1.equals("righthand")){
            baseline_solver = new RightHandRunner();
        } else if (baseline1.equals("bfs")) {
            baseline_solver= new GraphMazeRunner();
        }
        else {
            baseline_solver = new GraphMazeRunner();
            logger.error("/!\\ Invalid CLI input for 'baseline' /!\\");
            System.exit(1);
        }

        MazeSolver method_solver;
        if (method1.equals("righthand")){
            method_solver = new RightHandRunner();
        } else if (method1.equals("bfs")) {
            method_solver= new GraphMazeRunner();
        }
        else {
            method_solver = new GraphMazeRunner();
            logger.error("/!\\ Invalid CLI input for 'method' /!\\");
            System.exit(1);
        }
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
        System.out.printf("Time spent using method: %s algorithm (ms): %.2f\n",method1,method_time);
        System.out.printf("Time spent using baseline: %s algorithm (ms): %.2f\n",baseline1,baseline_time);
        System.out.printf("Speedup in terms of path length - baseline/method: %.2f\n",speedup);
    }
}