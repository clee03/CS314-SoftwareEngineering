package edu.csu2017fa314.T17.Server;

import static org.junit.Assert.*;

import edu.csu2017fa314.T17.Model.Brewery;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestServerResponse {
  ServerResponse serverResponse;

  @Before
  public void setUp() throws Exception{
    Brewery b1 = new Brewery("Avery Brewing Company", "cwesterm", "Boulder",
        40.0625, 105.2047, 5188);
    Brewery b2 = new Brewery("Dry Dock Brewing Company", "idkirk", "Aurora",
        39.6525, 104.8125, 5033);
    ArrayList<Brewery> brews = new ArrayList<>();
    brews.add(b1);
    brews.add(b2);
    serverResponse = new ServerResponse("brews.svg", brews);
  }
/*
  @Test
  public void testToString(){
    assertEquals("ServerResponse{svg='brews.svg', " +
            "locations=[{elevation=5188.0, city=Boulder, latitude=40.0625, name=cwesterm, code=Avery Brewing Company, longitude=105.2047}, " +
                       "{elevation=5033.0, city=Aurora, latitude=39.6525, name=idkirk, code=Dry Dock Brewing Company, longitude=104.8125}]}",
    serverResponse.toString());
  }
*/
}
