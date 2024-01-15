package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        Options options = new Options();
        String maze_filename =null;
        
        options.addOption("i","input", true, "input file for maze");
        CommandLineParser parser = new DefaultParser();
       

        try {
            CommandLine cmd = parser.parse(options, args);
            if (!cmd.hasOption("i")){
                logger.error("/!\\ No input file given /!\\");
            }
            else{
                maze_filename= cmd.getOptionValue("i");
                logger.info("**** Received input maze file "+ maze_filename);
            }
        }
        catch(ParseException e) {
            logger.error("/!\\ An error has occured /!\\");
        }
            
        if (maze_filename!=null){
            logger.info("** Starting Maze Runner");
            try {
                logger.info("**** Reading the maze from file " + maze_filename);
                BufferedReader reader = new BufferedReader(new FileReader(maze_filename));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.info("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.info("PASS ");
                        }
                    }
                    logger.info(System.lineSeparator());
                }
            } catch(Exception e) {
                logger.error("/!\\ An error has occured /!\\");
            }
            logger.info("**** Computing path");
            logger.info("PATH NOT COMPUTED");
            logger.info("** End of MazeRunner");
        }

    }
}
