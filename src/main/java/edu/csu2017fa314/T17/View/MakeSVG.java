package edu.csu2017fa314.T17.View;

import edu.csu2017fa314.T17.Model.Brewery;

import java.io.*;
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
      out.print(buildColoradoMap(brews, lines, dots));
      out.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("Could not open: " + filename);
    }
  }

  private String loadColoradoBackground() throws IOException {
    StringBuilder backgroundSVG = new StringBuilder("");
    String backgroundFileName = "data/colorado_map.svg";
    String line = null;

    try (BufferedReader br = new BufferedReader(new FileReader(backgroundFileName))){
      int skipFirstThree = 0;
      while((line=br.readLine()) != null){
        if (skipFirstThree < 3){
          skipFirstThree++;
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
  public String buildColoradoMap (ArrayList<Brewery> brews, boolean lines, boolean dots){
    StringBuilder map = new StringBuilder(String.format("%s %s %s %s\n\t%s %s\n",
      "<svg",
      "version='1.0'",
      String.format("width='%f'", width),
      String.format("height='%f'", height),
      "xmlns='http://www.w3.org/2000/svg'",
      ">").replace('\'', '\"'));

    // add colorado map background to svg
    try {
      map.append(loadColoradoBackground());
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
   * Part of SVG helpers
   * Creates a line connecting two Brewery's
   * @param brew1 First Brewery
   * @param brew2 Second Brewery
   */
  private String createLine (Brewery brew1, Brewery brew2) {
    return String.format(
        "<line y1='%d' x1='%d' y2='%d' x2='%d' stroke-width='1' stroke='#0000FF' fill='none'/>",
        Math.round(degreesToCartY(brew1)), Math.round(degreesToCartX(brew1)),
        Math.round(degreesToCartY(brew2)), Math.round(degreesToCartX(brew2))
    ).replace('\'','\"');
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