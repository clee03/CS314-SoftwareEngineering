package edu.csu2017fa314.T17.Server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.csu2017fa314.T17.Model.Brewery;
import edu.csu2017fa314.T17.Model.ParseCSV;
import edu.csu2017fa314.T17.Model.ShorterTrip;
import edu.csu2017fa314.T17.View.MakeSVG;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

import static spark.Spark.post;
import static spark.Spark.port;

/**
 * Created by sswensen on 10/1/17.
 * Edited by T17 on 10/17/17
 */

public class Server {
  public static void main(String[] args) {
    Server s = new Server();
    s.serve();
  }

  public void serve() {
    //port(112233);
    Gson g = new Gson();
    post("/testing", this::testing, g::toJson); // Create new listener
    //post("/search", this::search, g::toJson);
    //post("/plan", this::plan, g::toJson);
  }

  private Object testing(Request rec, Response res) {
    // Set the return headers
    setHeaders(res);
    System.out.println("Got " + rec.body() + "from the server.");

    //JsonParser parser = new JsonParser();
    //JsonElement element = parser.parse(rec.body());
    Gson gson = new Gson();
    //ServerRequest request = gson.fromJson(element, ServerRequest.class);
    //System.out.println("Got \"" + request.toString() + "\" from server.");
    //String searched = request.getQuery();

    // start use database
    /*
    String searched = rec.body();
    SQL sql = new SQL(searched, SQL.sqlType.remote);
    ArrayList<Brewery> brewList = new ArrayList<>();
    brewList = sql.destList;
    */
    // end use database

    // start use hard-coded
    String temp = "data/airport.csv";
    String CSV = "data/airport.csv";
    CSV = CSV.replaceAll(".*/", "");
    CSV = CSV.replaceAll(".csv", "");
    ParseCSV parse = null;
    try {
      parse = new ParseCSV(temp);
    } catch (Exception e) {
      System.out.println("Error parsing the .csv file \'" + CSV +
          ".csv\'! Please input better data.");
      e.printStackTrace();
    }
    ArrayList<Brewery> brewList = parse.getBrewerys();
    // end use hard-coded

    // create svg file
    ShorterTrip st = new ShorterTrip(brewList, ShorterTrip.type.TwoOpt);
    brewList = st.computePath();
    int totalDistance = st.pathDistanceBrews(brewList);
    //System.out.println("Total Distance " + CSV + "]: " + totalDistance);

    // create Json file
    //WriteJSON jWrite = new WriteJSON();
    //jWrite.formatJSON(brewList, temp);
    //System.out.println("JSON file \'data/" + CSV + ".json\' successfully created!");

    MakeSVG svgObj = new MakeSVG(1066.6073,783.0824);
    String SVG = svgObj.buildColoradoMap(brewList,true, true);

    ServerResponse response = new ServerResponse(SVG, brewList);

    System.out.print(response.getJSON());

    Object ret = gson.toJson(response, ServerResponse.class);
    return response.getJSON();
  }

  private void setHeaders(Response res) {
    // Declares returning type json
    res.header("Content-Type", "application/json");

    // Ok for browser to call even if different host host
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "*");
  }
}