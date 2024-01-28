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
    int current_direction=1; //Assume directions: 0=north, 1=east, 2=south,3-west
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

    private boolean isLeftValid(){
        //true if square 1 to the left is " ", else false
        int relative_left_direction = (current_direction-1)%4;
        return checkSquare(relative_left_direction);
    }
    private boolean isRightValid(){
        //true if square 1 to the right is " ", else false
        int relative_right_direction = (current_direction+1)%4;
        return checkSquare(relative_right_direction);
    }
    private boolean isForwardValid(){
        //true if square 1 fwd left is " ", else false
        return checkSquare(current_direction);
    }

    private boolean checkSquare(int direction){
        boolean valid;
        try {
            switch (direction) {
                case 0:
                    valid = maze[position[0]][position[1]-1].equals(" ");
                    break;
                case 1:
                    valid = maze[position[0]+1][position[1]].equals(" ");
                    break;
                case 2:
                    valid = maze[position[0]][position[1]+1].equals(" ");
                    break;
                case 3:
                    valid = maze[position[0]-1][position[1]].equals(" ");   
                    break;             
                default:
                    return false;
            }
            return valid;
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            return false;
        }
    }
    
    private void rotateRunnerLeft(){
        this.current_direction=(this.current_direction-1)%4;
    }
    private void rotateRunnerRight(){
        this.current_direction=(this.current_direction+1)%4;
    }
    private void moveForward(){
        //no validation because contract is that a valid movement has been decided by helper methods
        switch (current_direction) {
            case 0:
                position=new int[]{position[0],position[1]-1};
                break;
            case 1:
                position=new int[]{position[0]+1,position[1]};
                break;
            case 2:
                position=new int[]{position[0],position[1]+1};
                break;
            case 3:
                position=new int[]{position[0]-1,position[1]};
                break;             
            default:
                logger.error("/!\\ Error moving the runner  /!\\");
                logger.error("/!\\ Terminating the Program  /!\\");
                System.exit(1);
                break;
        }
        return;
    }

    public String solveMaze(){
        startSearch();
        logger.info("Initial FWD direction: "+ this.current_direction);
        logger.info("Initial pos: ["+ this.position[0]+","+this.position[1]);
        logger.info("Moving FWD");
        moveForward();
        logger.info("New pos: ["+ this.position[0]+","+this.position[1]+"\n");
        rotateRunnerRight();
        logger.info("New FWD direction: "+ this.current_direction);
        moveForward();
        logger.info("New pos: ["+ this.position[0]+","+this.position[1]+"\n");
        rotateRunnerRight();
        logger.info("New FWD direction: "+ this.current_direction);
        moveForward();
        logger.info("New pos: ["+ this.position[0]+","+this.position[1]+"\n");
        rotateRunnerRight();
        logger.info("New FWD direction: "+ this.current_direction);
        moveForward();
        logger.info("New pos: ["+ this.position[0]+","+this.position[1]+"\n");
        rotateRunnerRight();
        logger.info("New FWD direction: "+ this.current_direction);
        // logger.info("Left Square Valid? : "+ isLeftValid());
        // logger.info("Right Square Valid? : "+ isRightValid());
        // logger.info("FWD Square Valid? : "+ isForwardValid());
        // rotateRunnerLeft();
        // logger.info("--Rotating Left");
        // logger.info("Right Square Valid? : "+ isRightValid());
        // logger.info("FWD Square Valid? : "+ isForwardValid());
        // rotateRunnerRight();
        // logger.info("--Rotating Right");
        // logger.info("Right Square Valid? : "+ isRightValid());
        // logger.info("FWD Square Valid? : "+ isForwardValid());
        return "No path yet";
    }
}

