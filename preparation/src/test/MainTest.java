package test;
import main.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MainTest{
    @Test
    public void multiplcationOfZeroIntReturnsZero(){

       assertEquals(0,Main.mult(4, 0), "4 x 0 must be 0");
       assertEquals(0,Main.mult(0, 4), "0 x 4 must be 0");
       assertEquals(0,Main.mult(0, 0), "0 x 0 must be 0");

    }
}