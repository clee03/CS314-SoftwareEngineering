package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class WriteJSON {
  private Gson gson;

  public WriteJSON() {
    gson = new GsonBuilder().setPrettyPrinting().create();
  }

  public String webJSON(ArrayList<Location> brews, Distance.unit units){
    return gson.toJson(buildJSONObject(brews, units));
  }

  private JSONObject buildJSONObject (ArrayList<Location> brews, Distance.unit units){
    JSONObject jobj = new JSONObject();
    jobj.put("brews", buildJSONArray(brews, units));
    return jobj;
  }

  private JSONArray buildJSONArray (ArrayList<Location> brews, Distance.unit units) {
    JSONArray jsonArray = new JSONArray();
    Distance dist = new Distance(units);

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
