package edu.csu2017fa314.T17.Server;

import com.google.gson.Gson;
import edu.csu2017fa314.T17.Model.Brewery;
import edu.csu2017fa314.T17.Model.SQL;
import edu.csu2017fa314.T17.Model.ShorterTrip;
import edu.csu2017fa314.T17.View.MakeSVG;
import spark.Request;
import spark.Response;

import java.sql.*;
import java.util.ArrayList;

import static spark.Spark.post;

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

    String searched = rec.body().replace("\"", "");
    System.out.println("Searching: " + searched);

    SQL sql = new SQL();
    ArrayList<Brewery> brewList = sql.searchByWord(searched);

    if (brewList.size() != 0) {
      // create svg file
      ShorterTrip trip = new ShorterTrip(brewList, ShorterTrip.type.TwoOpt);
      brewList = trip.computePath();
      System.out.println("Total distance: " + trip.pathDistanceBrews(brewList));
    }
    MakeSVG svgObj = new MakeSVG();
    String SVG = svgObj.buildMap(brewList,true, true);

    ServerResponse response = new ServerResponse(SVG, brewList);

    System.out.println("Sending Itinerary and SVG back to server: " + searched);

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