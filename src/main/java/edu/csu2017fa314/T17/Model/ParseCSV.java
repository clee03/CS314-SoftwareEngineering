package edu.csu2017fa314.T17.Model;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class ParseCSV{
  ArrayList<String> header;
  ArrayList<Brewery> brewList;

  public ParseCSV( String filename ) throws Exception{
    brewList = new ArrayList<Brewery>();

    String line;
    BufferedReader br = new BufferedReader( new FileReader( filename ) );

    if( ( line = br.readLine() ) != null ){
      parseHeader( line.toLowerCase() );
      while( ( line = br.readLine() ) != null ){
        parseLine( line );
      }
    }
   
    br.close();
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
    Brewery tmpBrew = new Brewery();
    String[] splitLine = line.split( "," );
    ArrayList<String> values = new ArrayList<String>();
    for( int i = 0; i < splitLine.length; i++ ){
      values.add( splitLine[i].trim() );
    }

    // Loop through the header list and assign the values acording to the split line
    for( int i = 0; i < header.size(); i++ ){
      switch( header.get( i ) ){
        case "id":
          tmpBrew.setID( values.get( i ) );
          break;
        case "name":
          tmpBrew.setName( values.get( i ));
          break;
        case "city":
          tmpBrew.setCity( values.get( i ) );
          break;
        case "latitude":
          tmpBrew.setLat( degToDecimal( values.get( i ) ) );
          break;
        case "longitude":
          tmpBrew.setLon( degToDecimal( values.get( i ) ) );
          break;
        case "elevationft":
          tmpBrew.setElv( Double.parseDouble( values.get( i ) ) );
          break;
      }
    }

    brewList.add( tmpBrew );
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
  public ArrayList<Brewery> getBrewerys(){
    return brewList;
  }
}
