package edu.csu2017fa314.T17.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.ArrayList;

public class TestParseCSV{
  private ParseCSV parseObj;

  @Before
  public void setUp() throws Exception{
    parseObj = new ParseCSV( "data/test.csv" );
  }
  @Test
  public void testHeaders(){
    ArrayList<String> tmp =
      new ArrayList<String>( Arrays.asList( "id","name","city","latitude","longitude","elevationft" ) );
    assertEquals( parseObj.getHeader(), tmp );
  }
  @Test
  public void testDegreeConvert(){
    String testString1 = "106\u00B049'43.24\" W";
    String testString2 = "39°38' N";
    String testString3 = "40.98° W";
    String testString4 = "-28.39";
    assertEquals( parseObj.degToDecimal( testString1 ), -106.829, 0.001 );   
    assertEquals( parseObj.degToDecimal( testString2 ), 39.633, 0.001 );
    assertEquals( parseObj.degToDecimal( testString3 ), -40.98, 0.001 );
    assertEquals( parseObj.degToDecimal( testString4 ), -28.39, 0.001 );
  }
  @Test
  public void testBrewerys(){
    Brewery b0 = new Brewery("abee", "Two22 Brew", "Centennial",
                              39.63527777777778, -104.75888888888889, 5872.0 );
    Brewery b1 = new Brewery("abellend", "Mad Jacks Mountain Brewery", "Bailey",
                              39.40138888888889, -105.47694444444444, 9580.0 );
    Brewery b2 = new Brewery("acwatson", "Equinox Brewing", "Fort Collins",
                              40.588055555555556, -105.07388888888889, 4988.0 );
    ArrayList<Brewery> brews = parseObj.getBrewerys();
    assertEquals( brews.get(0), b0 );
    assertEquals( brews.get(1), b1 );
    assertEquals( brews.get(2), b2 );
  }
  @Test
  public void testHeaderOrder(){
    Brewery b0 = new Brewery("abee", "Two22 Brew", "Centennial",
                              39.63527777777778, -104.75888888888889, 5872.0 );
    Brewery b1 = new Brewery("abellend", "Mad Jacks Mountain Brewery", "Bailey",
                              39.40138888888889, -105.47694444444444, 9580.0 );
    Brewery b2 = new Brewery("acwatson", "Equinox Brewing", "Fort Collins",
                              40.588055555555556, -105.07388888888889, 4988.0 );
    ArrayList<Brewery> brews = parseObj.getBrewerys();
    assertEquals( brews.get(0), b0 );
    assertEquals( brews.get(1), b1 );
    assertEquals( brews.get(2), b2 );
  }
}
