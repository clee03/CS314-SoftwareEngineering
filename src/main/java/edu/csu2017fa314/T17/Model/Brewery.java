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
    return info.toString();
  }

  @Override
  public boolean equals (Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Brewery)){
      return false;
    }
    Brewery b = (Brewery)o;
    
    if (get("id").equals(b.get("id"))
        && (Math.abs((double) get("latitude") - (double) b.get("latitude")) <= 0.01)
        && (Math.abs((double) get("longitude") - (double) b.get("longitude")) <= 0.01)) {
      return true;
    }
    return false;
  }

  public Object get (String id) {
    return info.get(id);
  }
  public HashMap getAll () {
    return info;
  }

  public void set ( String key, Object value ) {
    info.put(key, value);
  }

  public void setID( String id ){
    info.put( "id", id );
  }
  public void setName( String name ){
    info.put( "name", name );
  }
  public void setCity( String city ){
    info.put( "city", city );
  }
  public void setLat( double lat ){
    info.put( "latitude", lat );
  }
  public void setLon( double lon ){
    info.put( "longitude", lon );
  }
  public void setElv( double elv ){
    info.put( "elevation", elv );
  }
}
