package ca.mcmaster.se2aa4.mazerunner;

/**
 * Implementation of MazeSolver which uses the right hand algorithm.
 * An old class that has not changed so I will not comment it, but still works
 */

public class RightHandRunner implements MazeSolver{
    Coordinates position;
    DIRECTION direction= DIRECTION.WEST;
    @Override
    public Path solveMaze(Maze maze) {
        position=maze.getEastEntry();
        String path="";
        Coordinates target = maze.getWestEntry();
        while (!(position.coordEquals(target))){
            path+=move(maze);
        }
        return new Path(path);
    }

    private boolean isLeftValid(Maze maze){
        DIRECTION relative_left_direction = (this.direction.rotateLeft());
        return checkSquare(maze, relative_left_direction);
    }
    private boolean isRightValid(Maze maze){
        DIRECTION relative_right_direction = (direction.rotateRight());
        return checkSquare(maze, relative_right_direction);
    }
    private boolean isForwardValid(Maze maze){
        return checkSquare(maze, direction);
    }

    private boolean checkSquare(Maze maze, DIRECTION direction){
        boolean valid;
        valid = maze.isOpen(position.preMoveCheck(direction));
        return valid;
    }

    private void rotateRunnerLeft(){
        this.direction=direction.rotateLeft();
    }
    private void rotateRunnerRight(){
        this.direction=direction.rotateRight();
    }
    private void moveForward(){
        this.position=this.position.move(direction);
    }

    private String move(Maze maze){
        if (isRightValid(maze)) {
            rotateRunnerRight();
            moveForward();
            return "RF";
        }
        else if (isForwardValid(maze)){
            moveForward();
            return "F";
        }
        else if (isLeftValid(maze)){
            rotateRunnerLeft();
            moveForward();
            return "LF";
        }
        else{
            rotateRunnerLeft();
            return "L";
        }
    }
}
