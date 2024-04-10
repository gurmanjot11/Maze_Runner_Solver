package ca.mcmaster.se2aa4.mazerunner;

public enum DIRECTION {
    NORTH, SOUTH, EAST, WEST;

    public DIRECTION rotateRight(){
        switch(this){
            case NORTH: return DIRECTION.EAST;
            case EAST:  return DIRECTION.SOUTH;
            case SOUTH: return DIRECTION.WEST;
            case WEST:  return DIRECTION.NORTH;
            default: throw new IllegalArgumentException(this+"*Unexpected Value*");
        }
    }
    public DIRECTION rotateLeft(){
        switch(this){
            case NORTH: return DIRECTION.WEST;
            case EAST:  return DIRECTION.NORTH;
            case SOUTH: return DIRECTION.EAST;
            case WEST:  return DIRECTION.SOUTH;
            default: throw new IllegalArgumentException(this+"*Unexpected Value*");
        }
    }
}
