package edu.csu2017fa314.T17.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDistance {
    private Distance d;


    @Before
    public void setUp() throws Exception{
        d = new Distance();
    }


    @Test
    public void testRadians()
    {
        assertEquals(d.convertToRadians(180.0), Math.PI, .01);
    }
}
