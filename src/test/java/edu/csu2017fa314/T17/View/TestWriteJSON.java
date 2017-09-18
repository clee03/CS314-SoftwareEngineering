package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestWriteJSON {
  private WriteJSON wj;

  @Before
  public void setUp() throws Exception {
    wj = new WriteJSON();
  }

  @Test
  public void testWriteJson() {

    Brewery a = new Brewery("a1", "alpha", "alpha city",
        50.06639, -5.714722, 5000);
    Brewery b = new Brewery("b1", "beta", "beta city",
        58.64389, -3.07, 5000);
    Brewery c = new Brewery("c1", "charlie", "charile city",
        50.16639, -5.714721, 5000);
    Brewery d = new Brewery("d1", "delta", "delta city",
        50.16649, -5.714725, 5000);

    ArrayList<Brewery> brews = new ArrayList<>();
    brews.add(a);
    brews.add(b);
    brews.add(c);
    brews.add(d);
    wj.formatJSON(brews, "data/test.csv");
  }
}
