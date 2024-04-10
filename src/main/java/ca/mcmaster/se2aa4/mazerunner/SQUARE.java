package ca.mcmaster.se2aa4.mazerunner;

public enum SQUARE {
    OPEN(' '),WALL('#');
    private Character value;
    SQUARE(char value){
        this.value=value;
    }

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
