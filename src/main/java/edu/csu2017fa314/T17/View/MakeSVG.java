package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.Brewery;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MakeSVG {
  private double width;
  private double height;
  private double ratiox;
  private double ratioy;

  public MakeSVG () {
    this(1066.6073, 783.0824);
  }

  public MakeSVG (double width, double height) {
    this.width = width;
    this.height = height;

    // Calculate the ratio to use between deg and cart
    // Both of these are hardcoded for the colorado map but can be changed later
    ratiox = (1027-36)/(-102+109.05);
    ratioy = (747-40)/(37-41);
  }

  public void saveMap(ArrayList<Brewery> brews, String filename){
    try {
      PrintWriter out = new PrintWriter(filename.substring(0, filename.length() - 3) + "svg");
      out.print(buildColoradoMap(brews));
      out.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("Could not open: " + filename);
    }
  }

  /**
   * Constructs a '.svg' file that contains the connection between breweries
   * @param brews     The locations to place on the map
   */
  private String buildColoradoMap (ArrayList<Brewery> brews){
    StringBuilder map = new StringBuilder(String.format("%s %s %s %s\n\t%s %s\n",
      "<svg",
      "version='1.0'",
      String.format("width='%f'", width),
      String.format("height='%f'", height),
      "xmlns='http://www.w3.org/2000/svg'",
      ">").replace('\'', '\"'));

    for (Brewery brew: brews) {
       map.append(String.format("  %s\n", createCircle(brew)));
    }

    map.append("</svg>");
    return map.toString();
  }

  /**
   * Part of SVG helpers
   * Creates a String representing a circle at the given coords
   * @param brew  Location of the circle
   */
  private String createCircle (Brewery brew) {
    return String.format(
        "<ellipse ry='7' rx='7' cy='%d' cx='%d' stroke-width='5' fill='#FF0000' stroke='#000000'/>"
        .replace('\'', '\"'),
        Math.round(degreesToCartY(brew)),
        Math.round(degreesToCartX(brew))
      );
  }

  /**
   * Part of SVG helpers
   * Creates a line connecting two Brewery's
   * @param brew1 First Brewery
   * @param brew2 Second Brewery
   */
  private String createLine (Brewery brew1, Brewery brew2) {
    return "";
  }

  /**
   * @param brew  Brewery to collect longitude from
   * @return  value matching the cartesian map X
   */
  private double degreesToCartX (Brewery brew) {
    double deltaLoc = (double) brew.get("longitude") + 109.05;
    return deltaLoc * ratiox + 36;
  }

  /**
   * @param brew  Brewery to collect latitude from
   * @return value matching the cartesian map Y
   */
  private double degreesToCartY (Brewery brew) {
    double deltaLoc = (double) brew.get("latitude") - 41;
    return deltaLoc * ratioy + 40;
  }

}