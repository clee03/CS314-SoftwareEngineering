package edu.csu2017fa314.T17.Server;

import static edu.csu2017fa314.T17.Helpers.SQLHelpers.collectCreds;
import static spark.Spark.port;

public class TripCo {
  private static int portNum = 33001;

  public static void main(String[] args) {
    System.out.println("Starting server on port " + portNum);
    String[] creds = collectCreds();
    Server s = new Server(creds[0], creds[1]);
    port(portNum);
    s.serve();
  }
}

