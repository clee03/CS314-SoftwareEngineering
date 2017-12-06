package edu.csu2017fa314.T17.View;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestView {

    private View v = new View();

    @Before
    public void setUp() throws Exception {
        v.setTotalDistance(6);
    }

    @Test
    public void testSetTotalDistance() {
        v.setTotalDistance(7);
        assertEquals(7, v.getTotalDistance(), .01);
    }

    @Test
    public void testFileExtender() {
        assertEquals("test.t", v.fileExtender("test.g", "t"));
    }


}
