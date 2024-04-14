package ca.mcmaster.se2aa4.mazerunner;

/**
 * Abstract vetex class with general typing to decorate objects and encapsulate them to use as a
 * vertex in a graph. Since this is an abstract class, all runtime vertices must be extensions of this
 * and redefine equals and hashcode functions. They use general typing T to give each vertex its own
 * unique type based on the object that is contained within it. It also has a label that diffrentiates it
 * based on the defining value of its object. (i.e. object Coordinates(1,2) would have label (1,2))
 */

public abstract class Vertex<T> {
    private T obj;
    private String label;
    public Vertex(T obj){
        this.obj=obj;
        this.label=obj.toString();
    }
    /**
     * getters to access properties of the internal objects
     * @return the respective value
     */
    public String getLabel(){
        return this.label;
    }
    public T getObject(){
        return this.obj;
    }
    /**
     * abstract methods to be redefined which properly determine if 2 vertices are equal based on their
     * value, and to hash them (would need their actual value to determine proper values) 
     */
    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
