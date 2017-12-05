package edu.csu2017fa314.T17.Server;

import static spark.Spark.port;

public class TripCo {
  private static int portNum = 33001;

  public static void main(String[] args) {
    if(args[0] == null || args[1] == null){
      System.out.println("Usage error:");
      System.out.println("java -jar [jar name] [CSU CS username] [CSU CS password]");
      return;
    }

    System.out.println("Starting server on port " + portNum);
    Server s = new Server(args[0], args[1]);
    port(portNum);
    s.serve();
  }
}

