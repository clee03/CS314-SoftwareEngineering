package edu.csu2017fa314.T17.Model;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestShorterTrip {
  private ShorterTrip st;

   @Test
    public void testShorterDistance() {
     Location a = new Location();
     a.set("latitude", 40.5881);
     a.set("longitude", 105.0739);

     Location b = new Location();
     b.set("latitude", 40.0625);
     b.set("longitude", 105.2047);

     Location c = new Location();
     c.set("latitude", 39.6352);
     c.set("longitude", 104.7588);

     Location d = new Location();
     d.set("latitude", 39.4013);
     d.set("longitude", 105.4769);

     Location e = new Location();
     e.set("latitude", 39.6525);
     e.set("longitude", 104.8125);

     ArrayList<Location> brews = new ArrayList<>();
     brews.add(a);
     brews.add(b);
     brews.add(c);
     brews.add(d);
     brews.add(e);

     ArrayList<Location> brews2 = new ArrayList<>();
     brews2.add(a);
     brews2.add(b);
     brews2.add(c);
     brews2.add(d);

     ArrayList<Location> brews3 = new ArrayList<>();
     brews3.add(a);
     brews3.add(b);
     brews3.add(c);

     ArrayList<Location> brews4 = new ArrayList<>();
     brews4.add(b);
     brews4.add(c);

     st = new ShorterTrip(brews, ShorterTrip.type.NearestNeighbor, Distance.unit.Miles);
     assertEquals(st.pathDistanceBrews(st.computePath()), 202);

     st = new ShorterTrip(brews2, ShorterTrip.type.NearestNeighbor, Distance.unit.Miles);
     assertEquals(st.pathDistanceBrews(st.computePath()), 195);

     st = new ShorterTrip(brews3, ShorterTrip.type.NearestNeighbor, Distance.unit.Miles);
     assertEquals(st.pathDistanceBrews(st.computePath()), 143);

     st = new ShorterTrip(brews4, ShorterTrip.type.NearestNeighbor, Distance.unit.Miles);
     assertEquals(st.pathDistanceBrews(st.computePath()), 76);
   }

   @Test
   public void testTwoOptSwap(){
     ShorterTrip st = new ShorterTrip();
     int[] test = new int[5];
     test[0] = 1;
     test[1] = 2;
     test[2] = 3;
     test[3] = 4;
     test[4] = 5;
     test = st.twoOptSwap(test, 0,4);
     int[] solution = new int[5];
     solution[0] = 5;
     solution[1] = 4;
     solution[2] = 3;
     solution[3] = 2;
     solution[4] = 1;
     for(int i = 0; i < solution.length; i++){
       assertEquals(test[i], solution[i]);
     }
   }

   @Test
   public void testTwoOpt(){
     Location a = new Location();
     a.set("latitude", 40.0);
     a.set("longitude", 105.0);

     Location b = new Location();
     b.set("latitude", 39.0);
     b.set("longitude", 105.0);

     Location c = new Location();
     c.set("latitude", 40.0);
     c.set("longitude", 104.0);

     Location d = new Location();
     d.set("latitude", 39.0);
     d.set("longitude", 104.0);

     //this is the data list to initialize the milage table needed for two opt
     ArrayList<Location> brews = new ArrayList<>();
     brews.add(a);
     brews.add(b);
     brews.add(c);
     brews.add(d);
     //path creates an crossing point
     int[] path = new int[5];
     path[0] = 2;
     path[1] = 1;
     path[2] = 0;
     path[3] = 3;
     path[4] = 2;
     //solution goes c,a,b,d //i have a graph; this makes sense
     int[] solution = new int[5];
     solution[0] = 2;
     solution[1] = 0;
     solution[2] = 1;
     solution[3] = 3;
     solution[4] = 2;
     ShorterTrip st = new ShorterTrip(brews);
     path = st.twoOpt(path);

     for(int i = 0; i < solution.length; i++){
       assertEquals(path[i], solution[i]);
     }
   }

   @Test
   public void testThreeOpt1(){
     Location a = new Location();
     a.set("latitude", -0.5);
     a.set("longitude", -1.0);

     Location b = new Location();
     b.set("latitude", 0.5);
     b.set("longitude", -1.0);

     Location c = new Location();
     c.set("latitude", 1.0);
     c.set("longitude", 0.0);

     Location d = new Location();
     d.set("latitude", 0.5);
     d.set("longitude", 1.0);

     Location e = new Location();
     e.set("latitude", -0.5);
     e.set("longitude", 1.0);

     Location f = new Location();
     f.set("latitude", -1.0);
     f.set("longitude", 0.0);

     ArrayList<Location> solution = new ArrayList<>(6);
     solution.add(a);
     solution.add(b);
     solution.add(c);
     solution.add(d);
     solution.add(e);
     solution.add(f);
     solution.add(a);
     ArrayList<Location> path1 = new ArrayList<>(6);
     path1.add(a);
     path1.add(b);
     path1.add(c);
     path1.add(d);
     path1.add(e);
     path1.add(f);
     ShorterTrip st = new ShorterTrip(path1, ShorterTrip.type.ThreeOpt);
     path1 = st.computePath();
     assertEquals(solution, path1);
   }
   /*
   @Test
  public void testThreeOpt2() {
    //hexagon for the test cases in the slides
    Location a = new Location("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Location b = new Location("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Location c = new Location("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Location d = new Location("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Location e = new Location("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Location f = new Location("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Location> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Location> path2 = new ArrayList<>(6);
    path2.add(a);
    path2.add(b);
    path2.add(c);
    path2.add(d);
    path2.add(e);
    path2.add(f);
    st = new ShorterTrip(path2, ShorterTrip.type.ThreeOpt);
    path2 = st.computePath();
    assertEquals(solution, path2);
  }
  @Test
  public void testThreeOpt3() {
    //hexagon for the test cases in the slides
    Location a = new Location("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Location b = new Location("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Location c = new Location("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Location d = new Location("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Location e = new Location("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Location f = new Location("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Location> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Location> path3 = new ArrayList<>(6);
    path3.add(a);
    path3.add(b);
    path3.add(c);
    path3.add(d);
    path3.add(e);
    path3.add(f);
    st = new ShorterTrip(path3, ShorterTrip.type.ThreeOpt);
    path3 = st.computePath();
    assertEquals(solution, path3);
  }
  @Test
  public void testThreeOpt4() {
    //hexagon for the test cases in the slides
    Location a = new Location("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Location b = new Location("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Location c = new Location("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Location d = new Location("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Location e = new Location("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Location f = new Location("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Location> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Location> path4 = new ArrayList<>(6);
    path4.add(a);
    path4.add(b);
    path4.add(c);
    path4.add(d);
    path4.add(e);
    path4.add(f);
    st = new ShorterTrip(path4, ShorterTrip.type.ThreeOpt);
    path4 = st.computePath();
    assertEquals(solution, path4);
  }
  @Test
  public void testThreeOpt5() {
    //hexagon for the test cases in the slides
    Location a = new Location("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Location b = new Location("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Location c = new Location("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Location d = new Location("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Location e = new Location("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Location f = new Location("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Location> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Location> path5 = new ArrayList<>(6);
    path5.add(a);
    path5.add(b);
    path5.add(c);
    path5.add(d);
    path5.add(e);
    path5.add(f);
    st = new ShorterTrip(path5, ShorterTrip.type.ThreeOpt);
    path5 = st.computePath();
    assertEquals(solution, path5);
  }
  @Test
  public void testThreeOpt6() {
    //hexagon for the test cases in the slides
    Location a = new Location("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Location b = new Location("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Location c = new Location("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Location d = new Location("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Location e = new Location("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Location f = new Location("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Location> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Location> path6 = new ArrayList<>(6);
    path6.add(a);
    path6.add(b);
    path6.add(c);
    path6.add(d);
    path6.add(e);
    path6.add(f);
    st = new ShorterTrip(path6, ShorterTrip.type.ThreeOpt);
    path6 = st.computePath();
    assertEquals(solution, path6);
  }
  @Test
  public void testThreeOpt7() {
    //hexagon for the test cases in the slides
    Location a = new Location("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Location b = new Location("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Location c = new Location("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Location d = new Location("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Location e = new Location("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Location f = new Location("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Location> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Location> path7 = new ArrayList<>(6);
    path7.add(a);
    path7.add(b);
    path7.add(c);
    path7.add(d);
    path7.add(e);
    path7.add(f);
    st = new ShorterTrip(path7, ShorterTrip.type.ThreeOpt);
    path7 = st.computePath();
    assertEquals(solution, path7);
  }
  @Test
  public void testThreeOpt8() {
    //hexagon for the test cases in the slides
    Location a = new Location("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Location b = new Location("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Location c = new Location("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Location d = new Location("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Location e = new Location("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Location f = new Location("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Location> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Location> path8 = new ArrayList<>(6);
    path8.add(a);
    path8.add(b);
    path8.add(c);
    path8.add(d);
    path8.add(e);
    path8.add(f);
    st = new ShorterTrip(path8, ShorterTrip.type.ThreeOpt);
    path8 = st.computePath();
    assertEquals(solution, path8);
  }
  */
}
