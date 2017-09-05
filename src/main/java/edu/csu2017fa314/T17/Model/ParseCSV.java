package edu.csu2017fa314.T17.Model;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class ParseCSV{
  ArrayList<String> header;
  Brewery[] brewlist;

  public ParseCSV( String filename ) throws Exception{
    // This loads from the resources folder ( src/main/resources or src/test/resources )
    ClassLoader classLoader = this.getClass().getClassLoader();
    File loadFile = new File( classLoader.getResource( filename ).getFile() );
    BufferedReader in = new BufferedReader(
        new InputStreamReader( new FileInputStream( loadFile ) )
      );

    parseHeader( in.readLine() );

//    if( String[] colheader = in.readLine() != null ){
//      String line;
//      while( ( line = in.readLine() ) != null ){
//      }
//    }
  }

  private void parseHeader( String line ){
    header = new ArrayList<String>( Arrays.asList( line.split(",") ) );
  }

  public ArrayList<String> getHeader(){
    return header;
  }
}
