package edu.csu2017fa314.T17.Server;

import com.google.gson.*;

import edu.csu2017fa314.T17.Model.*;

import edu.csu2017fa314.T17.View.WriteJSON;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;

import static spark.Spark.post;

public class Server {
  private SQL sql;

  public Server(String username, String password){
    sql = new SQL(username, password);
  }

  public void serve() {
    Gson g = new Gson();
    post("/search", this::search, g::toJson);
    post("/load", this::load, g::toJson);
    post("/plan", this::plan, g::toJson);
  }

  private Object search(Request rec, Response res) {
    // Set the return headers
    setHeaders(res);
    System.out.println("Loading file from the server.");

    JsonObject data = new JsonParser().parse(rec.body()).getAsJsonObject();
    String searchTerm = data.getAsJsonPrimitive("t").getAsString();
    int searchLimit = data.getAsJsonPrimitive("l").getAsInt();

    // get the search string
    System.out.println("Searching for [" + searchTerm + "] and returning " + searchLimit + " results.");
    // pass string to SQL
    HashMap<String, String> results = sql.searchAllTablesByWord(searchTerm, searchLimit);
    // return result-set
    return new JSONObject(results);
  }

  private Object load(Request rec, Response res) {
    // Set the return headers
    setHeaders(res);

    // get data from request
    JsonArray codez = new JsonParser().parse(rec.body()).getAsJsonArray();

    // codes: codes
    ArrayList<String> codes = new ArrayList<>();
    Iterator<JsonElement> it = codez.iterator();
    while(it.hasNext()){
      codes.add(it.next().getAsJsonPrimitive().getAsString());
    }
    System.out.println(codes.toString());
    String[] codesA = new String[codes.size()];
    codesA = codes.toArray(codesA);

    // set brewList
    HashMap<String, String> data = sql.getNamesWithID(codesA);

    // return result-set
    return new JSONObject(data);
  }

  private Object plan(Request rec, Response res) {
    // Set the return headers
    setHeaders(res);

    // get data from request
    JsonObject object = new JsonParser().parse(rec.body()).getAsJsonObject();

    // optimization: optz
    String optz = object.getAsJsonPrimitive("optz").getAsString();
    System.out.println("Optimization: " + optz);
    // units: units
    String unit = object.getAsJsonPrimitive("units").getAsString();
    System.out.println("units: " + unit);
    // codes: codes
    ArrayList<String> codes = new ArrayList<>();
    Iterator<JsonElement> it = object.getAsJsonArray("codes").iterator();
    while(it.hasNext()){
      codes.add(it.next().getAsJsonPrimitive().getAsString());
    }
    System.out.println(codes.toString());
    String[] codesA = new String[codes.size()];
    codesA = codes.toArray(codesA);

    // set brewList
    ArrayList<Location> brewList = sql.getAllDataWithID(codesA);

    // set units
    Distance.unit units;
    if (unit.equals("km")) {
      units = Distance.unit.Kilometers;
    }
    else {
      units = Distance.unit.Miles;
    }
    // set optimization
    ShorterTrip.type opt = ShorterTrip.type.NoOpt;
    if (brewList.size() != 0) {
      switch(optz) {
        case "none":
          opt = ShorterTrip.type.NoOpt;
          break;
        case "nn":
          opt = ShorterTrip.type.NearestNeighbor;
          break;
        case "2opt":
          opt = ShorterTrip.type.TwoOpt;
          break;
        case "3opt":
        default: //3opt
          opt = ShorterTrip.type.ThreeOpt;
          break;
      }
    }
    // create trip
    ShorterTrip trip = new ShorterTrip(brewList, opt, units);
    brewList = trip.computePath();
    System.out.println("Total distance: " + trip.pathDistanceBrews(brewList));

    // pass brewList and SVG back to web
    System.out.println("Sending Itinerary and SVG back to server");
    WriteJSON wj = new WriteJSON();
    return wj.webJSON(brewList, units);
  }

  private void setHeaders(Response res) {
    // Declares returning type json
    res.header("Content-Type", "application/json");

    // Ok for browser to call even if different host host
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "*");
  }
}