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
}
