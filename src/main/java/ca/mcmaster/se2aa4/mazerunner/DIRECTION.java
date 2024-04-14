package ca.mcmaster.se2aa4.mazerunner;

/**
 * DIRECTION enum to represent each cardinal direction
 */

public enum DIRECTION {
    NORTH, SOUTH, EAST, WEST;

    /**
     * Both rotation commands are self-explanatory. Based on its current status, it will change to match
     * if it were rotated right or left respectively.
     * @return the rotated direction
     */

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
