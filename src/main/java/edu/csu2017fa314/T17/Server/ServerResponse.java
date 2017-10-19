package edu.csu2017fa314.T17.Server;
import edu.csu2017fa314.T17.View.WriteJSON;

import edu.csu2017fa314.T17.Model.Brewery;

import java.util.ArrayList;

/**
 * Created by sswensen on 10/1/17.
 * Edited by T17 on 10/17/17
 */

public class ServerResponse {
  private String svg = "";
  private ArrayList<Brewery> brews;

  public ServerResponse(String svg, ArrayList brews) {
    this.svg = svg;
    this.brews = brews;
  }

  public Object getJSON(){
    WriteJSON wj = new WriteJSON();
    return wj.webJSON(this.brews, this.svg);
  }

  @Override
  public String toString() {
    return "ServerResponse{" +
        "svg='" + svg + '\'' +
        ", locations=" + brews +
        '}';
  }
}