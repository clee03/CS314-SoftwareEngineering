package edu.csu2017fa314.T17.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBrewery{
  private Brewery b1;
  private Brewery b2;

  @Before
  public void setUp() throws Exception{
    b1 = new Brewery( "b99", "TestBrew", "TestCity",
                     99.99, 99.99, 5000 );
    b2 = new Brewery();
  }
  @Test
  public void testAccessors(){
    b2.setID( "b99" );
    b2.setName( "TestBrew" );
    b2.setCity( "TestCity" );
    b2.setLat( 99.99 );
    b2.setLon( 99.99 );
    b2.setElv( 5000 );
    assertEquals( b1.get("id"), "b99" );
    assertEquals( b1.get("name"), "TestBrew" );
    assertEquals( b1.get("city"), "TestCity" );
    assertEquals( (double) b1.get("latitude"), 99.99, .01 );
    assertEquals( (double) b1.get("longitude"), 99.99, .01 );
    assertEquals( (double) b1.get("elevation"), 5000, .01 );
  }
}
