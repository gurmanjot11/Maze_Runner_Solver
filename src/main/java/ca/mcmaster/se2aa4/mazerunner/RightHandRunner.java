package ca.mcmaster.se2aa4.mazerunner;

public class RightHandRunner implements MazeSolver{
    Coordinates position;
    DIRECTION direction= DIRECTION.EAST;
    @Override
    public Path solveMaze(Maze maze) {
        position=maze.getWestEntry();
        String path="";
        Coordinates target = maze.getEastEntry();
        while (!(position.coordEquals(target))){
            path+=move(maze);
        }
        return new Path(path);
    }

    private boolean isLeftValid(Maze maze){
        //true if square 1 to the left is " ", else false
        //modulo was yielding incorrect result for boundary cases like current_direction=0
        DIRECTION relative_left_direction = (this.direction.rotateLeft());
        return checkSquare(maze, relative_left_direction);
    }
    private boolean isRightValid(Maze maze){
        //true if square 1 to the right is " ", else false
        //modulo would have worked here but I wanted to keep logic consistent
        DIRECTION relative_right_direction = (direction.rotateRight());
        return checkSquare(maze, relative_right_direction);
    }
    private boolean isForwardValid(Maze maze){
        //true if square 1 fwd left is " ", else false
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
        //method will make 1 move to get closer to solving the maze
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
