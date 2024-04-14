package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Maze data structure to represent a maze. It uses a 2d list to contain the 2d structure of the maze.
 * each maze tile is represented by an open/wall square from the SQUARE enum
 * It also stores the coordinates of its eastern and western entries
 */

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private List<List<SQUARE>> maze = new ArrayList<>();
    private Coordinates east_entry;
    private Coordinates west_entry;
    /**
     *Constructor takes a string filepath, which is then used by a file reader to extract the
     * maze from the text file. Each '#' is represented by a wall square, and each ' ' is an open one
     * @param filepath string filepath for the text file containing maze
     */
    public Maze(String filepath) throws IOException {

        logger.info("Beginning conversion of maze file to a valid maze");
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;

        while ((line = reader.readLine()) != null) {
            List<SQUARE> nextLine= new ArrayList<>();
            for (int i=0; i<line.length(); i++){
                Character c = line.charAt(i);
                SQUARE s= SQUARE.valueOfSquare(c);
                nextLine.add(s);
            }
            maze.add(nextLine);
        }

        this.east_entry=findEastEntry();
        this.west_entry=findWestEntry();

        logger.info("Maze Processed");
    }
    /**
     * the next 2 methods find their respective entries within the maze. Given the computed 2d list,
     * they check the first and last columns to determine the eastern and western entries
     * @return coordinates of the respective entries
     */
    private Coordinates findEastEntry(){
        for (int i=0; i<maze.size(); i++){
            if (isOpen(new Coordinates(getWidth()-1,i))){
                return new Coordinates(getWidth()-1,i);
            }
        }
        throw new IllegalArgumentException("*No opening on the eastern end*");
    }
    private Coordinates findWestEntry(){
        for (int i=0; i<maze.size(); i++){
            if (isOpen(new Coordinates(0,i))){
                return new Coordinates(0,i);
            }
        }
        throw new IllegalArgumentException("*No opening on the western end*");
    }
    /**
     * given a specific coordinate, it checks if the maze is open at that coordinate
     * uses the getSquare method, which simply returns a square at that coordinate
     * @param coords the coordinates to check
     * @return true if it is open and false if its a wall/ not open
     */
    public Boolean isOpen(Coordinates coords){
        try{
            SQUARE square = getSquare(coords);
            return square.equals(SQUARE.OPEN);
        }
        catch(IndexOutOfBoundsException IOOBE){
            return false;
        }
    }
    private SQUARE getSquare(Coordinates coords){
        Integer x = coords.getX();
        Integer y = coords.getY();
        return maze.get(y).get(x);
    }
    /**
     * All the following methods are getters that simply return properties of the maze and take
     * no parameters. The printMaze is self-explanatory.
     */
    public Integer getWidth(){
        return this.maze.get(0).size();
    }
    public Integer getHeight(){
        return this.maze.size();
    }

    public void printMaze(){
        for (int i=0; i<maze.size();i++){
            for (int j=0; j<getWidth();j++){
                System.out.print(" "+getSquare(new Coordinates(j,i)));
            }
            System.out.print("\n");
        }
    }

    public Coordinates getWestEntry(){
        return new Coordinates(this.west_entry.getX(),this.west_entry.getY());
    }
    public Coordinates getEastEntry(){
        return new Coordinates(this.east_entry.getX(), this.east_entry.getY());
    }
}
