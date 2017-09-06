package edu.csu2017fa314.T17.View;
import edu.csu2017fa314.T17.Model.*;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.LinkedHashMap;
import java.util.Arrays;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class writeJSON {

  public writeJSON() {
  }

  public void formatJSON(Brewery a, Brewery b) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JSONArray jsonArray = new JSONArray();
    Distance dist = new Distance();
    LinkedHashMap<String, String> jsonMap = new LinkedHashMap<String, String>();

    jsonMap.put("start", a.getID());
    jsonMap.put("end", b.getID());
    jsonMap.put("distance", Double.toString(dist.greatCircleDistance(a, b)));

    JSONObject orderedJson = new JSONObject(jsonMap);
    jsonArray.add(orderedJson);
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
