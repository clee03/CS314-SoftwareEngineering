package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestwriteJSON {
  private writeJSON wj;

  @Before
  public void setUp() throws Exception {
    wj = new writeJSON();
  }

  @Test
  public void testwriteJson() {

    Brewery a = new Brewery("a1", "alpha", "alpha city",
        50.06639, -5.714722, 5000);
    Brewery b = new Brewery("b1", "beta", "beta city",
        58.64389, -3.07, 5000);
    Brewery c = new Brewery("c1", "charlie", "charile city",
        50.16639, -5.714721, 5000);

    ArrayList<Brewery> brews = new ArrayList<>();
    brews.add(a);
    brews.add(b);
    brews.add(c);

    wj.formatJSON(brews);
  }


}
