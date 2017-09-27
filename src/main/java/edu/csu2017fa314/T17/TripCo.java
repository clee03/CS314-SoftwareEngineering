package edu.csu2017fa314.T17;
import edu.csu2017fa314.T17.Model.*;
import edu.csu2017fa314.T17.View.*;
import java.util.ArrayList;

public class TripCo {
  public static void main (String[] args) {
    System.out.println("Welcome to TripCo");
    if (args.length == 0){
      System.out.println("Proper usage of this program is through " +
          "the commandline followed by a .csv file");
    }else if( args.length > 1){
      System.out.println("sorry this feature isn't implemented yet." +
          "please use only a single file for now");
    }

    ParseCSV parse = null;
    try {
      parse = new ParseCSV(args[0]);
    } catch (Exception e) {
      System.out.println("Error parsing the .csv file! Please input better data");
      e.printStackTrace();
    }

    // create svg file
    ArrayList<Brewery> brewList = parse.getBrewerys();
    ShorterTrip st = new ShorterTrip(brewList);
    brewList = st.computePath();
    int totalDistance = st.pathDistanceBrews(brewList);
    System.out.println("Total Distance: " + totalDistance);
    MakeSVG svgObj = new MakeSVG(1066.6073,783.0824);
    svgObj.saveMap(brewList, args[0],
        true, true);
    System.out.println("Svg file successfully created");

    // create Json file
    WriteJSON jWrite = new WriteJSON();
    jWrite.formatJSON(brewList, args[0]);
    System.out.println("Json file successfully created!");
  }
}
