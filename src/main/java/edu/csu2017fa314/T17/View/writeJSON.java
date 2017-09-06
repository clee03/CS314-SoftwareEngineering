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

public class writeJSON {
  public writeJSON() {
  }
  //Method that takes Brewery ArrayList and formats data then writes to a JSON file
  public void formatJSON(ArrayList<Brewery> brews) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JSONArray jsonArray = new JSONArray();
    Distance dist = new Distance();

    for(int i = 0; i < brews.size()-1;  i++){
      //j gets the next brewery in brews
      int j = i +1;
      //Create new LinkedHashMap for each iteration
      LinkedHashMap<String, String> jsonMap = new LinkedHashMap<String, String>();
      //Create the map
      jsonMap.put("start", brews.get(i).getID());
      jsonMap.put("end", brews.get(j).getID());
      jsonMap.put("distance", Double.toString(dist.greatCircleDistance(brews.get(i), brews.get(j))));
      //Create new JSONObject from map to add to JSONArray
      JSONObject orderedJson = new JSONObject(jsonMap);
      jsonArray.add(orderedJson);
      }
    String jsonString = gson.toJson(jsonArray);
    try {
      //write to .json file
      FileWriter file = new FileWriter("Documents\\BreweryJsonFile.json");
      file.write(jsonString.toString());
      file.flush();
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}