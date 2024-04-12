package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private List<List<SQUARE>> maze = new ArrayList<>();
    private Coordinates east_entry;
    private Coordinates west_entry;
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
        return this.west_entry;
    }
    public Coordinates getEastEntry(){
        return this.east_entry;
    }
}
