package edu.csu2017fa314.T17.Model;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class ParseCSV{
  ArrayList<String> header;
  ArrayList<Brewery> brewList;

  public ParseCSV( String filename ) throws Exception{
    // This loads from the resources folder ( src/main/resources or src/test/resources )
    ClassLoader classLoader = this.getClass().getClassLoader();
    File loadFile = new File( classLoader.getResource( filename ).getFile() );
    BufferedReader in = new BufferedReader(
        new InputStreamReader( new FileInputStream( loadFile ) )
      );

    String line;
    if( ( line = in.readLine() ) != null ){
      parseHeader( line.toLowerCase() );
      while( ( line = in.readLine() ) != null ){
        parseLine( line );
      }
    }
  }
  
  /**
   *  Helper function to parse the first line of data
   *  @param line       the line to be parsed ( comma seperated )
   *  @return void      result added to the internal header list
   */
  private void parseHeader( String line ){
    header = new ArrayList<String>( Arrays.asList( line.split(",") ) );
  }

  /**
   *  Helper function to parse a line in the brew file according to the header
   *  @param line       the line to be parsed ( comma seperated )
   *  @return void      added to internal brew list
   */
  private void parseLine( String line ){
  }

  /**
   *  Helper function to exchange lat/lon into their decimal counterparts
   *  @param degrees    the string that will be parsed
   *  @return double    a decimal value representing the string passed as a parameter
   */
  public double degToDecimal( String degrees ){
    degrees = degrees.replace( " ", "" );
    String[] parts = degrees.split( "[°'\"]" );

    double total = 0;
    switch( parts.length ){
      case 4:
        total += ( Double.parseDouble( parts[2] ) / 3600 );
      case 3:
        total += ( Double.parseDouble( parts[1] ) / 60 );
      case 2:
        total += ( Double.parseDouble( parts[0] ) );
        String cardinal = parts[ parts.length - 1 ];
        if( cardinal.equals("W") || cardinal.equals("S") )
          total *= -1;
        return total;
      default:
        return Double.parseDouble( parts[0] );
    }
  }

  public ArrayList<String> getHeader(){
    return header;
  }
}
