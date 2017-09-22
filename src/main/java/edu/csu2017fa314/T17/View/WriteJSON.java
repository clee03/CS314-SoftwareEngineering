package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.*;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class WriteJSON {
  Gson gson;

  public WriteJSON() {
    gson = new GsonBuilder().setPrettyPrinting().create();
  }

  //Method that takes Brewery ArrayList and formats data then writes to a JSON file
  public void formatJSON(ArrayList<Brewery> brews, String fileName) {
    JSONArray jsonArray = buildJSONArray(brews);

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

  private JSONArray buildJSONArray (ArrayList<Brewery> brews) {
    JSONArray jsonArray = new JSONArray();
    Distance dist = new Distance();

    for(int i = 0; i < brews.size()-1;  i++){
      //j gets the next brewery in brews
      int j = i + 1;
      //Create new JSONObject from maps to add to JSONArray
      JSONObject jsonObj = new JSONObject();

      Iterator it = brews.get(i).getAll().entrySet().iterator();
      JSONObject startObj = new JSONObject();
      while (it.hasNext()){
        HashMap.Entry entry = (HashMap.Entry)it.next();
        startObj.put(entry.getKey(),entry.getValue());
      }
      jsonObj.put("start", startObj);

      it = brews.get(j).getAll().entrySet().iterator();
      JSONObject endObj = new JSONObject();
      while (it.hasNext()){
        HashMap.Entry entry = (HashMap.Entry)it.next();
        endObj.put(entry.getKey(),entry.getValue());
      }
      jsonObj.put("end", endObj);

      jsonObj.put("distance",  dist.greatCircleDistance(brews.get(i), brews.get(j)));
      jsonArray.add(jsonObj);
    }

    return jsonArray;
  }
}
