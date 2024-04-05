package ca.mcmaster.se2aa4.mazerunner;

public enum SQUARE {
    OPEN(' '),WALL('#');
    public final Character value;
    SQUARE(char value){
        this.value=value;
    }

    public static SQUARE valueOfSquare(char val) {
        System.out.println("VAL"+val);
        for (SQUARE s : values()) {
            if (s.value.equals(val)) {
                return s;
            }
        }
        throw new IllegalArgumentException("*Invalid Square*");
    }

}
