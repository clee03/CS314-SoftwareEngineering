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
  }
