package edu.csu2017fa314.T17.View;
import edu.csu2017fa314.T17.Model.*;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
//import org.json.JSONWriter;
//import java.util.HashMap;

public class writeJSON {

  public writeJSON(){
  }

  public void formatJSON(Brewery a, Brewery b) {

    JSONArray jsonArr = new JSONArray();
    JSONObject jsonObj = new JSONObject();
    jsonObj.put("start", a.getID());
    jsonObj.put("end", b.getID());
    Distance dist = new Distance();
    jsonObj.put("distance", dist.greatCircleDistance(a, b));
    jsonArr.put(jsonObj);
    try {
      FileWriter file = new FileWriter("/Users/Documents/BreweryJsonFile.json");
      file.write(jsonArray.toString());
      file.flush();
      file.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

