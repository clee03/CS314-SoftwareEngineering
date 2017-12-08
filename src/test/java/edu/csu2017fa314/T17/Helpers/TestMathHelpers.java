package edu.csu2017fa314.T17.Helpers;

import static edu.csu2017fa314.T17.Helpers.MathHelpers.degToDecimal;
import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.Test;

public class TestMathHelpers {
  // Lat and Lon values from Avery Brewing Company location
  @Test
  public void testDegToDecimal() {
    assertEquals(-105.2048, degToDecimal("105°12'17.1\" W"), .01);
  }

  @Test
  public void testDegToDecimal2() {
    assertEquals(5.0, degToDecimal("5"), .01);
  }
  @Test
  public void testDefault() {
    new MathHelpers();
  }
}
