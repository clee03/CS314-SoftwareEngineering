package edu.csu2017fa314.T17.Model;
import java.util.HashMap;

public class Brewery{
  private HashMap info = new HashMap();

  public Brewery(){
    set( "id", "None" );
    set( "latitude", 0 );
    set( "longitude", 0 );
  }
  public Brewery( String id, String name, String city,
                  double lat, double lon, double elv ){
    set( "id", id );
    set( "name", name );
    set( "city", city );
    set( "latitude", lat );
    set( "longitude", lon );
    set( "elevation", elv );
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
}
