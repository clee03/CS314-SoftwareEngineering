package edu.csu2017fa314.T17.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBrewery{
  private Brewery b;

  @Before
  public void setUp() throws Exception{
    b = new Brewery( "b99", "TestBrew", "TestCity",
                     99.99, 99.99, 5000 );
  }
  @Test
  public void testAccessors(){
    assertEquals( b.getID(), "b99" );
    assertEquals( b.getName(), "TestBrew" );
    assertEquals( b.getCity(), "TestCity" );
    assertEquals( b.getLat(), 99.99, .01 );
    assertEquals( b.getLon(), 99.99, .01 );
    assertEquals( b.getElv(), 5000, .01 );
  }
}
