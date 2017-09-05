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
    parseObj = new ParseCSV( "test.csv" );
  }
  @Test
  public void testHeaders(){
    ArrayList<String> tmp =
      new ArrayList<String>( Arrays.asList( "ID","Name","City","Latitude","Longitude","ElevationFt" ) );
    assertEquals( parseObj.getHeader(), tmp );
  }
  @Test
  public void testData(){
    
  }
}
