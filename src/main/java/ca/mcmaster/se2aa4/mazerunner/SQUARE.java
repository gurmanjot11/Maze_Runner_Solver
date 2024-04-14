package ca.mcmaster.se2aa4.mazerunner;
/**
 *  SQUARE enum that describes the state of a square in the maze. Can be either open(' ') or closed('#').
 *  Will be assigned a value upon creation.
 */

public enum SQUARE {
    OPEN(' '),WALL('#');
    private Character value;
    SQUARE(char value){
        this.value=value;
    }

    /**
     * static method to create a new square given the character value
     * @param val character (' ','#') representing new square's value. If the value DNE, throws error
     * @return a new square will value val that is correspondingly open/wall
     */
    public static SQUARE valueOfSquare(char val) {
        //System.out.print(val);
        for (SQUARE s : values()) {
            if (s.value.equals(val)) {
                return s;
            }
        }
        throw new IllegalArgumentException("*Invalid Square*");
    }
}
