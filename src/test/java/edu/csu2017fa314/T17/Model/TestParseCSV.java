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
      new ArrayList<String>( Arrays.asList( "id","name","city","latitude","longitude","elevationft" ) );
    assertEquals( parseObj.getHeader(), tmp );
  }
  @Test
  public void testData(){
    String testString1 = "106\u00B049'43.24\" W";
    String testString2 = "39°38' N";
    String testString3 = "40.98° W";
    String testString4 = "-28.39";
    assertEquals( parseObj.degToDecimal( testString1 ), -106.829, 0.001 );   
    assertEquals( parseObj.degToDecimal( testString2 ), 39.633, 0.001 );
    assertEquals( parseObj.degToDecimal( testString3 ), -40.98, 0.001 );
    assertEquals( parseObj.degToDecimal( testString4 ), -28.39, 0.001 );
  }
}
