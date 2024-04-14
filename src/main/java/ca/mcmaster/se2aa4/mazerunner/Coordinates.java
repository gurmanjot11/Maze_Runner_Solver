package ca.mcmaster.se2aa4.mazerunner;
/**
 * The coordinates class offers a method to store map coordinates as a data type.
 * These coordinates are not specifc to a specific given map, but work for any general coordinate system
 * relative to the starting posiition defined in the constructor. Travelling E/S will positively increament
 * X/Y respective, whereas W/N will negatively increment X/Y.
 */

public class Coordinates {
    private Integer x_coord;
    private Integer y_coord;

    /**
     * Constructor to instantiate a coordinates object
     * @param x x coord to initialize
     * @param y y coord to initialize
     */
    public Coordinates(Integer x,Integer y){
        this.x_coord=x;
        this.y_coord=y;
    }
    /**
     *Will take a coordinate and increment coords to reflect movement one time in a direction
     * @param direction the cardinal direction to move 1 square in
     * @return returns the updated coordinate (but should update value internally)
     */
    public Coordinates move(DIRECTION direction){
        switch (direction){
            case NORTH -> {
                this.y_coord--;
            }
            case SOUTH -> {
                this.y_coord++;
            }
            case EAST -> {
                this.x_coord++;
            }
            case WEST -> {
                this.x_coord--;
            }
        }
        return this;
    }
    /**
     * Same function as move command but instead of updating the corresponding
     * coord object, it leaves that the same and returns a new object with the new coords
     * to simply check values
     * @param direction the cardinal direction to move 1 square in
     * @return returns the updated coordinate (but should update value internally)
     */
    public Coordinates preMoveCheck(DIRECTION direction){
        Integer x=this.getX();
        Integer y=this.getY();
        switch (direction){
            case NORTH -> {
                y--;
            }
            case SOUTH -> {
                y++;
            }
            case EAST -> {
                x++;
            }
            case WEST -> {
                x--;
            }
        }
        return new Coordinates(x,y);
    }
    /**
     * takes another coordinate and returns if it is equal to itself
     * @param c coordinate object
     * @return boolean value if they are equal or not
     */
    public boolean coordEquals(Coordinates c){
        int c_x= c.getX();
        int c_y= c.getY();
        if (this.x_coord==c_x & this.y_coord==c_y){
            return true;
        }
        return false;
    }
    public Integer getX(){
        return this.x_coord;
    }
    public Integer getY(){
        return this.y_coord;
    }
    @Override
    public String toString() {
        Integer x = this.x_coord;
        Integer y = this.y_coord;
        String s = "("+x+","+y+")";
        return s;
    }
}
