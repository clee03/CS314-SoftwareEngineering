package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.*;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class WriteJSON {
  public WriteJSON() {
  }
  //Method that takes Brewery ArrayList and formats data then writes to a JSON file
  public void formatJSON(ArrayList<Brewery> brews, String fileName) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JSONArray jsonArray = new JSONArray();
    Distance dist = new Distance();

    for(int i = 0; i < brews.size()-1;  i++){
      //j gets the next brewery in brews
      int j = i +1;
      //Create new JSONObject from maps to add to JSONArray
      JSONObject orderedJson = new JSONObject();
      orderedJson.put("start", brews.get(i).get("id"));
      orderedJson.put("end", brews.get(j).get("id"));
      orderedJson.put("distance",  dist.greatCircleDistance(brews.get(i), brews.get(j)));
      jsonArray.add(orderedJson);
      }
    String jsonString = gson.toJson(jsonArray);
    try {
      String name = fileName.substring(0, fileName.length() - 3) + "json"; 
      //write to .json file
      FileWriter file = new FileWriter(name);
      file.write(jsonString);
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
