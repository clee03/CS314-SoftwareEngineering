package edu.csu2017fa314.T17.Model;

import java.util.HashMap;

public class Location {
  private HashMap info = new HashMap();

  public Location(){}
  public Location(HashMap locHashMap){
    info = locHashMap;
  }

  @Override
  public String toString(){
    return info.toString();
  }

  public Object get (String code) {
    return info.get(code);
  }
  public HashMap getAll () {
    return info;
  }

  public void set ( String key, Object value ) {
    info.put(key, value);
  }
}