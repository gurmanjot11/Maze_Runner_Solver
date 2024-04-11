package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;

public class CoordinatesVertex extends Vertex<Coordinates>{
    public CoordinatesVertex(Coordinates obj) {
        super(obj);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof CoordinatesVertex)){
            return false;
        }
        CoordinatesVertex cv= (CoordinatesVertex) o;
        return this.getLabel().equals(cv.getLabel());
    }

    @Override
    public int hashCode() {
        Coordinates coords = this.getObject();
        Integer x = coords.getX();
        Integer y = coords.getY();
        return Objects.hash(x,y);
    }
}
