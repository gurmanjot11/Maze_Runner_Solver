package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;


//TEST COMMIT FOR NEW REPO
public class Main {
    private static final Logger logger = LogManager.getLogger();

    private record Congifuration (String maze_filepath){
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
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        
        if (!cmd.hasOption("i")){
            logger.error("/!\\ No input file given /!\\");
            logger.error("/!\\ Terminating Program /!\\");
            System.exit(1);
        }

        String  maze_filename= cmd.getOptionValue("i");
        logger.info("**** Received input maze file "+ maze_filename);
        //logger.trace(new Configuration(maze_filename));

        return new Congifuration(maze_filename);
    }

    public static void main(String[] args) {
        try{
            Congifuration config = configure(args);
            MazeConvertor maze_convertor = new MazeConvertor(config.maze_filepath);
            try {
                Runner runner = new Runner(maze_convertor.convertToArray());
                Path path = runner.solveMaze();
                System.out.println("Path: "+ path.getFactorizedPath());
                Maze m=new Maze(config.maze_filepath);
                m.printMaze();
            } catch (IOException ioe) {
                logger.error("/!\\ Error Reading File /!\\");
                logger.error("/!\\ Terminating Program /!\\");
                System.exit(1);
            }
        }
        catch(ParseException pe){
            logger.error("/!\\ An error parsing the CLI inputs has occured  /!\\");
            System.exit(1);
        }
    }
}

