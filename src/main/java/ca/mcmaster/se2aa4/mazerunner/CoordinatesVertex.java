package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;

/**
 * Extends abstract Vertex<Coordinates> to specify the abstract methods. Refer to vertex for non-abstract
 * methods. Essentially, a wrapper/decorator for coordinates objects to use as vertices in graph
 */

public class CoordinatesVertex extends Vertex<Coordinates>{
    public CoordinatesVertex(Coordinates obj) {
        super(obj);
    }

    /**
     * Determines whether 2 vertices are equal. Only equal if they are both coordinates vertices objects and
     * if they share the same internal coordinate
     * @param o object o which will be cast to a coordinate in this case, represents the internal object
     * @return true if they are equivalent vertices, else false
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof CoordinatesVertex)){
            return false;
        }
        CoordinatesVertex cv= (CoordinatesVertex) o;
        return this.getLabel().equals(cv.getLabel());
    }

    /**
     * Overrides hashcode to provide unique one based on the coordinates x and y values, and so that the same
     * coordinates map to each other
     * @return integer hash code based on x,y of coords
     */
    @Override
    public int hashCode() {
        Coordinates coords = this.getObject();
        Integer x = coords.getX();
        Integer y = coords.getY();
        return Objects.hash(x,y);
    }
}
