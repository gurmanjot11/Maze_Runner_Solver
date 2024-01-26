package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class MazeConvertor {
    private static final Logger logger = LogManager.getLogger();

    String filepath;

    //Constructor
    public MazeConvertor(String maze_filepath){
        this.filepath=maze_filepath;
    }

    //Method to convert file in filepath to a 2d array
    public String[][] convertToArray() throws IOException {

        // Assumption is that given maze is valid
        logger.info("Beginning conversion of maze file to a valid maze");
        String maze_pattern="";
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        
        //Initial case (should be no \n preceding the entry)
        String line=reader.readLine();
        maze_pattern+=line;

        //Loops through for the rest of the maze rows
        int line_num=1;
        while ((line = reader.readLine()) != null) {
            maze_pattern+="\n";
            maze_pattern+=line;
            line_num++;
        }

        String[] maze_rows_only = maze_pattern.split("\n");

        String[][] maze = new String[maze_rows_only.length][maze_rows_only[0].length()];
        for (int i =0; i<maze_rows_only.length;i++){
            maze[i] = maze_rows_only[i].split("");
        }
        logger.info("Maze Computed");

        return maze;
    }
 }

