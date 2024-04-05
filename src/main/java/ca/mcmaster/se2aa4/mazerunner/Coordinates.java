package ca.mcmaster.se2aa4.mazerunner;

public class Coordinates {
    private Integer x_coord=0;
    private Integer y_coord=0;
    public Coordinates(Integer x,Integer y){
        this.x_coord=x;
        this.y_coord=y;
    }
    public void move(DIRECTION direction){
        switch (direction){
            case NORTH -> {
                this.y_coord++;
            }
            case SOUTH -> {
                this.y_coord--;
            }
            case EAST -> {
                this.x_coord++;
            }
            case WEST -> {
                this.x_coord--;
            }
        }
    }
    public Integer getX(){
        return this.x_coord;
    }
    public Integer getY(){
        return this.y_coord;
    }
}
