package edu.csu2017fa314.T17.Model;

    import static org.junit.Assert.*;

    import org.junit.Before;
    import org.junit.Test;

public class TestDistance {
  private Distance d;
  private Location alpha;
  private Location beta;

  @Before
  public void setUp() throws Exception {
    d = new Distance(Distance.unit.Kilometers);
    alpha = new Location();
    alpha.set("latitude", 50.06639);
    alpha.set("longitude", -5.714722);
    beta = new Location();
    beta.set("latitude", 58.64389);
    beta.set("longitude", -3.07);
  }

  @Test
  public void testRadians() {
    assertEquals(d.convertToRadians(180.0), Math.PI, .01);
  }

  @Test
  public void testDistance() {
    assertEquals(d.greatCircleDistance(alpha, beta), 968, 1);
  }
}
