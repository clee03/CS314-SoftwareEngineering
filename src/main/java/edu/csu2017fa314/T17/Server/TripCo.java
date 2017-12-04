package edu.csu2017fa314.T17.Server;

import static spark.Spark.port;

public class TripCo {
  public static void main(String[] args) {
    System.out.println("Welcome to Team 17's TripCo!");
    Server s = new Server();
    port(33001);
    s.serve();
  }
}

