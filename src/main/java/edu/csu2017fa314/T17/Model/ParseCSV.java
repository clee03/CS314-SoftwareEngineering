package edu.csu2017fa314.T17.Model;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class ParseCSV {
  private ArrayList<String> header = new ArrayList<> ();
  private ArrayList<Brewery> brewList = new ArrayList<> ();

  public ParseCSV (String filename) throws Exception {

    String line;
    BufferedReader br = new BufferedReader (new FileReader(filename));

    if ((line = br.readLine()) != null) {
      addHeader(line.toLowerCase());
      while ((line = br.readLine()) != null) {
        addLine(line);
      }
    }
   
    br.close();
  }
  
  /**
   *  Helper function to parse the first line of data
   *  @param line       the line to be parsed ( comma seperated )
   */
  private void addHeader(String line){
    header = new ArrayList<> (Arrays.asList(line.split(",")));
  }

  /**
   *  Helper function to parse a line in the brew file according to the header
   *  @param line       the line to be parsed ( comma seperated )
   */
  private void addLine(String line) {
    Brewery tmpBrew = new Brewery();
    String[] splitLine = line.split(",");
    ArrayList<String> values = new ArrayList<>();
    for (String s: splitLine) {
      values.add(s.trim());
    }

    // Loop through the header list and assign the values acording to the split line
    for (int i = 0; i < header.size(); i++) {
      String h = header.get(i);
      switch (h) {
        case "latitude":
          tmpBrew.setLat(degToDecimal(values.get(i)));
          break;
        case "longitude":
          tmpBrew.setLon(degToDecimal(values.get(i)));
          break;
        case "elevation": // fall through
        case "elevationft":
          tmpBrew.setElv(Double.parseDouble(values.get(i)));
          break;
        default:
          tmpBrew.set(h,values.get(i));
      }
    }

    brewList.add(tmpBrew);
  }

  /**
   *  Helper function to exchange lat/lon into their decimal counterparts
   *  @param degrees    the string that will be parsed
   *  @return double    a decimal value representing the string passed as a parameter
   */
  public double degToDecimal (String degrees) {
    degrees = degrees.replace(" ", "");
    String[] parts = degrees.split("[°'\"]");

    double total = 0;
    switch (parts.length) {
      case 4:
        total += (Double.parseDouble(parts[2]) / 3600 );
        // fall through
      case 3:
        total += (Double.parseDouble(parts[1]) / 60 );
        // fall through
      case 2:
        total += (Double.parseDouble( parts[0]));
        String cardinal = parts[ parts.length - 1 ];
        if(cardinal.equals("W") || cardinal.equals("S"))
          total *= -1;
        return total;
      default:
        return Double.parseDouble(parts[0]);
    }
  }

  public ArrayList<String> getHeader (){
    return header;
  }
  public ArrayList<Brewery> getBrewerys (){
    return brewList;
  }
}
