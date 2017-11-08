package edu.csu2017fa314.T17.Model;

    import static org.junit.Assert.*;

    import org.junit.Before;
    import org.junit.Test;

public class TestDistance {
  private Distance d;


  @Before
  public void setUp() throws Exception {
    d = new Distance(Distance.unit.Kilometers);
  }


  @Test
  public void testRadians() {
    assertEquals(d.convertToRadians(180.0), Math.PI, .01);
  }

  @Test
  public void testDistance() {
    Brewery alpha = new Brewery("a1", "alpha", "alpha city",
        50.06639, -5.714722, 5000);
    Brewery beta = new Brewery("b1", "beta", "beta city",
        58.64389, -3.07, 5000);
    assertEquals(d.greatCircleDistance(alpha, beta), 968, 1);
  }
}
