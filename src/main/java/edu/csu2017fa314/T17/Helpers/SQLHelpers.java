package edu.csu2017fa314.T17.Helpers;

import java.io.BufferedReader;
import java.io.FileReader;

public class SQLHelpers {
  private static String parseLine(String line) {
    return line.split(":",2)[1].trim();
  }

  public static String[] collectCreds(){
    String[] creds = new String[2];
    String travisU = System.getenv("TRAVIS_USERNAME");
    if(travisU != null) {
      creds[0] = travisU;
      creds[1] = System.getenv("TRAVIS_PASSWORD");
      return creds;
    }

    BufferedReader bReader;
    try{
      bReader = new BufferedReader(new FileReader("data/creds.txt"));

      bReader.readLine(); // Intro text
      creds[0] = parseLine(bReader.readLine()); // Username
      creds[1] = parseLine(bReader.readLine()); // Password
    }
    catch (Exception e){
      System.err.print("Could not open the creds.txt file in data.");
    }
    return creds;
  }
}
