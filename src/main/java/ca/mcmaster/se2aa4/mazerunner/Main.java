package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;



//TEST COMMIT FOR NEW REPO
public class Main {
    private static final Logger logger = LogManager.getLogger();

    private record Congifuration (String maze_filepath, String test_path, String method,String baseline){
        Congifuration{
            if (maze_filepath == null){
                throw new IllegalArgumentException("File path to maze cannot be null");
            }
        }
    }

    private static Congifuration configure (String[] args) throws ParseException {
        Options options = new Options();

        /** Option for taking input maze file path */
        options.addOption("i","input", true, "input file for maze");
        options.addOption("p","path", true, "Optional flag to provide test path for maze");
        options.addOption("method","method", true, "Optional flag to choose which solving algorithm to use");
        options.addOption("baseline","baseline", true, "Optional flag to compare performance from baseline to method");


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        
        if (!cmd.hasOption("i")){
            logger.error("/!\\ No input file given /!\\");
            logger.error("/!\\ Terminating Program /!\\");
            System.exit(1);
        }

        String  maze_filename= cmd.getOptionValue("i");
        String test_path = cmd.getOptionValue("p");
        String method = cmd.getOptionValue("method");
        String baseline = cmd.getOptionValue("baseline");

        logger.info("**** Received input maze file "+ maze_filename);

        return new Congifuration(maze_filename,test_path,method,baseline);
    }

    public static void main(String[] args) {
        try{
            Congifuration config = configure(args);
            try {
                if(config.test_path!=null){
                    Maze maze= new Maze(config.maze_filepath);
                    Path path= new Path(config.test_path);
                    path=path.defactorPath();
                    boolean valid=path.verifyPath(maze);
                    if (valid){
                        System.out.println("\n**This path is valid**");
                    }
                    else{
                        System.out.println("\n**This path is NOT valid**");
                    }
                }
                else if (config.baseline!=null) {
                    if (config.method==null){
                        logger.error("/!\\ An error parsing the CLI inputs has occured /!\\");
                        System.exit(1);
                    }
                    else{
                        String baseline = config.baseline.toLowerCase();
                        String method = config.method.toLowerCase();

                        MazeSolver baseline_solver;
                        if (baseline.equals("righthand")){
                            baseline_solver = new RightHandRunner();
                        } else if (baseline.equals("bfs")) {
                            baseline_solver= new GraphMazeRunner();
                        }
                        else {
                            baseline_solver = new GraphMazeRunner();
                            logger.error("/!\\ Invalid CLI input for 'baseline' /!\\");
                            System.exit(1);
                        }

                        MazeSolver method_solver;
                        if (method.equals("righthand")){
                            method_solver = new RightHandRunner();
                        } else if (method.equals("bfs")) {
                            method_solver= new GraphMazeRunner();
                        }
                        else {
                            method_solver = new GraphMazeRunner();
                            logger.error("/!\\ Invalid CLI input for 'method' /!\\");
                            System.exit(1);
                        }

                        //test both strategies

                        long maze_load_start = System.currentTimeMillis();
                        Maze maze= new Maze(config.maze_filepath);
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
                        System.out.printf("Time spent using method: %s algorithm (ms): %.2f\n",method,method_time);
                        System.out.printf("Time spent using baseline: %s algorithm (ms): %.2f\n",baseline,baseline_time);
                        System.out.printf("Speedup in terms of path length - baseline/method: %.2f\n",speedup);
                    }
                }
                else{
                    Maze maze= new Maze(config.maze_filepath);
                    MazeSolver maze_solver;
                    if (config.method == null){
                        maze_solver = new GraphMazeRunner();
                    }
                    else{
                        String method = config.method.toLowerCase();
                        switch (method){
                            case "righthand" -> {
                                maze_solver = new RightHandRunner();
                            }
                            case "bfs" -> {
                                maze_solver = new GraphMazeRunner();
                            }
                            default -> {
                                logger.info("****Invalid method flag. Using graph algorithm by default.");
                                maze_solver = new GraphMazeRunner();
                            }
                        }
                    }
                    Path path = maze_solver.solveMaze(maze);
                    System.out.println("Path: "+ path.getFactorizedPath());
                }
            } catch (IOException ioe) {
                logger.error("/!\\ Error Reading File /!\\");
                logger.error("/!\\ Terminating Program /!\\");
                System.exit(1);
            }
        }
        catch(ParseException pe){
            logger.error("/!\\ An error parsing the CLI inputs has occured /!\\");
            System.exit(1);
        }
    }
}

