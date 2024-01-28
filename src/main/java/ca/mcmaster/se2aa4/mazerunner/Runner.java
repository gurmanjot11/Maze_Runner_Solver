/**      
 *   Coordinate Layout:
 *       0 1 2 3 4
 *   0   # # # # #
 *   1   # # # # #
 *   2   # # # # #
 *   3   # # # # #
 *   4   # # # # #
 */
package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Runner {
    private static final Logger logger = LogManager.getLogger();
    /** In this iteration, we will assume the maze entry is the west side and exit is the east */
    String[][] maze;
    int[] position=new int[2]; //will store coords as (x,y) w/ x increasing as it goes to the east, y as it goes south
    int fwd_direction=1; //Assume directions: 0=north, 1=east, 2=south,3-west
    int[] east_entry;
    int[] west_entry;

    public Runner(String[][] maze_array){
        this.maze=maze_array;
    }
    private void determineEntries(){
        for (int i=0; i<maze.length;i++){
            if (maze[i][0].equals(" ")){
                this.west_entry=new int[]{0,i};
            }
            if (maze[i][maze[0].length-1].equals(" ")){
                this.east_entry=new int[]{maze[0].length-1,i};
            }
        }
        logger.info("West Entry: ["+ this.west_entry[0]+"," +this.west_entry[1]+"]");
        logger.info("East Entry: ["+ this.east_entry[0]+"," +this.east_entry[1]+"]");
    }
    private void startSearch(){
        determineEntries();
        this.position=west_entry;
        logger.info("Starting Position: ["+ this.position[0]+"," +this.position[1]+"]");
    }

    public void printEntries(){
        logger.info("West Entry: ["+ this.west_entry[0]+"," +this.west_entry[1]+"]");
        logger.info("East Entry: ["+ this.east_entry[0]+"," +this.east_entry[1]+"]");
    }
    public void printPosition(){
        logger.info("Starting Position: ["+ this.position[0]+"," +this.position[1]+"]");
    }

    public String solveMaze(){
        startSearch();
        printEntries();
        printPosition();
        return "No path yet";
    }
}

