package edu.csu2017fa314.T17.Model;

    import static org.junit.Assert.assertEquals;

    import org.junit.Before;
    import org.junit.Test;

public class TestDistance {
  private Distance testDistance;
  private Location alpha;
  private Location beta;

  @Before
  public void setUp() throws Exception {
    testDistance = new Distance(Distance.unit.Kilometers);
    alpha = new Location();
    alpha.set("latitude", 50.06639);
    alpha.set("longitude", -5.714722);
    beta = new Location();
    beta.set("latitude", 58.64389);
    beta.set("longitude", -3.07);
  }

  @Test
  public void testDefault() throws Exception {
    new Distance();
  }
  
  @Test
  public void testRadians() {
    assertEquals(testDistance.convertToRadians(180.0), Math.PI, .01);
  }

  @Test
  public void testDistance() {
    assertEquals(testDistance.greatCircleDistance(alpha, beta), 968, 1);
  }
}
