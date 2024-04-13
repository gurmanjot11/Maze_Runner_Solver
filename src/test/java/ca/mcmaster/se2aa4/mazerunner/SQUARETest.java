package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SQUARETest {
    @Test
    public void valueOfSquare(){
        SQUARE s = SQUARE.valueOfSquare(' ');
        assertEquals(s,SQUARE.OPEN);
    }
}
