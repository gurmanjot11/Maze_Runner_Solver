package ca.mcmaster.se2aa4.mazerunner;

public abstract class Vertex<T> {
    private T obj;
    private String label;
    public Vertex(T obj){
        this.obj=obj;
        this.label=obj.toString();
    }
    public String getLabel(){
        return this.label;
    }
    public T getObject(){
        return this.obj;
    }
    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
