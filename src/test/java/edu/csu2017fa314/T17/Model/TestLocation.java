package edu.csu2017fa314.T17.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TestLocation {
  private Location b1;
  private Location b2;

  private HashMap hashMapSetup() {
    HashMap hm1 = new HashMap();
    hm1.put("code", "b99");
    hm1.put("name", "TestBrew");
    hm1.put("city", "TestCity");
    hm1.put("latitude", 99.99);
    hm1.put("longitude", 99.99);
    hm1.put("elevation", 5000);

    return hm1;
  }

  @Before
  public void setUp() throws Exception{
    b1 = new Location(hashMapSetup());
    b2 = new Location();
  }
  @Test
  public void testAccessors(){
    b2.set( "code", "b99" );
    b2.set( "name", "TestBrew" );
    b2.set( "city", "TestCity" );
    b2.set( "latitude", 99.99 );
    b2.set( "longitude", 99.99 );
    b2.set( "elevation", 5000 );
    assertEquals( b1.get("code"), "b99" );
    assertEquals( b1.get("name"), "TestBrew" );
    assertEquals( b1.get("city"), "TestCity" );
    assertEquals( (double) b1.get("latitude"), 99.99, .01 );
    assertEquals( (double) b1.get("longitude"), 99.99, .01 );
    assertEquals( b1.get("elevation"), 5000 );
  }
  @Test
  public void testToString(){
    HashMap testingMap = hashMapSetup();
    assertEquals(b1.getAll(), testingMap);
    assertEquals(b1.toString(), testingMap.toString());
  }
}
