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

  public void formatJSON(ArrayList<Brewery> brews) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JSONArray jsonArray = new JSONArray();
    Distance dist = new Distance();
    //LinkedHashMap<String, String> jsonMap = new LinkedHashMap<String, String>();

    int j = 1;
    for(int i = 0; i < brews.size();  i++){
      LinkedHashMap<String, String> jsonMap = new LinkedHashMap<String, String>();
      jsonMap.put("start", brews.get(i).getID());
      jsonMap.put("end", brews.get(j).getID());
      jsonMap.put("distance", Double.toString(dist.greatCircleDistance(brews.get(i), brews.get(j++))));
      JSONObject orderedJson = new JSONObject(jsonMap);
      jsonArray.add(orderedJson);
      }
    String jsonString = gson.toJson(jsonArray);

    try {
      FileWriter file = new FileWriter("C:\\Users\\Jennifer\\Documents\\BreweryJsonFile.json");
      file.write(jsonString.toString());
      file.flush();
      file.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
