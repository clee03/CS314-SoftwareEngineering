package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TeswriteJSON {
  private writeJSON wj;

  @Before
  public void setUp() throws Exception {
    wj = new writeJSON();
  }

  @Test
  public void testwriteJson() {
    Brewery a = new Brewery("a1", "alpha", "alpha city",
        50.06639, -5.714722, 5000);
    Brewery b = new Brewery("b1", "beta", "beta city",
        58.64389, -3.07, 5000);
    assertEquals(wj.formatJSON(a, b);
  }


}
