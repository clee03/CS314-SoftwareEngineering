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

     st = new ShorterTrip(brews);
     assertEquals(st.pathDistanceBrews(st.computePath()), 202);

     st = new ShorterTrip(brews2);
     assertEquals(st.pathDistanceBrews(st.computePath()), 195);

     st = new ShorterTrip(brews3);
     assertEquals(st.pathDistanceBrews(st.computePath()), 143);

     st = new ShorterTrip(brews4);
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
}
