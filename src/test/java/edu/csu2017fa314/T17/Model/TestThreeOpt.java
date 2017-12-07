package edu.csu2017fa314.T17.Model;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class TestThreeOpt {
  private ArrayList<Location> solution;
  private Location a, b, c, d, e, f;
  private ShorterTrip st;
  @Before
  public void setUp3Opt(){
    //hexagon for the test cases in the slides
    a = new Location();
    a.set("latitude", -0.5);
    a.set("longitude", -1.0);

    b = new Location();
    b.set("latitude", 0.5);
    b.set("longitude", -1.0);

    c = new Location();
    c.set("latitude", 1.0);
    c.set("longitude", 0.0);

    d = new Location();
    d.set("latitude", 0.5);
    d.set("longitude", 1.0);

    e = new Location();
    e.set("latitude", -0.5);
    e.set("longitude", 1.0);

    f = new Location();
    f.set("latitude", -1.0);
    f.set("longitude", 0.0);
    solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
  }
  @Test
  public void testThreeOpt2() {
    ArrayList<Location> path2 = new ArrayList<>(6);
    path2.add(a);
    path2.add(c);
    path2.add(b);
    path2.add(d);
    path2.add(e);
    path2.add(f);
    st = new ShorterTrip(path2, ShorterTrip.type.ThreeOpt);
    path2 = st.computePath();
    assertEquals(solution, path2);
  }
  @Test
  public void testThreeOpt3() {
    ArrayList<Location> path3 = new ArrayList<>(6);
    path3.add(a);
    path3.add(b);
    path3.add(c);
    path3.add(e);
    path3.add(d);
    path3.add(f);
    st = new ShorterTrip(path3, ShorterTrip.type.ThreeOpt);
    path3 = st.computePath();
    assertEquals(solution, path3);
  }
  @Test
  public void testThreeOpt4() {
    ArrayList<Location> path4 = new ArrayList<>(6);
    path4.add(a);
    path4.add(e);
    path4.add(d);
    path4.add(c);
    path4.add(b);
    path4.add(f);
    st = new ShorterTrip(path4, ShorterTrip.type.ThreeOpt);
    path4 = st.computePath();
    assertEquals(solution, path4);
  }
  @Test
  public void testThreeOpt5() {
    ArrayList<Location> path5 = new ArrayList<>(6);
    path5.add(a);
    path5.add(c);
    path5.add(b);
    path5.add(e);
    path5.add(d);
    path5.add(f);
    st = new ShorterTrip(path5, ShorterTrip.type.ThreeOpt);
    path5 = st.computePath();
    assertEquals(solution, path5);
  }
  @Test
  public void testThreeOpt6() {
    ArrayList<Location> path6 = new ArrayList<>(6);
    path6.add(a);
    path6.add(d);
    path6.add(e);
    path6.add(c);
    path6.add(b);
    path6.add(f);
    st = new ShorterTrip(path6, ShorterTrip.type.ThreeOpt);
    path6 = st.computePath();
    assertEquals(solution, path6);
  }
  @Test
  public void testThreeOpt7() {
    ArrayList<Location> path7 = new ArrayList<>(6);
    path7.add(a);
    path7.add(e);
    path7.add(d);
    path7.add(b);
    path7.add(c);
    path7.add(f);
    st = new ShorterTrip(path7, ShorterTrip.type.ThreeOpt);
    path7 = st.computePath();
    assertEquals(solution, path7);
  }
  @Test
  public void testThreeOpt8() {
    ArrayList<Location> path8 = new ArrayList<>(6);
    path8.add(a);
    path8.add(d);
    path8.add(e);
    path8.add(b);
    path8.add(c);
    path8.add(f);
    st = new ShorterTrip(path8, ShorterTrip.type.ThreeOpt);
    path8 = st.computePath();
    assertEquals(solution, path8);
  }

}
