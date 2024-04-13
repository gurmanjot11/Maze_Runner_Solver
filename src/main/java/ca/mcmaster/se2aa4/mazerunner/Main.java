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
                        logger.error("/!\\ An error with CLI inputs entries has occured /!\\");
                        System.exit(1);
                    }
                    else{
                        String baseline1 = config.baseline.toLowerCase();
                        String method1 = config.method.toLowerCase();

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
                        Benchmarker b = new Benchmarker();
                        b.runBenchmark(config.maze_filepath, method_solver, baseline_solver);
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

