package edu.csu2017fa314.T17.Model;
import java.util.HashMap;

public class Brewery{
  private HashMap info = new HashMap();

  public Brewery( String id, String name, String city,
                  double lat, double lon, double elv ){
    info.put( "ID",  id );
    info.put( "Name", name );
    info.put( "City", city );
    info.put( "Latitude", lat );
    info.put( "Longitude", lon );
    info.put( "Elevation", elv );
  }

  public String getID(){
    return (String)info.get( "ID" );
  }
  public String getName(){
    return (String)info.get( "Name" );
  }
  public String getCity(){
    return (String)info.get( "City" );
  }
  public double getLat(){
    return (double)info.get( "Latitude" );
  }
  public double getLon(){
    return (double)info.get( "Longitude" );
  }
  public double getElv(){
    return (double)info.get( "Elevation" );
  }
}
