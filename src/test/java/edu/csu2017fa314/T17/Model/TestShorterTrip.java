package edu.csu2017fa314.T17.Model;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestShorterTrip {
  private ShorterTrip st;

   @Test
    public void testShorterDistance() {
     Distance dis = new Distance();
     Brewery a = new Brewery("Equinox Brewing", "acwatson", "Fort Collins",
          40.5881, 105.0739, 4988);
     Brewery b = new Brewery("Avery Brewing Company", "cwesterm", "Boulder",
          40.0625, 105.2047, 5188);
     Brewery c = new Brewery("Two22 Brew", "abee", "Centennial",
          39.6352, 104.7588, 5872);
     Brewery d = new Brewery("Mad Jacks Mountain Brewery", "abellend", "Bailey",
          39.4013, 105.4769, 9580);
     Brewery e = new Brewery("Dry Dock Brewing Company", "idkirk", "Aurora",
         39.6525, 104.8125, 5033);

     ArrayList<Brewery> brews = new ArrayList<>();
     brews.add(a);
     brews.add(b);
     brews.add(c);
     brews.add(d);
     brews.add(e);

     ArrayList<Brewery> brews2 = new ArrayList<>();
     brews2.add(a);
     brews2.add(b);
     brews2.add(c);
     brews2.add(d);

     ArrayList<Brewery> brews3 = new ArrayList<>();
     brews3.add(a);
     brews3.add(b);
     brews3.add(c);

     ArrayList<Brewery> brews4 = new ArrayList<>();
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
     Brewery a = new Brewery("1", "a", "Happy Town",
         40.0, 105.0, 4988);
     Brewery b = new Brewery("2", "b", "Happy Town",
         39.0, 105.0, 4988);
     Brewery c = new Brewery("3", "c", "Happy Town",
         40.0, 104.0, 4988);
     Brewery d = new Brewery("4", "d", "Happy Town",
         39.0, 104.0, 4988);
     //this is the data list to initialize the milage table needed for two opt
     ArrayList<Brewery> brews = new ArrayList<>();
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
     //hexagon for the test cases in the slides
     Brewery a = new Brewery("1", "a", "Happy Town",
         -0.5, -1.0, 4988);
     Brewery b = new Brewery("2", "b", "Happy Town",
         0.5, -1.0, 4988);
     Brewery c = new Brewery("3", "c", "Happy Town",
         1.0, 0.0, 4988);
     Brewery d = new Brewery("4", "d", "Happy Town",
         0.5, 1.0, 4988);
     Brewery e = new Brewery("5", "e", "Happy Town",
         -0.5, 1.0, 4988);
     Brewery f = new Brewery("6", "f", "Happy Town",
         -1.0, 0, 4988);

     ArrayList<Brewery> solution = new ArrayList<>(6);
     solution.add(a);
     solution.add(b);
     solution.add(c);
     solution.add(d);
     solution.add(e);
     solution.add(f);
     solution.add(a);
     ArrayList<Brewery> path1 = new ArrayList<>(6);
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
   @Test
  public void testThreeOpt2() {
    //hexagon for the test cases in the slides
    Brewery a = new Brewery("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Brewery b = new Brewery("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Brewery c = new Brewery("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Brewery d = new Brewery("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Brewery e = new Brewery("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Brewery f = new Brewery("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Brewery> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Brewery> path2 = new ArrayList<>(6);
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
    Brewery a = new Brewery("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Brewery b = new Brewery("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Brewery c = new Brewery("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Brewery d = new Brewery("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Brewery e = new Brewery("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Brewery f = new Brewery("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Brewery> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Brewery> path3 = new ArrayList<>(6);
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
    Brewery a = new Brewery("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Brewery b = new Brewery("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Brewery c = new Brewery("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Brewery d = new Brewery("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Brewery e = new Brewery("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Brewery f = new Brewery("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Brewery> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Brewery> path4 = new ArrayList<>(6);
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
    Brewery a = new Brewery("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Brewery b = new Brewery("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Brewery c = new Brewery("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Brewery d = new Brewery("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Brewery e = new Brewery("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Brewery f = new Brewery("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Brewery> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Brewery> path5 = new ArrayList<>(6);
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
    Brewery a = new Brewery("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Brewery b = new Brewery("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Brewery c = new Brewery("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Brewery d = new Brewery("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Brewery e = new Brewery("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Brewery f = new Brewery("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Brewery> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Brewery> path6 = new ArrayList<>(6);
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
    Brewery a = new Brewery("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Brewery b = new Brewery("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Brewery c = new Brewery("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Brewery d = new Brewery("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Brewery e = new Brewery("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Brewery f = new Brewery("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Brewery> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Brewery> path7 = new ArrayList<>(6);
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
    Brewery a = new Brewery("1", "a", "Happy Town",
        -0.5, -1.0, 4988);
    Brewery b = new Brewery("2", "b", "Happy Town",
        0.5, -1.0, 4988);
    Brewery c = new Brewery("3", "c", "Happy Town",
        1.0, 0.0, 4988);
    Brewery d = new Brewery("4", "d", "Happy Town",
        0.5, 1.0, 4988);
    Brewery e = new Brewery("5", "e", "Happy Town",
        -0.5, 1.0, 4988);
    Brewery f = new Brewery("6", "f", "Happy Town",
        -1.0, 0, 4988);

    ArrayList<Brewery> solution = new ArrayList<>(6);
    solution.add(a);
    solution.add(b);
    solution.add(c);
    solution.add(d);
    solution.add(e);
    solution.add(f);
    solution.add(a);
    ArrayList<Brewery> path8 = new ArrayList<>(6);
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

}
