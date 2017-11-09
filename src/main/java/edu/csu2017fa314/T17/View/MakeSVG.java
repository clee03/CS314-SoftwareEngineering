package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.Brewery;

import java.io.*;
import java.util.ArrayList;

public class MakeSVG {
  private double width;
  private double height;
  private double ratiox;
  private double ratioy;

  // Colorado: 1066.6073, 783.0824
  public MakeSVG () {
    this(1024, 512);
  }

  // world: 1024 x 512
  public MakeSVG (double width, double height) {
    this.width = width;
    this.height = height;

    // Calculate the ratio to use between deg and cart
    // Both of these are hardcoded for the colorado map but can be changed later
    ratiox = width / 360.0;  // Colorado: (1027-36)/(-102+109.05);
    ratioy = height / 180.0;  //           (747-40)/(37-41);
  }

  /**
   * Outputs the completed map to file.svg
   * @param brews     List of brewery's to draw
   * @param filename  File path to output to
   * @param lines     Turns on and off lines
   * @param dots      Turns on and off dots
   */
  public void saveMap(ArrayList<Brewery> brews, String filename,
                      boolean lines, boolean dots){
    try {
      PrintWriter out = new PrintWriter(filename.substring(0, filename.length() - 3) + "svg");
      out.print(buildMap(brews, lines, dots));
      out.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("Could not open: " + filename);
    }
  }

  /**
   * Outputs the svg as a string with world.svg and lines/dots
   * @param backgroundFilepath      filepath to background svg
   */
  private String loadMapBackground(String backgroundFilepath) throws IOException {
    StringBuilder backgroundSVG = new StringBuilder("");
    String backgroundFileName = backgroundFilepath; // data/world.svg
    String line = null;
    try (BufferedReader br = new BufferedReader(new FileReader(backgroundFileName))){
      int skipToSVGTag = 2; // World: 2, Colorado 3
      while((line=br.readLine()) != null){
        if (skipToSVGTag < 2){
          ++skipToSVGTag;
          continue;
        }
        backgroundSVG.append(line);
        backgroundSVG.append("\n");
      }
      if (backgroundSVG.length() > 0) {
        backgroundSVG.setLength(backgroundSVG.length() - 7);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return backgroundSVG.toString();
  }

  /**
   * Constructs a '.svg' file that contains the connection between breweries
   * @param brews     The locations to place on the map
   * @param lines     Turns on and off lines
   * @param dots      Turns on and off dots
   */
  public String buildMap (ArrayList<Brewery> brews, boolean lines, boolean dots){
    StringBuilder map = new StringBuilder();

    // add colorado map background to svg
    try {
      map.append(loadMapBackground("data/world.svg"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // add lines to svg
    if (lines) {
      for (Brewery brew : brews) {
        map.append(String.format("  %s\n", createCircle(brew)));
      }
    }

    // add dots to svg
    if (dots) {
      int i = 0;
      int j = i + 1;
      while (j < brews.size()) {
        map.append(String.format("  %s\n",
            createLine(brews.get(i), brews.get(j))
            )
        );

        i++;
        j++;
      }
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
        "<ellipse ry='3' rx='3' cy='%d' cx='%d' stroke-width='2' fill='#0000FF' stroke='#000000'/>",
        Math.round(degreesToCartY(brew)),
        Math.round(degreesToCartX(brew))
      ).replace('\'', '\"');
  }

  /**
   * Helper function for createLine();
   * determines how to wrap lines off an image
   * @param x1 start position of line
   * @param x2 end position of line
   */
  private int shouldWrap(int x1, int x2){
    // 0 don't wrap, 1 wrap left, 2 wrap right
    if ((x1-x2) > width/2) return 1;
    else if ((x2-x1) > width/2) return 2;
    else return 0;
  }

  /**
   * Part of SVG helpers
   * Creates a line connecting two Brewery's
   * @param brew1 First Brewery
   * @param brew2 Second Brewery
   */
  private String createLine (Brewery brew1, Brewery brew2) {
    int x1, x2;
    int y1, y2;
    x1 = (int)Math.round(degreesToCartX(brew1));
    x2 = (int)Math.round(degreesToCartX(brew2));
    y1 = (int)Math.round(degreesToCartY(brew1));
    y2 = (int)Math.round(degreesToCartY(brew2));

    String line;
    if (shouldWrap(x1, x2) == 1) {
      line = String.format(
          "<line y1='%d' x1='%d' y2='%d' x2='%d' stroke-width='1' stroke='#0000FF' fill='none'/>" +
          "<line y1='%d' x1='%d' y2='%d' x2='%d' stroke-width='1' stroke='#0000FF' fill='none'/>",
          y1, x1-(int)width, y2, x2,
          y1, x1, y2, (int)width+x2
      ).replace('\'', '\"');
    }
    else if (shouldWrap(x1, x2) == 2) {
      line = String.format(
          "<line y1='%d' x1='%d' y2='%d' x2='%d' stroke-width='1' stroke='#0000FF' fill='none'/>" +
              "<line y1='%d' x1='%d' y2='%d' x2='%d' stroke-width='1' stroke='#0000FF' fill='none'/>",
          y1, (int)width+x1, y2, x2,
          y1, x1, y2, x2-(int)width
          ).replace('\'', '\"');
    }
    else { // shouldWrap == 0
      line = String.format(
          "<line y1='%d' x1='%d' y2='%d' x2='%d' stroke-width='1' stroke='#0000FF' fill='none'/>",
          y1, x1, y2, x2
      ).replace('\'', '\"');
    }
    return line;
  }

  /**
   * @param brew  Brewery to collect longitude from
   * @return  value matching the cartesian map X
   */
  private double degreesToCartX (Brewery brew) {
    double longitude = (double) brew.get("longitude");
    // shift the map over to the right by 180
    double deltaLoc = longitude + 180;
    return deltaLoc * ratiox;
  }

  /**
   * @param brew  Brewery to collect latitude from
   * @return value matching the cartesian map Y
   */
  private double degreesToCartY (Brewery brew) {
    double latitude = (double) brew.get("latitude");
    // shift the map down by 90
    double deltaLoc = latitude + 90;
    // flip it back to
    return height - (deltaLoc * ratioy);
  }

}