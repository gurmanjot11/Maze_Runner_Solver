package ca.mcmaster.se2aa4.mazerunner;

public class Coordinates {
    private Integer x_coord=0;
    private Integer y_coord=0;
    public Coordinates(Integer x,Integer y){
        this.x_coord=x;
        this.y_coord=y;
    }
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
}
