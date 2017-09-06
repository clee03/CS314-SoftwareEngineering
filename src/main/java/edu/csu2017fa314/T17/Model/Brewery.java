package edu.csu2017fa314.T17.Model;
import java.util.HashMap;

public class Brewery{
  private HashMap info = new HashMap();

  public Brewery(){}
  public Brewery( String id, String name, String city,
                  double lat, double lon, double elv ){
    setID( id );
    setName( name );
    setCity( city );
    setLat( lat );
    setLon( lon );
    setElv( elv );
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

  public void setID( String id ){
    info.put( "ID", id );
  }
  public void setName( String name ){
    info.put( "Name", name );
  }
  public void setCity( String city ){
    info.put( "City", city );
  }
  public void setLat( double lat ){
    info.put( "Latitude", lat );
  }
  public void setLon( double lon ){
    info.put( "Longitude", lon );
  }
  public void setElv( double elv ){
    info.put( "Elevation", elv );
  }
}
