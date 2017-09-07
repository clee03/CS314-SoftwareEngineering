package edu.csu2017fa314.T17.Model;
import java.util.HashMap;

public class Brewery{
  private HashMap info = new HashMap();

  public Brewery(){
    setID( "None" );
    setName( "None" );
    setCity( "None" );
    setLat( 0 );
    setLon( 0 );
    setElv( 0 );
  }
  public Brewery( String id, String name, String city,
                  double lat, double lon, double elv ){
    setID( id );
    setName( name );
    setCity( city );
    setLat( lat );
    setLon( lon );
    setElv( elv );
  }

  @Override
  public String toString(){
    return getID() + " " + getName() + " " + getCity() + " " +
           getLat() + " " + getLon() + " " + getElv();
  }
  @Override
  public boolean equals( Object o ){
    if (o == this) {
      return true;
    }
    if (!(o instanceof Brewery)){
      return false;
    }
    Brewery b = (Brewery)o;
    
    if (getID().equals(b.getID())&&
        getName().equals(b.getName())&&
        getCity().equals(b.getCity())&&
        (Math.abs( getLat() - b.getLat() ) <= 0.01)&&
        (Math.abs( getLon() - b.getLon() ) <= 0.01)&&
        (Math.abs( getElv() - b.getElv() ) <= 0.01)
        ){
      return true;
    }
    return false;
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
